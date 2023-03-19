package dku.merona.dto;

import dku.merona.domain.Matching;
import dku.merona.domain.Post;
import dku.merona.domain.Request;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class MatchingRequest {

    private Long postId;

    private Long requestId;

    private Post post;

    private Request request;

    public Matching toEntity() {
        return new Matching(post, request);
    }
}
