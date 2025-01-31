package ru.cft.template.api.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.cft.template.api.dto.session.LoginDto;
import ru.cft.template.api.dto.session.GetSessionDto;
import ru.cft.template.core.service.SessionService;

import java.util.UUID;

@RestController
@RequestMapping("/sessions")
@RequiredArgsConstructor
public class SessionController {

    private final SessionService sessionService;

    @PostMapping
    public GetSessionDto createSession(@RequestBody LoginDto loginDto){

        return sessionService.createSession(loginDto);
    }

    @GetMapping("/{sessionId}")
    public GetSessionDto getSessionById(@PathVariable(name = "sessionId") UUID sessionId){

        return sessionService.getSessionById(sessionId);
    }

    @DeleteMapping("/{sessionId}")
    public ResponseEntity<String> closeSession(@PathVariable(name = "sessionId") UUID sessionId){

        return sessionService.closeSession(sessionId);
    }
}
