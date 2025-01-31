package ru.cft.template.api.dto.user;

import java.util.Date;

public record UpdateUserDto(
        String lastName,
        String firstName,
        String middleName,
        Date birthday
) {
}
