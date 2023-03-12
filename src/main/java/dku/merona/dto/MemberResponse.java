package dku.merona.dto;

import dku.merona.domain.Member;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class MemberResponse {

    private Long id;

    private String email;

    private String username;

    private String token;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    public MemberResponse(Member member, String token) {
        this.id = member.getId();
        this.email = member.getEmail();
        this.username = member.getUsername();
        this.token = token;
        this.createdAt = member.getCreatedAt();
        this.updatedAt = member.getUpdatedAt();
    }
}
