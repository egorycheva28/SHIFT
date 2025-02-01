package ru.cft.template.core.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ru.cft.template.api.dto.session.LoginDto;
import ru.cft.template.api.dto.session.GetSessionDto;
import ru.cft.template.api.mapper.SessionMapper;
import ru.cft.template.core.exception.LoginException;
import ru.cft.template.core.exception.SessionNotFoundException;
import ru.cft.template.core.exception.UserNotFoundException;
import ru.cft.template.core.service.SessionService;
import ru.cft.template.core.entity.Session;
import ru.cft.template.core.entity.User;
import ru.cft.template.core.repository.SessionRepository;
import ru.cft.template.core.repository.UserRepository;

import java.util.Date;
import java.util.Objects;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class SessionServiceImpl implements SessionService {

    private final UserRepository userRepository;
    private final SessionRepository sessionRepository;

    @Override
    public GetSessionDto createSession(LoginDto loginDto) {
        User user = userRepository.findById(loginDto.userId())
                .orElseThrow(() -> new UserNotFoundException("User with ID: " + loginDto.userId() + " not found"));

        if (!Objects.equals(user.getPassword(), loginDto.password())){
            throw new LoginException("Неправильные входные данные");
        }

        Session session = new Session();
        session.setUserId(user.getId());
        session.setExpirationTime(new Date());
        session.setActive(true);
        sessionRepository.save(session);

        return SessionMapper.getSessionMapper(session);
    }

    @Override
    public GetSessionDto getSessionById(UUID sessionId) {
        Session session = sessionRepository.findById(sessionId)
                .orElseThrow(() -> new SessionNotFoundException("Session with ID: " + sessionId + " not found"));

        return SessionMapper.getSessionMapper(session);
    }

    @Override
    public ResponseEntity<String> closeSession(UUID sessionId) {
        Session session = sessionRepository.findById(sessionId)
                .orElseThrow(() -> new SessionNotFoundException("Session with ID: " + sessionId + " not found"));

        sessionRepository.deleteById(sessionId);
        return ResponseEntity.ok("Вы вышли из системы");
    }
}
