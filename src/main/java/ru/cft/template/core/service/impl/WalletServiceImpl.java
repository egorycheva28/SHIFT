package ru.cft.template.core.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.cft.template.api.dto.wallet.GetWalletDto;
import ru.cft.template.api.mapper.WalletMapper;
import ru.cft.template.core.exception.AccessRightsException;
import ru.cft.template.core.exception.SessionNotFoundException;
import ru.cft.template.core.exception.UserNotFoundException;
import ru.cft.template.core.repository.SessionRepository;
import ru.cft.template.core.service.WalletService;
import ru.cft.template.core.entity.Session;
import ru.cft.template.core.entity.User;
import ru.cft.template.core.entity.Wallet;
import ru.cft.template.core.repository.UserRepository;
import ru.cft.template.core.repository.WalletRepository;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class WalletServiceImpl implements WalletService {

    private final WalletRepository walletRepository;
    private final UserRepository userRepository;

    private final SessionRepository sessionRepository;

    @Override
    public Wallet createWallet() {
        Wallet wallet = new Wallet();
        wallet.setBalance(100L);

        walletRepository.save(wallet);
        return wallet;
    }

    @Override
    public GetWalletDto getWalletByUserId(UUID userId, UUID sessionId) {
        Session session = sessionRepository.findById(sessionId)
                .orElseThrow(() -> new SessionNotFoundException("Session with ID: " + sessionId + " not found"));

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User with ID: " + userId + " not found"));

        if (!userId.equals( session.getUserId())) {
            throw new AccessRightsException("Можно посмотреть только свой кошелек");
        }

        return WalletMapper.getWalletMapper(user.getWallet());
    }
}
