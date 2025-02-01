package ru.cft.template.api.dto.transfer;

import ru.cft.template.api.dto.transfer.enums.StatusTransfer;
import ru.cft.template.api.dto.transfer.enums.TransferType;

import java.util.UUID;

public record FilterTransfersDto(
        TransferType transferType,
        StatusTransfer active,
        UUID userId
) {
}
