package com.example.demoSpringSec;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UserSessionRepository extends JpaRepository<UserSessionEntity, Long> {
    Optional<UserSessionEntity> findByToken(String token);

    void deleteByToken(String token);
}

