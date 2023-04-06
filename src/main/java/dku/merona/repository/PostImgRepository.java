package dku.merona.repository;

import dku.merona.domain.Post;
import dku.merona.domain.PostImg;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostImgRepository extends JpaRepository<PostImg, Long> {

    List<PostImg> findAllByPost(Post post);

}
