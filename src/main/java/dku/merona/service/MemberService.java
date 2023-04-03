package dku.merona.service;

import dku.merona.config.UserDetailsImpl;
import dku.merona.domain.Member;
import dku.merona.dto.MemberRequest;
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

    public Member saveMember(Member member) {
        return memberRepository.save(member);
    }

    public void validateMember(UserDetailsImpl user, Long memberId) {
        if (!user.getId().equals(memberId)) {
            throw new IllegalStateException("잘못된 요청입니다");
        }
    }

    public void validateMember(Long memberId, Member member) {
        if (!member.getId().equals(memberId)) {
            throw new IllegalStateException("잘못된 요청입니다");
        }
    }

    public boolean isDuplicateUsername(String username) {
        return memberRepository.findByUsername(username).isPresent();
    }

    public MemberResponse getMember(Long memberId) {
        Member member = findMemberById(memberId);
        return new MemberResponse(member);
    }

    public MemberResponse updateMember(Long memberId, MemberRequest request, UserDetailsImpl user) {
        Member member = findMemberById(memberId);
        validateMember(user, memberId);
        member.setMember(request.getUsername());
        return new MemberResponse(member);
    }

    public void deleteMember(Long memberId, UserDetailsImpl user) {
        validateMember(user, memberId);
        memberRepository.deleteById(memberId);
    }
}
