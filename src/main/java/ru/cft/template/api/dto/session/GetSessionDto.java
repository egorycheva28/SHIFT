package ru.cft.template.api.dto.session;

import java.util.Date;
import java.util.UUID;

public record GetSessionDto(
        UUID sessionId,
        UUID userId,
        Date expirationTime,
        Boolean active
) {
}
