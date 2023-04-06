package dku.merona.service;

import dku.merona.config.UserDetailsImpl;
import dku.merona.constant.Category;
import dku.merona.domain.Post;
import dku.merona.dto.PostRequest;
import dku.merona.dto.PostResponse;
import dku.merona.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class PostService {

    private final PostRepository postRepository;
    private final MemberService memberService;
    private final PostImgService postImgService;

    public Post findPostById(Long postId) {
        return postRepository.findById(postId)
                .orElseThrow(() -> new NoSuchElementException("게시물이 없습니다"));
    }

    public PostResponse createPost(PostRequest postRequest,
                                   UserDetailsImpl user,
                                   List<MultipartFile> multipartFiles) {
        postRequest.setMember(user.getMember());
        postRequest.setPostCategory(Category.ofCode(postRequest.getCategory()));
        Post post = savePost(postRequest.toEntity());
        postImgService.createPostImgList(multipartFiles, post);
        return new PostResponse(post);
    }

    public PostResponse getPost(Long postId) {
        Post post = findPostById(postId);
        return new PostResponse(post);
    }

    public PostResponse updatePost(Long postId, PostRequest postRequest, UserDetailsImpl user) {
        Post post = findPostById(postId);
        memberService.validateMember(user.getId(), post.getMember());
        post.setPost(postRequest);
        return new PostResponse(post);
    }

    public void deletePost(Long postId, UserDetailsImpl user) {
        Post post = findPostById(postId);
        memberService.validateMember(user.getId(), post.getMember());
        postImgService.deletePostImgList(post);
        postRepository.deleteById(postId);
    }

    public List<PostResponse> getAllPost(UserDetailsImpl user) {
        return postRepository.findAllByMemberNot(user.getMember())
                .stream().map(PostResponse::new).collect(Collectors.toList());
    }

    private Post savePost(Post post) {
        return postRepository.save(post);
    }
}
