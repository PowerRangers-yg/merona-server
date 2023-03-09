package dku.merona.dto;

import dku.merona.constant.Status;
import dku.merona.domain.Member;
import dku.merona.domain.Post;
import lombok.Getter;

@Getter
public class PostResponse {

    private Long id;

    private String title;

    private String description;

    private int deliveryPay;

    private int dueTime;

    private String arrivalLocation;

    private Member member;

    private Status status;

    public PostResponse(Post post) {
        this.id = post.getId();
        this.title = post.getTitle();
        this.description = post.getDescription();
        this.deliveryPay = post.getDeliveryPay();
        this.dueTime = post.getDueTime();
        this.arrivalLocation = post.getArrivalLocation();
        this.member = post.getMember();
        this.status = post.getStatus();
    }
}
