package ru.cft.template.api.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.cft.template.api.dto.user.CreateUserDto;
import ru.cft.template.api.dto.user.ReturnUserDto;
import ru.cft.template.api.dto.user.UpdateUserDto;
import ru.cft.template.core.exception.AuthorizationException;
import ru.cft.template.core.service.UserService;

import java.util.UUID;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping()
    public ReturnUserDto createUser(@RequestBody CreateUserDto createUserDto) {

        return userService.createUser(createUserDto);
    }

    @GetMapping("/{userId}")
    public Object getUserById(@PathVariable(name = "userId") UUID userId,
                              @RequestHeader("Authorization") UUID sessionId) {

        if (sessionId == null) {
            throw new AuthorizationException("Пользователь не авторизован.");
        }

        return userService.getUserById(userId, sessionId);
    }

    @PatchMapping("/{userId}")
    public ResponseEntity<String> updateUser(@PathVariable(name = "userId") UUID userId,
                                             @RequestBody UpdateUserDto updateUserDto,
                                             @RequestHeader("Authorization") UUID sessionId) {

        if (sessionId == null) {
            throw new AuthorizationException("Пользователь не авторизован.");
        }

        return userService.updateUser(userId, updateUserDto, sessionId);
    }
}
