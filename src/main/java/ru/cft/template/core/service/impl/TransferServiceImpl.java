package ru.cft.template.core.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.cft.template.api.dto.*;
import ru.cft.template.api.dto.user.CreateUserDto;
import ru.cft.template.api.dto.user.GetUserByIdDto;
import ru.cft.template.api.mapper.TransferMapper;
import ru.cft.template.core.exception.*;
import ru.cft.template.core.repository.SessionRepository;
import ru.cft.template.core.repository.TransferRepository;
import ru.cft.template.core.repository.UserRepository;
import ru.cft.template.core.repository.WalletRepository;
import ru.cft.template.core.service.TransferService;
import ru.cft.template.core.service.WalletService;
import ru.cft.template.entity.Session;
import ru.cft.template.entity.Transfer;
import ru.cft.template.entity.User;
import ru.cft.template.entity.Wallet;

import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TransferServiceImpl implements TransferService {

    private final TransferRepository transferRepository;
    private final SessionRepository sessionRepository;
    private final WalletRepository walletRepository;
    private final UserRepository userRepository;

    @Override
    public ResponseTransferDto createTransfer(CreateTransferDto createTransferDto, UUID sessionId) {
        Session session = sessionRepository.findById(sessionId)
                .orElseThrow(() -> new SessionNotFoundException("Session with ID: " + sessionId + " not found"));

        Wallet wallet = walletRepository.findById(createTransferDto.walletId())
                .orElseThrow(() -> new WalletNotFoundException("Wallet with ID: " + createTransferDto.walletId() + " not found"));

        User user = userRepository.findById(session.getUserId())
                .orElseThrow(() -> new UserNotFoundException("User with ID: " + session.getUserId() + " not found"));

        if (createTransferDto.walletId().equals(user.getWallet().getNumber())) {
            throw new AccessRightsException("Вы не можете переводить себе");
        }

        UUID userId = null;
        List<User> users = (List<User>) userRepository.findAll();
        for (User userr : users) {
            if (Objects.equals(userr.getWallet().getNumber(), createTransferDto.walletId())) {
                userId = userr.getId();
            }
        }

        Transfer transfer = new Transfer();
        transfer.setUserId(userId);
        transfer.setCreationTime(new Date());
        transfer.setAmount(createTransferDto.amount());
        transfer.setTransferType(TransferType.TRANSFER);
        transfer.setStatus(StatusTransfer.PAID);
        transferRepository.save(transfer);

        wallet.plusAmount(createTransferDto.amount());
        user.getWallet().minusAmount(createTransferDto.amount());

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

        /*if(!session.getActive())
        {
            throw new UserNotFoundException("вы не авторизованы");
        }*/

        return TransferMapper.ResponseTransferMapper(transfer);
    }

}
