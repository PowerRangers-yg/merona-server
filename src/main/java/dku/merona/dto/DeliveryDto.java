package dku.merona.dto;

import dku.merona.constant.RequestStatus;
import dku.merona.domain.Delivery;
import dku.merona.domain.Member;
import dku.merona.domain.Post;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

public class DeliveryDto {

    @Getter @Setter
    @NoArgsConstructor
    public static class Request {
        private String currentLocation;

        private int dueTime;

        private Member member;

        private Post post;

        private Long postId;

        private RequestStatus status;

        public Delivery toEntity(){
            return Delivery.builder()
                    .currentLocation(currentLocation)
                    .dueTime(dueTime)
                    .member(member)
                    .post(post)
                    .build();
        }

        public void setMemberAndPost(Member member, Post post) {
            this.member = member;
            this.post = post;
        }
    }

    @Getter
    public static class Response {
        private Long id;

        private String currentLocation;

        private int dueTime;

        private Member member;

        private Post post;

        private RequestStatus status;

        private LocalDateTime createdAt;

        private LocalDateTime updatedAt;

        public Response(Delivery delivery) {
            this.id = delivery.getId();
            this.currentLocation = delivery.getCurrentLocation();
            this.dueTime = delivery.getDueTime();
            this.member = delivery.getMember();
            this.post = delivery.getPost();
            this.status = delivery.getStatus();
            this.createdAt = delivery.getCreatedAt();
            this.updatedAt = delivery.getUpdatedAt();
        }
    }
}
