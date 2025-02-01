package ru.cft.template.core.service;

import ru.cft.template.api.dto.wallet.GetWalletDto;
import ru.cft.template.core.entity.Wallet;

import java.util.UUID;

public interface WalletService {
    Wallet createWallet();

    GetWalletDto getWalletByUserId(UUID userId, UUID sessionId);
}
