package dku.merona.dto;

import dku.merona.domain.Post;
import dku.merona.domain.PostImg;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class PostImgDto {

    private Long id;

    private String imgName;

    private String imgUrl;

    private Post post;

    public PostImgDto(String imgName, String imgUrl, Post post) {
        this.imgName = imgName;
        this.imgUrl = imgUrl;
        this.post = post;
    }

    public PostImgDto(PostImg postImg) {
        this.id = postImg.getId();
        this.imgName = postImg.getImgName();
        this.imgUrl = postImg.getImgUrl();
        this.post = postImg.getPost();
    }

    public PostImg toEntity() {
        return new PostImg(imgName, imgUrl, post);
    }
}
