package ru.cft.template.api.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.cft.template.api.dto.CreateTransferDto;
import ru.cft.template.api.dto.FilterTransfersDto;
import ru.cft.template.api.dto.ResponseTransferDto;
import ru.cft.template.api.dto.user.CreateUserDto;
import ru.cft.template.api.dto.user.GetUserByIdDto;
import ru.cft.template.api.dto.user.UpdateUserDto;
import ru.cft.template.core.exception.AuthorizationException;
import ru.cft.template.core.service.TransferService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/transfers")
@RequiredArgsConstructor
public class TransferController {

    private final TransferService transferService;

    @PostMapping()
    public ResponseTransferDto createUser(@RequestBody CreateTransferDto createTransferDto,
                                          @RequestHeader("Authorization") UUID sessionId)
    {
        if (sessionId == null) {
            throw new AuthorizationException("Пользователь не авторизован");
        }

        return transferService.createTransfer(createTransferDto, sessionId);
    }

    /*@GetMapping()
    public List<ResponseTransferDto> getTransfers(@RequestBody FilterTransfersDto filterTransfersDto,
                                                  @RequestHeader("Authorization") UUID sessionId) {

        if (sessionId == null) {
            throw new AuthorizationException("Пользователь не авторизован");
        }

        return transferService.getTransfers(filterTransfersDto, sessionId);
    }*/

    @GetMapping("/{transferId}")
    public ResponseTransferDto getTransferById(@PathVariable(name = "transferId") UUID transferId,
                                             @RequestHeader("Authorization") UUID sessionId) {

        if (sessionId == null) {
            throw new AuthorizationException("Пользователь не авторизован");
        }

        return transferService.getTransferById(transferId, sessionId);
    }

}
