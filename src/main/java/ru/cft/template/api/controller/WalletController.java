package ru.cft.template.api.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.cft.template.api.dto.wallet.GetWalletDto;
import ru.cft.template.core.service.WalletService;

import java.util.UUID;

@RestController
@RequestMapping("/wallets")
@RequiredArgsConstructor
public class WalletController {

    private final WalletService walletService;

    @GetMapping("/{userId}")
    public GetWalletDto getWalletByUserId(@PathVariable(name = "userId") UUID userId,
                                          @RequestHeader("Authorization") UUID sessionId) {

        return walletService.getWalletByUserId(userId, sessionId);
    }
}
