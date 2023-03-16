package dku.merona.api;

import dku.merona.config.UserDetailsImpl;
import dku.merona.dto.MemberRequest;
import dku.merona.dto.MemberResponse;
import dku.merona.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/members")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/{id}")
    public ResponseEntity<MemberResponse> getMember(@PathVariable Long id) {
        return new ResponseEntity<>(memberService.getMember(id), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MemberResponse> updateMember(@AuthenticationPrincipal UserDetailsImpl user,
                                                       @PathVariable Long id, @RequestBody MemberRequest request) {
        return new ResponseEntity<>(memberService.updateMember(id, request, user), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public void deleteMember(@AuthenticationPrincipal UserDetailsImpl user, @PathVariable Long id) {
        memberService.deleteMember(id, user);
    }
}
