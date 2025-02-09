package ru.cft.template.core.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.cft.template.api.dto.transfer.CreateTransferDto;
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
import ru.cft.template.core.service.WalletService;

import javax.validation.*;

import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

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

        return TransferMapper.ResponseTransferMapper(transfer);
    }

/*@Override
    public List<ResponseTransferDto> getTransfers(FilterTransfersDto filterTransfersDto, UUID sessionId) {
        Session session = sessionRepository.findById(sessionId)
                .orElseThrow(() -> new SessionNotFoundException("Session with ID: " + sessionId + " not found"));

        if (filterTransfersDto.transferType() == TransferType.TRANSFER) {

        }
        if (filterTransfersDto.active() == StatusTransfer.PAID) {

        }
        if (filterTransfersDto.active() == StatusTransfer.UNPAID) {

        }

        List<Transfer> transfers = (List<Transfer>) transferRepository.findAll();
        for (Transfer transfer : transfers) {
            //System.out.println("ID: " + transfer.getId() + ", Name: " + transfer.getName());
        }

        if(!session.getActive())
        {
            throw new UserNotFoundException("вы не авторизованы");
        }

        return List < TransferMapper.ResponseTransferMapper(transfer) >;
    }*/

    @Override
    public ResponseTransferDto getTransferById(UUID transferId, UUID sessionId) {
        Session session = sessionRepository.findById(sessionId)
                .orElseThrow(() -> new SessionNotFoundException("Session with ID: " + sessionId + " not found"));

        Transfer transfer = transferRepository.findById(transferId)
                .orElseThrow(() -> new TransferNotFoundException("Transfer with ID: " + transferId + " not found"));

        return TransferMapper.ResponseTransferMapper(transfer);
    }

}
