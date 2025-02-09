package ru.cft.template.api.dto.user;

import java.util.Date;

public record ShotResponseDto(
        String lastName,
        String firstName,
        String middleName,
        Date birthday
) {
}
