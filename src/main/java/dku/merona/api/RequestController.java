package dku.merona.api;

import dku.merona.dto.RequestRequest;
import dku.merona.dto.RequestResponse;
import dku.merona.service.RequestService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/requests")
@RequiredArgsConstructor
public class RequestController {

    private final RequestService requestService;

    @PostMapping("/new")
    public ResponseEntity<RequestResponse> createRequest(@RequestBody RequestRequest request) {
        return new ResponseEntity<>(requestService.createRequest(request), HttpStatus.OK);
    }

    @GetMapping("/{postId}")
    public ResponseEntity<List<RequestResponse>> getAllRequestByPost(@PathVariable Long postId) {
        List<RequestResponse> requestList = requestService.getAllRequestByPost(postId);
        return new ResponseEntity<>(requestList, HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public void deleteRequest(@PathVariable Long id) {
        requestService.deleteRequest(id);
    }
}
