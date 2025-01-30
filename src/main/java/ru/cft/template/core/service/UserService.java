package ru.cft.template.core.service;

import org.springframework.http.ResponseEntity;
import ru.cft.template.api.dto.CreateUserDto;
import ru.cft.template.api.dto.GetUserByIdDto;
import ru.cft.template.api.dto.UpdateUserDto;

import java.util.UUID;

public interface UserService {
    UUID createUser(CreateUserDto createUserDto);

    GetUserByIdDto getUserById(UUID userId);

    ResponseEntity<String> updateUser(UUID userId, UpdateUserDto updateUserDto);
}
