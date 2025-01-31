package ru.cft.template.api.dto.wallet;

public record GetWalletDto(
        Integer number,
        Long balance
) {
}
