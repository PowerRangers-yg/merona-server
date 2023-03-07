package dku.merona.domain;

import dku.merona.constant.Status;

import javax.persistence.*;

@Entity
public class Post extends BaseTime{

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_id")
    private Long id;

    private String title;

    private String description;

    private int deliveryPay;

    private int dueTime;

    private String arrivalLocation;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @Enumerated(EnumType.STRING)
    private Status status;
}
