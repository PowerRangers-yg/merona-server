package dku.merona.dto;

import dku.merona.constant.RequestStatus;
import dku.merona.domain.Member;
import dku.merona.domain.Request;
import lombok.Getter;

@Getter
public class RequestResponse {

    private Long id;

    private String currentLocation;

    private int dueTime;

    private Member member;

    private RequestStatus status;

    public RequestResponse(Request request) {
        this.id = request.getId();
        this.currentLocation = request.getCurrentLocation();
        this.dueTime = request.getDueTime();
        this.member = request.getMember();
        this.status = request.getStatus();
    }
}
