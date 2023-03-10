package dku.merona.service;

import dku.merona.domain.Request;
import dku.merona.dto.RequestDto;
import dku.merona.repository.RequestRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class RequestService {

    private final RequestRepository requestRepository;

    public RequestDto createRequest(RequestDto requestDto) {
        Request request = requestRepository.save(requestDto.toEntity());
        return new RequestDto(request);
    }
}
