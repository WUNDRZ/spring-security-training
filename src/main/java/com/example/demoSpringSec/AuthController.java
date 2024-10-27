package com.example.demoSpringSec;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {
    private final UserService userService;

    @PostMapping("/register")
    public String register(@RequestBody AuthRequestDto authRequest) {
        userService.register(authRequest.getUsername(), authRequest.getPassword(), authRequest.getRole());
        return "User has been registered";
    }
    @PostMapping("/login")
    public String login(@RequestBody AuthRequestDto authRequest) {
        return userService.login(authRequest.getUsername(), authRequest.getPassword());
    }
    @PostMapping("/logout")
    public String logout(@RequestBody String token){
        userService.logout(token);
        return "Logged out successfully";
    }
}