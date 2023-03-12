package dku.merona.service;

import dku.merona.config.security.JwtTokenProvider;
import dku.merona.domain.Member;
import dku.merona.dto.MemberResponse;
import dku.merona.dto.SignInDto;
import dku.merona.dto.SignUpDto;
import dku.merona.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class MemberService {

    private final JwtTokenProvider jwtTokenProvider;
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    public void signUp(SignUpDto signUpDto) {
        Member member = signUpDto.toEntity();
        member.encodePassword(passwordEncoder);
        memberRepository.save(member);
    }

    public MemberResponse signIn(SignInDto signInDto) {
        Member member = memberRepository.findByEmail(signInDto.getEmail());

        if (!passwordEncoder.matches(signInDto.getPassword(), member.getPassword())) {
            throw new UsernameNotFoundException("이메일 또는 비밀번호를 확인해주세요");
        }

        return new MemberResponse(member, jwtTokenProvider.createToken(member.getEmail()));
    }
}
