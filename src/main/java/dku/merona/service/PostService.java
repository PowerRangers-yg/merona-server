package dku.merona.service;

import dku.merona.config.UserDetailsImpl;
import dku.merona.domain.Member;
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
    private final MemberService memberService;

    public Post findPostById(Long postId) {
        return postRepository.findById(postId)
                .orElseThrow(() -> new NoSuchElementException("게시물이 없습니다"));
    }

    public PostResponse createPost(PostRequest postRequest, UserDetailsImpl user) {
        Member member = memberService.findMemberById(user.getId());
        postRequest.setMember(member);
        Post post = postRepository.save(postRequest.toEntity());
        return new PostResponse(post);
    }

    public PostResponse getPost(Long postId) {
        Post post = findPostById(postId);
        return new PostResponse(post);
    }

    public PostResponse updatePost(Long postId, PostRequest postRequest, UserDetailsImpl user) {
        Post post = findPostById(postId);
        isEqualMember(user, post.getMember());
        post.setPost(postRequest);
        return new PostResponse(post);
    }

    public void deletePost(Long postId, UserDetailsImpl user) {
        Post post = findPostById(postId);
        isEqualMember(user, post.getMember());
        postRepository.deleteById(postId);
    }

    public boolean isEqualMember(UserDetailsImpl user, Member member) {
        if (!user.getId().equals(member.getId())) {
            throw new IllegalStateException("잘못된 요청입니다");
        }
        return true;
    }
}
