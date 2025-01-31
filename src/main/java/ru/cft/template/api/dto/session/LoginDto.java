package ru.cft.template.api.dto.session;

import java.util.UUID;

public record LoginDto(
        UUID userId,
        String password
) {
}
