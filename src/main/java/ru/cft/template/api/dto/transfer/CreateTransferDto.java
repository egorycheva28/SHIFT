package ru.cft.template.api.dto.transfer;

public record CreateTransferDto(
        Integer walletId,
        Long phone,
        Long amount
) {
}
