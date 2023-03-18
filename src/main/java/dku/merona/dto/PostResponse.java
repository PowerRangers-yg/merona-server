package dku.merona.dto;

import dku.merona.constant.Status;
import dku.merona.domain.Member;
import dku.merona.domain.Post;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class PostResponse {

    private Long id;

    private String title;

    private String description;

    private int deliveryPay;

    private int dueTime;

    private String address;

    private Double latitude;

    private Double longitude;

    private String category;

    private Member member;

    private Status status;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    public PostResponse(Post post) {
        this.id = post.getId();
        this.title = post.getTitle();
        this.description = post.getDescription();
        this.deliveryPay = post.getDeliveryPay();
        this.dueTime = post.getDueTime();
        this.address = post.getAddress();
        this.latitude = post.getLatitude();
        this.longitude = post.getLongitude();
        this.member = post.getMember();
        this.category = post.getCategory().getTitle();
        this.status = post.getStatus();
        this.createdAt = post.getCreatedAt();
        this.updatedAt = post.getUpdatedAt();
    }
}
