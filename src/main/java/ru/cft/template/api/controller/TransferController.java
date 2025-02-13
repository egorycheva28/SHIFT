package ru.cft.template.api.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.cft.template.api.dto.transfer.CreateTransferDto;
import ru.cft.template.api.dto.transfer.ListTransfersDto;
import ru.cft.template.api.dto.transfer.ResponseTransferDto;
import ru.cft.template.api.dto.transfer.enums.StatusTransfer;
import ru.cft.template.api.dto.transfer.enums.TransferType;
import ru.cft.template.core.service.TransferService;

import java.util.UUID;

@RestController
@RequestMapping("/transfers")
@RequiredArgsConstructor
public class TransferController {

    private final TransferService transferService;

    @PostMapping()
    public ResponseTransferDto createUser(@RequestBody CreateTransferDto createTransferDto,
                                          @RequestHeader("Authorization") UUID sessionId) {

        return transferService.createTransfer(createTransferDto, sessionId);
    }

    @GetMapping()
    public ListTransfersDto getTransfers(@RequestParam(required = false) TransferType transferType,
                                         @RequestParam(required = false) StatusTransfer statusTransfer,
                                         @RequestParam(required = false) UUID userId,
                                         @RequestParam Long size,
                                         @RequestParam Long current,
                                         @RequestHeader("Authorization") UUID sessionId) {

        return transferService.getTransfers(transferType, statusTransfer, userId, size, current, sessionId);
    }

    @GetMapping("/{transferId}")
    public ResponseTransferDto getTransferById(@PathVariable(name = "transferId") UUID transferId,
                                               @RequestHeader("Authorization") UUID sessionId) {

        return transferService.getTransferById(transferId, sessionId);
    }

}
