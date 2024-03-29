package dku.merona.domain;

import dku.merona.constant.RequestStatus;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
public class Delivery extends BaseTime{

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "delivery_id")
    private Long id;

    private String currentLocation;

    private int dueTime;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne
    @JoinColumn(name = "post_id")
    private Post post;

    @Enumerated(EnumType.STRING)
    private RequestStatus status;

    @Builder
    public Delivery(String currentLocation, int dueTime, Member member, Post post) {
        this.currentLocation = currentLocation;
        this.dueTime = dueTime;
        this.member = member;
        this.post = post;
        this.status = RequestStatus.WAITING;
    }

    public void deleteRelation() {
        this.member = null;
        this.post = null;
    }

    public void setStatusMatching() {
        this.status = RequestStatus.MATCHING;
    }

    public void setStatusCancelled() {
        this.status = RequestStatus.CANCELLED;
    }

}
