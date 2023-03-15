package dku.merona.service;

import dku.merona.config.security.JwtTokenProvider;
import dku.merona.domain.Member;
import dku.merona.dto.MemberResponse;
import dku.merona.dto.SignInDto;
import dku.merona.dto.SignUpDto;
import dku.merona.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
@Transactional
public class MemberService {

    private final JwtTokenProvider jwtTokenProvider;
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    public void signUp(SignUpDto signUpDto) {
        Member member = signUpDto.toEntity();
        member.encodePassword(passwordEncoder);
        saveMember(member);
    }

    public MemberResponse signIn(SignInDto signInDto) {
        Member member = memberRepository.findByEmail(signInDto.getEmail());

        if (!passwordEncoder.matches(signInDto.getPassword(), member.getPassword())) {
            throw new UsernameNotFoundException("이메일 또는 비밀번호를 확인해주세요");
        }
        return new MemberResponse(member, jwtTokenProvider.createToken(member.getEmail()));
    }

    public Member findMemberById(Long memberId) {
        return memberRepository.findById(memberId)
                .orElseThrow(() -> new NoSuchElementException("회원이 없습니다"));
    }

    public MemberResponse getMember(Long memberId) {
        Member member = findMemberById(memberId);
        return new MemberResponse(member);
    }

    private Member saveMember(Member member) {
        return memberRepository.save(member);
    }
}
