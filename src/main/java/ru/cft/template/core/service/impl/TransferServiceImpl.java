package ru.cft.template.core.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.cft.template.api.dto.transfer.CreateTransferDto;
import ru.cft.template.api.dto.transfer.ListTransfersDto;
import ru.cft.template.api.dto.transfer.Pagination;
import ru.cft.template.api.dto.transfer.ResponseTransferDto;
import ru.cft.template.api.dto.transfer.enums.StatusTransfer;
import ru.cft.template.api.dto.transfer.enums.TransferType;
import ru.cft.template.api.mapper.TransferMapper;
import ru.cft.template.core.exception.*;
import ru.cft.template.core.exception.ValidationException;
import ru.cft.template.core.repository.SessionRepository;
import ru.cft.template.core.repository.TransferRepository;
import ru.cft.template.core.repository.UserRepository;
import ru.cft.template.core.repository.WalletRepository;
import ru.cft.template.core.service.TransferService;
import ru.cft.template.core.entity.Session;
import ru.cft.template.core.entity.Transfer;
import ru.cft.template.core.entity.User;
import ru.cft.template.core.entity.Wallet;

import javax.validation.*;

import java.util.*;

@Service
@RequiredArgsConstructor
public class TransferServiceImpl implements TransferService {

    private final Validator validator;

    private final TransferRepository transferRepository;
    private final SessionRepository sessionRepository;
    private final WalletRepository walletRepository;
    private final UserRepository userRepository;

    @Autowired
    public TransferServiceImpl(TransferRepository transferRepository, SessionRepository sessionRepository, WalletRepository walletRepository, UserRepository userRepository) {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();

        this.transferRepository = transferRepository;
        this.sessionRepository = sessionRepository;
        this.walletRepository = walletRepository;
        this.userRepository = userRepository;
    }

    @Override
    public ResponseTransferDto createTransfer(CreateTransferDto createTransferDto, UUID sessionId) {
        Session session = sessionRepository.findById(sessionId)
                .orElseThrow(() -> new SessionNotFoundException("Session with ID: " + sessionId + " not found"));

        User user = userRepository.findById(session.getUserId())
                .orElseThrow(() -> new UserNotFoundException("User with ID: " + session.getUserId() + " not found"));

        User userId = null;
        List<User> users = (List<User>) userRepository.findAll();

        Wallet wallet = null;
        if (createTransferDto.walletId() != null) {
            wallet = walletRepository.findById(createTransferDto.walletId())
                    .orElseThrow(() -> new WalletNotFoundException("Wallet with ID: " + createTransferDto.walletId() + " not found"));

            for (User userr : users) {
                if (Objects.equals(userr.getWallet().getNumber(), createTransferDto.walletId())) {
                    userId = userr;
                }
            }

            if (createTransferDto.walletId().equals(user.getWallet().getNumber())) {
                throw new AccessRightsException("Вы не можете переводить себе");
            }
        }

        if (createTransferDto.phone() != null) {
            for (User userr : users) {
                if (Objects.equals(userr.getPhone(), createTransferDto.phone())) {
                    userId = userr;
                }
            }

            if (createTransferDto.phone().equals(user.getPhone())) {
                throw new AccessRightsException("Вы не можете переводить себе");
            }
        }

        Transfer transfer = new Transfer();
        transfer.setSenderWallet(user.getWallet());
        transfer.setReceiveWallet(wallet);
        transfer.setReceiverPhone(userId);
        transfer.setCreationTime(new Date());
        transfer.setAmount(createTransferDto.amount());
        transfer.setTransferType(TransferType.TRANSFER);
        transfer.setStatus(StatusTransfer.PAID);

        var errors = validator.validate(transfer);
        if (!errors.isEmpty()) {
            StringBuilder errorMessage = new StringBuilder();

            for (var error : errors) {
                errorMessage.append(error.getMessage()).append(" ");
            }

            throw new ValidationException(errorMessage.toString());
        }

        if (createTransferDto.phone() != null) {
            userId.getWallet().plusAmount(createTransferDto.amount());
        }

        if (createTransferDto.walletId() != null) {
            wallet.plusAmount(createTransferDto.amount());
        }

        user.getWallet().minusAmount(createTransferDto.amount());

        transferRepository.save(transfer);

        return TransferMapper.responseTransferMapper(transfer);
    }

    @Override
    public ListTransfersDto getTransfers(TransferType transferType, StatusTransfer statusTransfer, UUID userId, Long size, Long current, UUID sessionId) {
        Session session = sessionRepository.findById(sessionId)
                .orElseThrow(() -> new SessionNotFoundException("Session with ID: " + sessionId + " not found"));

        //userId получатель receiverWallet
        List<Transfer> transfers = (List<Transfer>) transferRepository.findAll();//все переводы
        List<ResponseTransferDto> filterTransfers = new ArrayList<>();//отфильтрованный список

        //фильтрация
        for (Transfer transfer : transfers) {
            if ((transferType == null || transferType.equals(transfer.getTransferType())) &&
                    (statusTransfer == null || statusTransfer.equals(transfer.getStatus()))
                /*(userId == null || userId.equals(transfer.getReceiveWallet()))*/) {

                filterTransfers.add(TransferMapper.responseTransferMapper(transfer));
            }
        }

        //пагинация
        //size - количество на одной странице (размер страницы), count - всего страниц, current - текущая
        int countTransfers = filterTransfers.size();//всего переводов
        int countPage = (int) Math.ceil((double) countTransfers / size);//всего страниц

        if (countTransfers > 0 && current > countPage) {
            throw new PaginationException("Вы хотите посмотреть несуществующую страницу.");
        }

        List<ResponseTransferDto> currentTransfers = filterTransfers.stream().skip((current - 1) * size).limit(size).toList(); //список переводов на данной странице

        Pagination pagination = new Pagination(size, countTransfers, current);

        /*if (!session.getActive()) {
            throw new UserNotFoundException("вы не авторизованы");
        }*/

        return TransferMapper.listTransfersDto(currentTransfers, pagination);
    }

    @Override
    public ResponseTransferDto getTransferById(UUID transferId, UUID sessionId) {
        Session session = sessionRepository.findById(sessionId)
                .orElseThrow(() -> new SessionNotFoundException("Session with ID: " + sessionId + " not found"));

        Transfer transfer = transferRepository.findById(transferId)
                .orElseThrow(() -> new TransferNotFoundException("Transfer with ID: " + transferId + " not found"));

        return TransferMapper.responseTransferMapper(transfer);
    }

}
