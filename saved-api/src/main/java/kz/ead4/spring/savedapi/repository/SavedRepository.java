package kz.ead4.spring.savedapi.repository;

import kz.ead4.spring.savedapi.model.Saved;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SavedRepository extends JpaRepository<Saved, Long> {
    Saved getSavedById(Long id);
}
