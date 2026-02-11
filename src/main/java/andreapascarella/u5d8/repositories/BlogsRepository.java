package andreapascarella.u5d8.repositories;

import andreapascarella.u5d8.entities.Blog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BlogsRepository extends JpaRepository<Blog, Long> {

}
