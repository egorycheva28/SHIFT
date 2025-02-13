package ru.cft.template.core.service;

import ru.cft.template.api.dto.transfer.CreateTransferDto;
import ru.cft.template.api.dto.transfer.ListTransfersDto;
import ru.cft.template.api.dto.transfer.ResponseTransferDto;
import ru.cft.template.api.dto.transfer.enums.StatusTransfer;
import ru.cft.template.api.dto.transfer.enums.TransferType;

import java.util.List;
import java.util.UUID;

public interface TransferService {
    ResponseTransferDto createTransfer(CreateTransferDto createTransferDto, UUID sessionId);

    ListTransfersDto getTransfers(TransferType transferType, StatusTransfer statusTransfer, UUID userId, Long size, Long current, UUID sessionId);

    ResponseTransferDto getTransferById(UUID transferId, UUID sessionId);
}
