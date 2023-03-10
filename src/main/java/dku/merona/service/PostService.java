package dku.merona.service;

import dku.merona.domain.Post;
import dku.merona.dto.PostRequest;
import dku.merona.dto.PostResponse;
import dku.merona.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
@Transactional
public class PostService {

    private final PostRepository postRepository;

    public Post findPostById(Long postId) {
        return postRepository.findById(postId)
                .orElseThrow(() -> new NoSuchElementException("게시물이 없습니다"));
    }

    public PostResponse createPost(PostRequest postRequest) {
        Post post = postRepository.save(postRequest.toEntity());
        return new PostResponse(post);
    }

    public PostResponse getPost(Long postId) {
        Post post = findPostById(postId);
        return new PostResponse(post);
    }

    public PostResponse updatePost(Long postId, PostRequest postRequest) {
        Post post = findPostById(postId);
        post.setPost(postRequest);
        return new PostResponse(post);
    }

    public void deletePost(Long postId) {
        postRepository.deleteById(postId);
    }
}
