package dku.merona.repository;

import dku.merona.domain.Post;
import dku.merona.domain.Request;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RequestRepository extends JpaRepository<Request, Long> {
    List<Request> findAllByPost(Post post);
}
