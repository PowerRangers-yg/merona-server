package dku.merona.dto;

import dku.merona.constant.RequestStatus;
import dku.merona.domain.Member;
import dku.merona.domain.Post;
import dku.merona.domain.Request;
import lombok.Getter;

@Getter
public class RequestDto {

    private Long id;

    private String currentLocation;

    private int dueTime;

    private Member member;

    private Post post;

    private RequestStatus status;

    public RequestDto(Request request) {
        this.id = request.getId();
        this.currentLocation = request.getCurrentLocation();
        this.dueTime = request.getDueTime();
        this.member = request.getMember();
        this.post = request.getPost();
        this.status = request.getStatus();
    }

    public Request toEntity(){
        return Request.builder()
                .currentLocation(currentLocation)
                .dueTime(dueTime)
                .member(member)
                .post(post)
                .build();
    }
}
