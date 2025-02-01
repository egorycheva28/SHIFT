package ru.cft.template.core.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.cft.template.api.dto.transfer.CreateTransferDto;
import ru.cft.template.api.dto.transfer.ResponseTransferDto;
import ru.cft.template.api.dto.transfer.enums.StatusTransfer;
import ru.cft.template.api.dto.transfer.enums.TransferType;
import ru.cft.template.api.mapper.TransferMapper;
import ru.cft.template.core.exception.*;
import ru.cft.template.core.repository.SessionRepository;
import ru.cft.template.core.repository.TransferRepository;
import ru.cft.template.core.repository.UserRepository;
import ru.cft.template.core.repository.WalletRepository;
import ru.cft.template.core.service.TransferService;
import ru.cft.template.core.entity.Session;
import ru.cft.template.core.entity.Transfer;
import ru.cft.template.core.entity.User;
import ru.cft.template.core.entity.Wallet;

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

        wallet.plusAmount(createTransferDto.amount());
        user.getWallet().minusAmount(createTransferDto.amount());

        transferRepository.save(transfer);

        return TransferMapper.ResponseTransferMapper(transfer);
    }



    @Override
    public ResponseTransferDto getTransferById(UUID transferId, UUID sessionId) {
        Session session = sessionRepository.findById(sessionId)
                .orElseThrow(() -> new SessionNotFoundException("Session with ID: " + sessionId + " not found"));

        Transfer transfer = transferRepository.findById(transferId)
                .orElseThrow(() -> new TransferNotFoundException("Transfer with ID: " + transferId + " not found"));

        return TransferMapper.ResponseTransferMapper(transfer);
    }

}
