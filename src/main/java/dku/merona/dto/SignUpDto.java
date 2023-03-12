package dku.merona.dto;

import dku.merona.domain.Member;
import lombok.Getter;

@Getter
public class SignUpDto {

    private String email;

    private String username;

    private String password;

    public Member toEntity() {
        return Member.builder()
                .email(email)
                .username(username)
                .password(password)
                .build();
    }
}
