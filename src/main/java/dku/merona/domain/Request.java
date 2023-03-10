package dku.merona.domain;

import dku.merona.constant.RequestStatus;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
public class Request extends BaseTime{

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "request_id")
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
    public Request(String currentLocation, int dueTime, Member member, Post post) {
        this.currentLocation = currentLocation;
        this.dueTime = dueTime;
        this.member = member;
        this.post = post;
        this.status = RequestStatus.WAITING;
    }
}
