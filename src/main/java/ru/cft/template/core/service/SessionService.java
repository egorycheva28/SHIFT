package ru.cft.template.core.service;

import org.springframework.http.ResponseEntity;
import ru.cft.template.api.dto.session.LoginDto;
import ru.cft.template.api.dto.session.GetSessionDto;

import java.util.UUID;

public interface SessionService {
    GetSessionDto createSession(LoginDto loginDto);

    GetSessionDto getSessionById(UUID sessionId);

    ResponseEntity<String> closeSession(UUID sessionId);
}
