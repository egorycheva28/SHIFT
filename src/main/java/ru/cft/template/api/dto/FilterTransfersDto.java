package ru.cft.template.api.dto;

import java.util.UUID;

public record FilterTransfersDto(
        TransferType transferType,
        StatusTransfer active,
        UUID userId
) {
}
