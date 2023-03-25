package dku.merona.dto;

import dku.merona.constant.RequestStatus;
import dku.merona.domain.Member;
import dku.merona.domain.Post;
import dku.merona.domain.Request;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class RequestResponse {

    private Long id;

    private String currentLocation;

    private int dueTime;

    private Member member;

    private Post post;

    private RequestStatus status;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    public RequestResponse(Request request) {
        this.id = request.getId();
        this.currentLocation = request.getCurrentLocation();
        this.dueTime = request.getDueTime();
        this.member = request.getMember();
        this.post = request.getPost();
        this.status = request.getStatus();
        this.createdAt = request.getCreatedAt();
        this.updatedAt = request.getUpdatedAt();
    }
}
