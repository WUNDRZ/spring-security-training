package com.example.demoSpringSec;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;
    private final UserRepository userRepository;

    public PostDto createPost(PostDto postDto, Long userId) {

        UserEntity user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        PostEntity post = new PostEntity();
        post.setPost(postDto.getPost());
        post.setUser(user);
        postRepository.save(post);
        return postDto;
    }

    public PostDto updatePost(Long id, PostDto postDto, Long userId) {
        PostEntity post = postRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Post not found"));

        if (!post.getUser().getId().equals(userId)) {
            assertHasRole(userId, UserEntity.Role.ADMIN);
        }

        post.setPost(postDto.getPost());
        postRepository.save(post);
        return postDto;
    }

    public PostDto getPost(Long id) {
        PostEntity post = postRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Post not found"));
        return new PostDto(post.getId(), post.getPost(), post.getUser() != null ? post.getUser().getId() : null);
    }

    public List<PostDto> getAllPosts() {
        return postRepository.findAll().stream()
                .map(post -> new PostDto(post.getId(), post.getPost(), post.getUser() != null ? post.getUser().getId() : null))
                .collect(Collectors.toList());
    }

    public void deletePost(Long id, Long userId) {
        PostEntity post = postRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Post not found"));

        assertHasRole(userId, UserEntity.Role.ADMIN);

        postRepository.delete(post);
    }

    public void assertHasRole(Long userId, UserEntity.Role role) {
        UserEntity user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!user.getRole().equals(role)) {
            throw new RuntimeException("You do not have the required permissions");
        }
    }
}
