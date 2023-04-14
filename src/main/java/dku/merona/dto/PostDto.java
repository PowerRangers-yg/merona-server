package dku.merona.dto;

import dku.merona.constant.Category;
import dku.merona.constant.Status;
import dku.merona.domain.Member;
import dku.merona.domain.Post;
import dku.merona.domain.PostImg;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;

public class PostDto {

    @Getter @Setter
    public static class Request {
        // 게시글을 쓸 때 요청받는 정보
        @NotNull
        private String title;

        @NotNull
        private String description;

        @NotNull
        private int deliveryPay;

        @NotNull
        private int dueTime;

        @NotNull
        private String address;

        private Double latitude;

        private Double longitude;

        private String category;

        private Category postCategory;

        private Member member;

        public Post toEntity(){
            return dku.merona.domain.Post.builder()
                    .title(title)
                    .description(description)
                    .deliveryPay(deliveryPay)
                    .dueTime(dueTime)
                    .address(address)
                    .latitude(latitude)
                    .longitude(longitude)
                    .category(postCategory)
                    .member(member)
                    .build();
        }
    }

    @Getter
    public static class Response {
    // 특정 게시물 하나를 조회할 때 보이는 정보
        private Long id;

        private String title;

        private String description;

        private int deliveryPay;

        private int dueTime;

        private String address;

        private String category;

        private Member member;

        private Status status;

        private List<PostImg> postImgList;

        private LocalDateTime createdAt;

        private LocalDateTime updatedAt;

        public Response(Post post) {
            this.id = post.getId();
            this.title = post.getTitle();
            this.description = post.getDescription();
            this.deliveryPay = post.getDeliveryPay();
            this.dueTime = post.getDueTime();
            this.address = post.getAddress();
            this.member = post.getMember();
            this.category = post.getCategory().getTitle();
            this.status = post.getStatus();
            this.postImgList = post.getPostImgList();
            this.createdAt = post.getCreatedAt();
            this.updatedAt = post.getUpdatedAt();
        }
    }
}
