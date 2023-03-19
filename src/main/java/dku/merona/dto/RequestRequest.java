package dku.merona.dto;

import dku.merona.constant.RequestStatus;
import dku.merona.domain.Member;
import dku.merona.domain.Post;
import dku.merona.domain.Request;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
public class RequestRequest {

    private String currentLocation;

    private int dueTime;

    private Member member;

    private Post post;

    private Long postId;

    private RequestStatus status;

    public Request toEntity(){
        return Request.builder()
                .currentLocation(currentLocation)
                .dueTime(dueTime)
                .member(member)
                .post(post)
                .build();
    }
}
