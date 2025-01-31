package ru.cft.template.core.service;

import org.springframework.http.ResponseEntity;
import ru.cft.template.api.dto.CreateTransferDto;
import ru.cft.template.api.dto.FilterTransfersDto;
import ru.cft.template.api.dto.ResponseTransferDto;
import ru.cft.template.api.dto.user.CreateUserDto;
import ru.cft.template.api.dto.user.GetUserByIdDto;
import ru.cft.template.api.dto.user.UpdateUserDto;

import java.util.List;
import java.util.UUID;

public interface TransferService {
    ResponseTransferDto createTransfer(CreateTransferDto createTransferDto, UUID sessionId);

    //List<ResponseTransferDto> getTransfers(FilterTransfersDto filterTransfersDto, UUID sessionId);

    ResponseTransferDto getTransferById(UUID transferId, UUID sessionId);
}
