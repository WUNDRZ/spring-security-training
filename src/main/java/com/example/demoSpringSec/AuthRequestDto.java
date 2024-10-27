package com.example.demoSpringSec;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthRequestDto {
    private String username;
    private String password;
    private UserEntity.Role role;

}
