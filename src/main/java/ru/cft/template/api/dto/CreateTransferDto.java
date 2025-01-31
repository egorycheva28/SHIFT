package ru.cft.template.api.dto;

public record CreateTransferDto(
        Integer walletId,
        Long amount
) {
}
