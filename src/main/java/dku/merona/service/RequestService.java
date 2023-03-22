package dku.merona.service;

import dku.merona.domain.Post;
import dku.merona.domain.Request;
import dku.merona.dto.RequestRequest;
import dku.merona.dto.RequestResponse;
import dku.merona.repository.RequestRepository;
import lombok.RequiredArgsConstructor;
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

    public Request findRequestById(Long requestId) {
        return requestRepository.findById(requestId)
                .orElseThrow(() -> new NoSuchElementException("해당 요청이 없습니다"));
    }

    public RequestResponse createRequest(RequestRequest requestRequest) {
        Post post = postService.findPostById(requestRequest.getPostId());

        requestRequest.setPost(post);
        Request request = requestRepository.save(requestRequest.toEntity());
        return new RequestResponse(request);
    }

    public List<RequestResponse> getAllRequestByPost(Long postId) {
        Post post = postService.findPostById(postId);
        return requestRepository.findAllByPost(post)
                .stream().map(RequestResponse::new).collect(Collectors.toList());
    }

    public void cancelRequest(Long requestId) {
        Request request = findRequestById(requestId);
        request.setStatusCancelled();
    }

    public void deleteRequest(Long requestId) {
        Request request = findRequestById(requestId);
        request.deleteRelation();
        requestRepository.deleteById(requestId);
    }
}
