package dku.merona.service;

import dku.merona.config.UserDetailsImpl;
import dku.merona.domain.Member;
import dku.merona.domain.Post;
import dku.merona.domain.Request;
import dku.merona.dto.RequestRequest;
import dku.merona.dto.RequestResponse;
import dku.merona.repository.RequestRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class RequestService {

    private final RequestRepository requestRepository;
    private final PostService postService;
    private final MemberService memberService;

    public Request findRequestById(Long requestId) {
        return requestRepository.findById(requestId)
                .orElseThrow(() -> new NoSuchElementException("해당 요청이 없습니다"));
    }

    public RequestResponse createRequest(UserDetailsImpl user, RequestRequest request) {
        Post post = postService.findPostById(request.getPostId());
        Member member = user.getMember();

        request.setMemberAndPost(member, post);
        Request newRequest = requestRepository.save(request.toEntity());
        return new RequestResponse(newRequest);
    }

    public List<RequestResponse> getAllRequestByPost(UserDetailsImpl user, Long postId) {
        Post post = postService.findPostById(postId);
        memberService.validateMember(user.getId(), post.getMember());
        return requestRepository.findAllByPost(post)
                .stream().map(RequestResponse::new).collect(Collectors.toList());
    }

    public void cancelRequest(UserDetailsImpl user, Long requestId) {
        Request request = findRequestById(requestId);
        memberService.validateMember(user.getId(), request.getMember());
        request.setStatusCancelled();
    }

    public void deleteRequest(UserDetailsImpl user, Long requestId) {
        Request request = findRequestById(requestId);
        memberService.validateMember(user.getId(), request.getMember());
        request.deleteRelation();
        requestRepository.deleteById(requestId);
    }
}
