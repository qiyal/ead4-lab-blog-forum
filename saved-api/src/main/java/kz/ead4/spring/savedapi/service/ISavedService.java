package kz.ead4.spring.savedapi.service;

import kz.ead4.spring.savedapi.model.Saved;

public interface ISavedService {
    Saved createSaved(Saved saved);
    Saved getById(Long savedId);
    Saved updateSavedTitle(Long id, String title);
}
