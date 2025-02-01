package ru.cft.template.api.mapper;

import org.springframework.stereotype.Component;
import ru.cft.template.api.dto.wallet.GetWalletDto;
import ru.cft.template.core.entity.Wallet;

@Component
public class WalletMapper {
    public static GetWalletDto getWalletMapper(Wallet wallet)
    {
        return new GetWalletDto(
                wallet.getNumber(),
                wallet.getBalance()
        );
    }
}
