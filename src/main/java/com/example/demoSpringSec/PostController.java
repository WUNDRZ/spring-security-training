package com.example.demoSpringSec;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/posts")

public class PostController {
    private final PostService postService;
    private final UserService userService;
    private final SessionAuthService sessionAuthService;

    @PostMapping
    public PostDto createPost(@RequestHeader("Authorization") String token, @RequestBody PostDto postDto) {
        Long userId = sessionAuthService.checkAndGetUserId(token);
        return postService.createPost(postDto, userId);
    }

    @PutMapping("/{id}")
    public PostDto updatePost(@RequestHeader("Authorization") String token, @PathVariable Long id, @RequestBody PostDto postDto) {
        Long userId = sessionAuthService.checkAndGetUserId(token);
        return postService.updatePost(id, postDto, userId);
    }

    @GetMapping("/{id}")
    public PostDto getPost(@PathVariable Long id, @RequestHeader("Authorization") String token) {
        sessionAuthService.checkAndGetUserId(token);
        return postService.getPost(id);
    }

    @GetMapping
    public List<PostDto> getAllPosts(@RequestHeader("Authorization") String token) {
        sessionAuthService.checkAndGetUserId(token);
        return postService.getAllPosts();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePost(@RequestHeader("Authorization") String token, @PathVariable Long id) {
        Long userId = sessionAuthService.checkAndGetUserId(token);
        postService.deletePost(id, userId);
        return ResponseEntity.accepted().body("succefully");
    }
}