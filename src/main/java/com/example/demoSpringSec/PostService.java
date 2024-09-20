package com.example.demoSpringSec;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;
    private final UserRepository userRepository;

    public PostDto createPost(PostDto postDto) {
        PostEntity post = new PostEntity();
        post.setPost(postDto.getPost());
        postRepository.save(post);
        return postDto;
    }

    public PostDto updatePost(Long id, PostDto postDto) {
        PostEntity post = postRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Post not found"));
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

    public void deletePost(Long id) {
        PostEntity post = postRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Post not found"));
        postRepository.delete(post);
    }
}
