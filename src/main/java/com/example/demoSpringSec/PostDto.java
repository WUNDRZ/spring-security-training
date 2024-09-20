package com.example.demoSpringSec;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.security.PrivateKey;

@Getter
@Setter
@AllArgsConstructor
public class PostDto {
    private Long id;
    private String post;
    private Long userId;
}
