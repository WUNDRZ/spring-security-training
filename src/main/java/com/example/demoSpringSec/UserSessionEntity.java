package com.example.demoSpringSec;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.function.Function;

@Entity
@Table(name = "user_sessions")
@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
public class UserSessionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @PrimaryKeyJoinColumn
    private Long id;

    private String token;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

}
