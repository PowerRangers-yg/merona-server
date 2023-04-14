package dku.merona.service;

import dku.merona.config.UserDetailsImpl;
import dku.merona.constant.Category;
import dku.merona.domain.Post;
import dku.merona.dto.PostDto;
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

    public PostDto.Response createPost(PostDto.Request request,
                              UserDetailsImpl user,
                              List<MultipartFile> multipartFiles) {
        request.setMember(user.getMember());
        request.setPostCategory(Category.ofCode(request.getCategory()));
        Post post = savePost(request.toEntity());
        postImgService.createPostImgList(multipartFiles, post);
        return new PostDto.Response(post);
    }

    public PostDto.Response getPost(Long postId) {
        Post post = findPostById(postId);
        return new PostDto.Response(post);
    }

    public PostDto.Response updatePost(Long postId, PostDto.Request request, UserDetailsImpl user) {
        Post post = findPostById(postId);
        memberService.validateMember(user.getId(), post.getMember());
        post.setPost(request);
        return new PostDto.Response(post);
    }

    public void deletePost(Long postId, UserDetailsImpl user) {
        Post post = findPostById(postId);
        memberService.validateMember(user.getId(), post.getMember());
        postImgService.deletePostImgList(post);
        postRepository.deleteById(postId);
    }

    public List<PostDto.Response> getAllPost(UserDetailsImpl user) {
        return postRepository.findAllByMemberNot(user.getMember())
                .stream().map(PostDto.Response::new).collect(Collectors.toList());
    }

    private Post savePost(Post post) {
        return postRepository.save(post);
    }
}
