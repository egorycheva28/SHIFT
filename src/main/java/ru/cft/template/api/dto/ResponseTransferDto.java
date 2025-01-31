package ru.cft.template.api.dto;

import java.util.Date;
import java.util.UUID;

public record ResponseTransferDto(
         UUID id,
         UUID userId,
         Date creationTime,
         Long amount,
         TransferType transferType,
         StatusTransfer status
) {
}
