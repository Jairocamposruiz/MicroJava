package es.jcoder.microjava.repositories;

import es.jcoder.microjava.entities.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepsository extends JpaRepository<Post, Long> {
}
