package com.example.demoSpringSec;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SessionAuthService {
    private final UserSessionRepository userSessionRepository;

    public Long checkAndGetUserId(String token) {
        UserSessionEntity session = userSessionRepository.findByToken(token).orElseThrow(() -> new RuntimeException("Invalid session token"));
        return session.getUser().getId();
    }

}
