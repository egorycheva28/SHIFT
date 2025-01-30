package ru.cft.template.api.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.cft.template.api.dto.CreateUserDto;
import ru.cft.template.api.dto.UpdateUserDto;
import ru.cft.template.api.dto.GetUserByIdDto;
import ru.cft.template.core.service.UserService;

import java.util.UUID;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping()
    public UUID createUser(@RequestBody CreateUserDto createUserDto) {

        return userService.createUser(createUserDto);
    }

    @GetMapping("/{userId}")
    public GetUserByIdDto getUserById(@PathVariable(name = "userId") UUID userId) {

        return userService.getUserById(userId);
    }

    @PatchMapping("/{userId}")
    public ResponseEntity<String> updateUser(@PathVariable(name = "userId") UUID userId, @RequestBody UpdateUserDto updateUserDto) {

        return userService.updateUser(userId, updateUserDto);

    }
}
