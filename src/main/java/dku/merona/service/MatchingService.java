package dku.merona.service;

import dku.merona.domain.Matching;
import dku.merona.dto.MatchingRequest;
import dku.merona.repository.MatchingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
@Transactional
public class MatchingService {

    private final MatchingRepository matchingRepository;
    private final PostService postService;
    private final RequestService requestService;

    public Matching findMatchingById(Long matchingId) {
        return matchingRepository.findById(matchingId)
                .orElseThrow(() -> new NoSuchElementException("매칭이 없습니다"));
    }

    public void newMatching(MatchingRequest request) {
        request.setPost(postService.findPostById(request.getPostId()));
        request.setRequest(requestService.findRequestById(request.getRequestId()));

        Matching matching = request.toEntity();
        matching.setNewStatus();
        matchingRepository.save(matching);
    }

    public void cancelMatching(Long matchingId) {
        Matching matching = findMatchingById(matchingId);
        matching.setCancelStatus();
        deleteMatching(matchingId);
    }

    private void deleteMatching(Long matchingId) {
        matchingRepository.deleteById(matchingId);
    }
}
