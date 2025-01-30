package ru.cft.template.core.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ru.cft.template.api.dto.CreateUserDto;
import ru.cft.template.api.dto.GetUserByIdDto;
import ru.cft.template.api.dto.UpdateUserDto;
import ru.cft.template.api.mapper.UserMapper;
import ru.cft.template.core.exception.UserNotFoundException;
import ru.cft.template.core.service.UserService;
import ru.cft.template.entity.User;
import ru.cft.template.repository.UserRepository;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public UUID createUser(CreateUserDto createUserDto) {
        User user = UserMapper.createUserMapper(createUserDto);
        userRepository.save(user);
        //UUID id = user.getId();
        //return null;
        return user.getId();
    }

    @Override
    public GetUserByIdDto getUserById(UUID userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User with ID: " + userId + " not found"));
        return UserMapper.getUserMapper(user);
    }

    @Override
    public ResponseEntity<String> updateUser(UUID userId, UpdateUserDto updateUserDto) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User with ID: " + userId + " not found"));

        if (updateUserDto.lastName() != null) {
            user.setLastName(updateUserDto.lastName());
        }
        if (updateUserDto.firstName() != null) {
            user.setFirstName(updateUserDto.firstName());
        }
        if (updateUserDto.middleName() != null) {
            user.setMiddleName(updateUserDto.middleName());
        }
        if (updateUserDto.birthday() != null) {
            user.setBirthday(updateUserDto.birthday());
        }

        userRepository.save(user);
        return ResponseEntity.ok("Данные успешно изменены");
    }
}
