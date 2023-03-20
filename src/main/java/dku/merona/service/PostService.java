package dku.merona.service;

import dku.merona.config.UserDetailsImpl;
import dku.merona.constant.Category;
import dku.merona.domain.Member;
import dku.merona.domain.Post;
import dku.merona.dto.PostRequest;
import dku.merona.dto.PostResponse;
import dku.merona.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

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
        postRequest.setPostCategory(Category.ofCode(postRequest.getCategory()));
        Post post = savePost(postRequest.toEntity());
        return new PostResponse(post);
    }

    public PostResponse getPost(Long postId) {
        Post post = findPostById(postId);
        return new PostResponse(post);
    }

    public PostResponse updatePost(Long postId, PostRequest postRequest, UserDetailsImpl user) {
        Post post = findPostById(postId);
        memberService.validateMember(user, post.getMember().getId());
        post.setPost(postRequest);
        return new PostResponse(post);
    }

    public void deletePost(Long postId, UserDetailsImpl user) {
        Post post = findPostById(postId);
        memberService.validateMember(user, post.getMember().getId());
        postRepository.deleteById(postId);
    }

    public List<PostResponse> getAllPost(UserDetailsImpl user) {
        Member member = memberService.findMemberById(user.getId());
        return postRepository.findAllByMemberNot(member)
                .stream().map(PostResponse::new).collect(Collectors.toList());
    }

    private Post savePost(Post post) {
        return postRepository.save(post);
    }
}
