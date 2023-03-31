package dku.merona.api;

import dku.merona.config.UserDetailsImpl;
import dku.merona.constant.Status;
import dku.merona.dto.PostRequest;
import dku.merona.dto.PostResponse;
import dku.merona.service.PostService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/posts")
@RequiredArgsConstructor
@Slf4j
public class PostController {

    private final PostService postService;

    @GetMapping("/")
    public ResponseEntity<List<PostResponse>> getAllPost(@AuthenticationPrincipal UserDetailsImpl user) {
        List<PostResponse> postList = postService.getAllPost(user)
                .stream().filter(b -> b.getStatus().equals(Status.WAITING)).collect(Collectors.toList());
        return new ResponseEntity<>(postList, HttpStatus.OK);
    }

    @PostMapping("/new")
    public ResponseEntity<PostResponse> createPost(@AuthenticationPrincipal UserDetailsImpl user,
                                                   @RequestPart(value = "file", required = false) List<MultipartFile> multipartFiles,
                                                   @RequestPart(value = "req") PostRequest request) {
        return new ResponseEntity<>(postService.createPost(request, user, multipartFiles), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PostResponse> getPost(@PathVariable Long id) {
        return new ResponseEntity<>(postService.getPost(id), HttpStatus.OK);
    }

    @PutMapping( "/{id}")
    public ResponseEntity<PostResponse> updatePost(@AuthenticationPrincipal UserDetailsImpl user,
                                                   @PathVariable Long id, @RequestBody PostRequest request) {
        return new ResponseEntity<>(postService.updatePost(id, request, user), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public void deletePost(@AuthenticationPrincipal UserDetailsImpl user, @PathVariable Long id) {
        postService.deletePost(id, user);
    }
}
