package kz.blog.spring.forumapi.repository;

import kz.blog.spring.forumapi.model.Forum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ForumRepository extends JpaRepository<Forum, Long> {
    Forum getForumById(Long forumId);
//    List<Forum> getAllBy();
    List<Forum> getForumsByTitleLike(String title);
}
