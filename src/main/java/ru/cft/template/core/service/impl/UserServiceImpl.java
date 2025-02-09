package ru.cft.template.core.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ru.cft.template.api.dto.user.CreateUserDto;
import ru.cft.template.api.dto.user.ReturnUserDto;
import ru.cft.template.api.dto.user.UpdateUserDto;
import ru.cft.template.api.mapper.UserMapper;
import ru.cft.template.core.exception.AccessRightsException;
import ru.cft.template.core.exception.ValidationException;
import ru.cft.template.core.exception.SessionNotFoundException;
import ru.cft.template.core.exception.UserNotFoundException;
import ru.cft.template.core.repository.SessionRepository;
import ru.cft.template.core.service.UserService;
import ru.cft.template.core.service.WalletService;
import ru.cft.template.core.entity.Session;
import ru.cft.template.core.entity.User;
import ru.cft.template.core.entity.Wallet;
import ru.cft.template.core.repository.UserRepository;

import javax.validation.*;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final Validator validator;

    private final UserRepository userRepository;
    private final WalletService walletService;
    private final SessionRepository sessionRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, WalletService walletService, SessionRepository sessionRepository) {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();

        this.userRepository = userRepository;
        this.walletService = walletService;
        this.sessionRepository = sessionRepository;
    }

    @Override
    public ReturnUserDto createUser(CreateUserDto createUserDto) {
        User user = UserMapper.createUserMapper(createUserDto);
        Wallet wallet = walletService.createWallet();
        user.setWallet(wallet);

        var errors = validator.validate(user);
        if (!errors.isEmpty()) {
            StringBuilder errorMessage = new StringBuilder();

            for (var error : errors) {
                errorMessage.append(error.getMessage()).append(" ");
            }

            throw new ValidationException(errorMessage.toString());
        }

        userRepository.save(user);
        return UserMapper.returnUserMapper(user);
    }

    @Override
    public Object getUserById(UUID userId, UUID sessionId) {
        Session session = sessionRepository.findById(sessionId)
                .orElseThrow(() -> new SessionNotFoundException("Session with ID: " + sessionId + " not found."));

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User with ID: " + userId + " not found."));

        if (!userId.equals(session.getUserId())) {
            return UserMapper.shotResponseMapper(user);
        }

        return UserMapper.getUserMapper(user);
    }

    @Override
    public ResponseEntity<String> updateUser(UUID userId, UpdateUserDto updateUserDto, UUID sessionId) {
        Session session = sessionRepository.findById(sessionId)
                .orElseThrow(() -> new SessionNotFoundException("Session with ID: " + sessionId + " not found."));

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User with ID: " + userId + " not found."));

        if (!userId.equals(session.getUserId())) {
            throw new AccessRightsException("Можно изменить только свой профиль.");
        }

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

        var errors = validator.validate(user);
        if (!errors.isEmpty()) {
            StringBuilder errorMessage = new StringBuilder();

            for (var error : errors) {
                errorMessage.append(error.getMessage()).append(" ");
            }

            throw new ValidationException(errorMessage.toString());
        }

        userRepository.save(user);
        return ResponseEntity.ok("Данные успешно изменены.");
    }
}
