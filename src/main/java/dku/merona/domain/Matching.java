package dku.merona.domain;

import javax.persistence.*;

@Entity
public class Matching {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "matching_id")
    private Long id;

    @OneToOne
    private Post post;

}
