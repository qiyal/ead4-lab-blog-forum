package kz.ead4.spring.savedapi.service.impl;

import kz.ead4.spring.savedapi.model.Saved;
import kz.ead4.spring.savedapi.repository.SavedRepository;
import kz.ead4.spring.savedapi.service.ISavedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SavedService implements ISavedService {
    @Autowired
    private SavedRepository savedRepository;

    @Override
    public Saved createSaved(Saved saved) {
        return savedRepository.saveAndFlush(saved);
    }

    @Override
    public Saved getById(Long savedId) {
        return savedRepository.getSavedById(savedId);
    }

    @Override
    public Saved updateSavedTitle(Long id, String title) {
        Saved saved = savedRepository.getSavedById(id);
        saved.setTitle(title);
        return savedRepository.saveAndFlush(saved);
    }
}
