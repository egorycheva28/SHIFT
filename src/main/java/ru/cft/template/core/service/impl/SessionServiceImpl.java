package ru.cft.template.core.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ru.cft.template.api.dto.session.LoginDto;
import ru.cft.template.api.dto.session.GetSessionDto;
import ru.cft.template.api.mapper.SessionMapper;
import ru.cft.template.core.exception.AccessRightsException;
import ru.cft.template.core.exception.SessionNotFoundException;
import ru.cft.template.core.exception.UserNotFoundException;
import ru.cft.template.core.service.SessionService;
import ru.cft.template.entity.Session;
import ru.cft.template.entity.User;
import ru.cft.template.core.repository.SessionRepository;
import ru.cft.template.core.repository.UserRepository;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class SessionServiceImpl implements SessionService {

    private final UserRepository userRepository;
    private final SessionRepository sessionRepository;

    @Override
    public GetSessionDto createSession(LoginDto loginDto) {
        User user = userRepository.findById(loginDto.userId())
                .orElse(null);

        if (user == null){
            throw new UserNotFoundException("Invalid login details");
        }

        LocalDateTime currentDateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedDateTime = currentDateTime.format(formatter);

        Session session = new Session();
        session.setUserId(user.getId());
        //session.setExpirationTime(Date.parse(formattedDateTime));
        session.setActive(true);
        sessionRepository.save(session);

        return SessionMapper.getSessionMapper(session);
    }

    @Override
    public GetSessionDto getSessionById(UUID sessionId) {
        Session session = sessionRepository.findById(sessionId)
                .orElseThrow(() -> new SessionNotFoundException("Session with ID: " + sessionId + " not found"));

        //проверка, что это твоя активная сессия
        /* if (!session.getUserId().equals(currentUserId)) {
            throw new AccessRightsException("Можно выйти только из своей сессии");
        }*/

        return SessionMapper.getSessionMapper(session);
    }

    @Override
    public ResponseEntity<String> closeSession(UUID sessionId) {
        Session session = sessionRepository.findById(sessionId)
                .orElseThrow(() -> new SessionNotFoundException("Session with ID: " + sessionId + " not found"));

        //проверка, что это твоя активная сессия
        /* if (!session.getUserId().equals(currentUserId)) {
            throw new AccessRightsException("Можно выйти только из своей сессии");
        }*/

        sessionRepository.deleteById(sessionId);
        return ResponseEntity.ok("Вы вышли из системы");
    }
}
