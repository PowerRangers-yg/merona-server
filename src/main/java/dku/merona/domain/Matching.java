package dku.merona.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
public class Matching extends BaseTime{

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "matching_id")
    private Long id;

    @OneToOne
    private Post post;

    @OneToOne
    private Request request;

    public Matching(Post post, Request request) {
        this.post = post;
        this.request = request;
    }

    public void setNewStatus() {
        this.post.setStatusMatching();
        this.request.setStatusMatching();
    }

    public void setCancelStatus() {
        this.post.setStatusWaiting();
        this.request.setStatusCancelled();
    }
}
