package dku.merona.api;

import dku.merona.config.UserDetailsImpl;
import dku.merona.dto.DeliveryDto;
import dku.merona.service.DeliveryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/deliveries")
@RequiredArgsConstructor
public class DeliveryController {

    private final DeliveryService deliveryService;

    @PostMapping("/new")
    public ResponseEntity<DeliveryDto.Response> createRequest(@AuthenticationPrincipal UserDetailsImpl user,
                                                              @RequestBody DeliveryDto.Request request) {
        return new ResponseEntity<>(deliveryService.createRequest(user, request), HttpStatus.OK);
    }

    @GetMapping("/{postId}")
    public ResponseEntity<List<DeliveryDto.Response>> getAllRequestByPost(@AuthenticationPrincipal UserDetailsImpl user,
                                                                     @PathVariable Long postId) {
        List<DeliveryDto.Response> requestList = deliveryService.getAllRequestByPost(user, postId);
        return new ResponseEntity<>(requestList, HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    public void cancelRequest(@AuthenticationPrincipal UserDetailsImpl user,
                              @PathVariable Long id) {
        deliveryService.cancelRequest(user, id);}

    @DeleteMapping("{id}")
    public void deleteRequest(@AuthenticationPrincipal UserDetailsImpl user,
                              @PathVariable Long id) {
        deliveryService.deleteRequest(user, id);
    }
}
