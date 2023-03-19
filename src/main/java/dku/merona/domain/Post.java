package dku.merona.domain;

import dku.merona.constant.Category;
import dku.merona.constant.Status;
import dku.merona.dto.PostRequest;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
public class Post extends BaseTime{

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_id")
    private Long id;

    private String title;

    private String description;

    private int deliveryPay;

    private int dueTime;

    private String address;

    private Double latitude;

    private Double longitude;

    @Enumerated(EnumType.STRING)
    private Category category;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "member_id")
    private Member member;

    @Enumerated(EnumType.STRING)
    private Status status;

    @Builder
    public Post(String title,
                String description,
                int deliveryPay,
                int dueTime,
                String address,
                double latitude,
                double longitude,
                Category category,
                Member member) {
        this.title = title;
        this.description = description;
        this.deliveryPay = deliveryPay;
        this.dueTime = dueTime;
        this.address = address;
        this.latitude = latitude;
        this.longitude = longitude;
        this.category = category;
        this.member = member;
        this.status = Status.WAITING;
    }

    public void setPost(PostRequest postRequest) {
        this.title = postRequest.getTitle();
        this.description = postRequest.getDescription();
        this.deliveryPay = postRequest.getDeliveryPay();
        this.dueTime = postRequest.getDueTime();
    }

    public void setStatusMatching() {
        this.status = Status.MATCHING;
    }

    public void setStatusWaiting() {
        this.status = Status.WAITING;
    }
}
