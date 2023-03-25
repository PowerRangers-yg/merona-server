package dku.merona.api;

import dku.merona.config.UserDetailsImpl;
import dku.merona.dto.RequestRequest;
import dku.merona.dto.RequestResponse;
import dku.merona.service.RequestService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/requests")
@RequiredArgsConstructor
public class RequestController {

    private final RequestService requestService;

    @PostMapping("/new")
    public ResponseEntity<RequestResponse> createRequest(@AuthenticationPrincipal UserDetailsImpl user,
                                                         @RequestBody RequestRequest request) {
        return new ResponseEntity<>(requestService.createRequest(user, request), HttpStatus.OK);
    }

    @GetMapping("/{postId}")
    public ResponseEntity<List<RequestResponse>> getAllRequestByPost(@AuthenticationPrincipal UserDetailsImpl user,
                                                                     @PathVariable Long postId) {
        List<RequestResponse> requestList = requestService.getAllRequestByPost(user, postId);
        return new ResponseEntity<>(requestList, HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    public void cancelRequest(@AuthenticationPrincipal UserDetailsImpl user,
                              @PathVariable Long id) {requestService.cancelRequest(user, id);}

    @DeleteMapping("{id}")
    public void deleteRequest(@AuthenticationPrincipal UserDetailsImpl user,
                              @PathVariable Long id) {
        requestService.deleteRequest(user, id);
    }
}
