package ru.cft.template.api.dto.wallet;

import java.util.UUID;

public record RequestWalletDto(
        UUID sessionId
) {
}
