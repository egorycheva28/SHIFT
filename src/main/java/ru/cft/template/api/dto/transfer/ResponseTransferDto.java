package ru.cft.template.api.dto.transfer;

import ru.cft.template.api.dto.transfer.enums.StatusTransfer;
import ru.cft.template.api.dto.transfer.enums.TransferType;

import java.util.Date;
import java.util.UUID;

public record ResponseTransferDto(
         UUID transferId,
         UUID userId,
         Date creationTime,
         Long amount,
         TransferType transferType,
         StatusTransfer status
) {
}
