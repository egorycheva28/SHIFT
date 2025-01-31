package ru.cft.template.api.dto.session;

import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

public record GetSessionDto(
        UUID Id,
        UUID userId,
        LocalDateTime expirationTime,
        Boolean active
) {
}
