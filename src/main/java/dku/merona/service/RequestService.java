package dku.merona.service;

import dku.merona.domain.Post;
import dku.merona.domain.Request;
import dku.merona.dto.RequestRequest;
import dku.merona.dto.RequestResponse;
import dku.merona.repository.PostRepository;
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
    private final PostRepository postRepository;

    public RequestResponse createRequest(RequestRequest requestRequest) {
        Post post = postRepository.findById(requestRequest.getPostId())
                .orElseThrow(() -> new NoSuchElementException("게시물이 없습니다"));

        requestRequest.setPost(post);
        Request request = requestRepository.save(requestRequest.toEntity());
        return new RequestResponse(request);
    }

    public List<RequestResponse> getAllRequestByPost(Long postId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new NoSuchElementException("게시물이 없습니다"));
        return requestRepository.findAllByPost(post)
                .stream().map(RequestResponse::new).collect(Collectors.toList());
    }

    public void deleteRequest(Long requestId) {
        Request request = requestRepository.findById(requestId)
                .orElseThrow(() -> new NoSuchElementException("해당 요청이 없습니다"));
        request.deleteRelation();
        requestRepository.deleteById(requestId);
    }
}
