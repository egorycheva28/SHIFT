package ru.cft.template.core.service;

import org.springframework.http.ResponseEntity;
import ru.cft.template.api.dto.user.CreateUserDto;
import ru.cft.template.api.dto.user.GetUserByIdDto;
import ru.cft.template.api.dto.user.UpdateUserDto;

import java.util.UUID;

public interface UserService {
    UUID createUser(CreateUserDto createUserDto);

    GetUserByIdDto getUserById(UUID userId, UUID sessionId);

    ResponseEntity<String> updateUser(UUID userId, UpdateUserDto updateUserDto, UUID sessionId);
}
