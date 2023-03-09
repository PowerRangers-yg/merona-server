package dku.merona.dto;

import dku.merona.domain.Member;
import dku.merona.domain.Post;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class PostRequest {
    private String title;

    private String description;

    private int deliveryPay;

    private int dueTime;

    private String arrivalLocation;

    private Member member;

    public Post toEntity(){
        return Post.builder()
                .title(title)
                .description(description)
                .deliveryPay(deliveryPay)
                .dueTime(dueTime)
                .arrivalLocation(arrivalLocation)
                .member(member)
                .build();
    }
}
