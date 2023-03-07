package dku.merona.domain;

import javax.persistence.*;

@Entity
public class PostImg {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_img_id")
    private Long id;

    private String imgName;

    private String imgUrl;

    @ManyToOne
    @JoinColumn(name = "post_id")
    private Post post;
}
