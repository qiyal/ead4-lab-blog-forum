package kz.ead4.spring.savedapi.service;

import kz.ead4.spring.savedapi.model.Post;
import kz.ead4.spring.savedapi.model.Saved;

import java.util.List;

public interface ISavedService {
    Saved createSaved(Saved saved);
    Saved getById(Long savedId);
    Saved updateSavedTitle(Long id, String title);
    void deletePost(Long id);
    List<Post> searchPostByTitleInSaved(Long id, String title);
}
