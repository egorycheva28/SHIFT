package ru.cft.template.api.dto.user;

import java.util.Date;

public record GetUserByIdDto(
        String lastName,
        String firstName,
        String middleName,
        Long phone,
        String email,
        Date birthday
) {
}
