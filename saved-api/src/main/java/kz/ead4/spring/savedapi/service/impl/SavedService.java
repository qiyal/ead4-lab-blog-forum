package kz.ead4.spring.savedapi.service.impl;

import kz.ead4.spring.savedapi.model.Post;
import kz.ead4.spring.savedapi.model.Saved;
import kz.ead4.spring.savedapi.repository.SavedRepository;
import kz.ead4.spring.savedapi.service.ISavedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class SavedService implements ISavedService {
    @Autowired
    private SavedRepository savedRepository;
    @Autowired
    private RestTemplate restTemplate;

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

    @Override
    public void deletePost(Long id) {
        savedRepository.deleteById(id);
    }

    @Override
    public List<Post> searchPostByTitleInSaved(Long id, String title) {
        List<Post> posts = restTemplate.getForObject("", List.class);
        return posts;
    }
}
