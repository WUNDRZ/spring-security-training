package com.example.demoSpringSec;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final UserSessionRepository userSessionRepository;
    private final PasswordEncoder passwordEncoder;

    public UserEntity register(String username, String password, UserEntity.Role role) {
        if (userRepository.findByUsername(username).isPresent()) {
            throw new RuntimeException("User has already been registered");
        }
        UserEntity user = new UserEntity();
        user.setUsername(username);
        user.setPassword(passwordEncoder.encode(password));
        user.setRole(role);
        return userRepository.save(user);
    }

    public String login(String username, String password) {
        UserEntity user = userRepository.findByUsername(username).orElseThrow(() -> new RuntimeException("Invalid username or password"));

        if (!checkPassword(password,user.getPassword())) {
            throw new RuntimeException("Invalid username or password");
        }
        String token = UUID.randomUUID().toString();

        UserSessionEntity session = new UserSessionEntity();
        session.setUser(user);
        session.setToken(token);
        session.setCreatedAt(LocalDateTime.now());
        userSessionRepository.save(session);
        return token;
    }

    public void logout(String token) {
        userSessionRepository.deleteByToken(token);
    }

    public UserEntity getUserByToken(String token){
        return userSessionRepository.findByToken(token)
                .map(UserSessionEntity::getUser)
                .orElseThrow(()->new RuntimeException("Session not found"));
    }

    public boolean checkPassword(String password, String encodedPassword) {
        return passwordEncoder.matches(password, encodedPassword);
    }

}
