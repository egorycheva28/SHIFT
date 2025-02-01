package ru.cft.template.core.service;

import ru.cft.template.api.dto.transfer.CreateTransferDto;
import ru.cft.template.api.dto.transfer.ResponseTransferDto;

import java.util.UUID;

public interface TransferService {
    ResponseTransferDto createTransfer(CreateTransferDto createTransferDto, UUID sessionId);

    //List<ResponseTransferDto> getTransfers(FilterTransfersDto filterTransfersDto, UUID sessionId);

    ResponseTransferDto getTransferById(UUID transferId, UUID sessionId);
}
