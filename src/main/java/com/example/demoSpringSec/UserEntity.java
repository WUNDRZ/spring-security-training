package com.example.demoSpringSec;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.apache.catalina.Role;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor

public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "username", unique = true, nullable = false)
    private String username;

    @OneToMany(mappedBy = "user")
    private List<PostEntity> posts = new ArrayList<>();

    @Column(name = "password", nullable = false)
    public String password;

    @Column(name = "role", nullable = false)
    @Enumerated(EnumType.STRING)
    public Role role;

    public enum Role {
        ADMIN,
        USER
    }
}



