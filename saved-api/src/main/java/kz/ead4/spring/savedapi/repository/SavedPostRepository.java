package kz.ead4.spring.savedapi.repository;

import kz.ead4.spring.savedapi.model.SavedPost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SavedPostRepository extends JpaRepository<SavedPost, Long> {
}
