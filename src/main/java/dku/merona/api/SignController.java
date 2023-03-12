package dku.merona.api;

import dku.merona.dto.MemberResponse;
import dku.merona.dto.SignInDto;
import dku.merona.dto.SignUpDto;
import dku.merona.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class SignController {

    private final MemberService memberService;

    @PostMapping("/sign-up")
    public void signUp(@RequestBody SignUpDto signUpDto) {
        memberService.signUp(signUpDto);
    }

    @PostMapping("/sign-in")
    public ResponseEntity<MemberResponse> signIn(@RequestBody SignInDto signInDto) {
        return new ResponseEntity<>(memberService.signIn(signInDto), HttpStatus.OK);
    }
}
