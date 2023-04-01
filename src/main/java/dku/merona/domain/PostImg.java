package dku.merona.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity @Getter
@NoArgsConstructor
public class PostImg extends BaseTime{

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_img_id")
    private Long id;

    private String imgName;

    private String imgUrl;

    @ManyToOne
    @JoinColumn(name = "post_id")
    private Post post;

    public PostImg(String imgName, String imgUrl, Post post) {
        this.imgName = imgName;
        this.imgUrl = imgUrl;
        this.post = post;
    }
}
