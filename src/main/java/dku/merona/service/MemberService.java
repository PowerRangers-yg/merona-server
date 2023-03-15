package dku.merona.service;

import dku.merona.domain.Member;
import dku.merona.dto.MemberResponse;
import dku.merona.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
@Transactional
public class MemberService {

    private final MemberRepository memberRepository;

    public Member findMemberById(Long memberId) {
        return memberRepository.findById(memberId)
                .orElseThrow(() -> new NoSuchElementException("회원이 없습니다"));
    }

    public MemberResponse getMember(Long memberId) {
        Member member = findMemberById(memberId);
        return new MemberResponse(member);
    }

    public Member saveMember(Member member) {
        return memberRepository.save(member);
    }
}
