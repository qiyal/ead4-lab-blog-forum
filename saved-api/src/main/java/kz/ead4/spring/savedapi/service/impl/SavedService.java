package kz.ead4.spring.savedapi.service.impl;

import kz.ead4.spring.savedapi.model.Post;
import kz.ead4.spring.savedapi.model.PostList;
import kz.ead4.spring.savedapi.model.Saved;
import kz.ead4.spring.savedapi.model.SavedPost;
import kz.ead4.spring.savedapi.repository.SavedRepository;
import kz.ead4.spring.savedapi.service.ISavedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
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
        Saved saved = savedRepository.getSavedById(id);

        List<Long> postIds = new ArrayList<>();
        for (SavedPost savedPost : saved.getPostsIds())
            postIds.add(savedPost.getPostId());

        PostList postList = restTemplate.postForObject("http://localhost:8083/post/getPostIds", postIds, PostList.class);

        List<Post> result = new ArrayList<>();

        if (postList != null) {
            for (Post post : postList.getPosts()) {
                if (post.getTitle().contains(title)) {
                    result.add(post);
                }
            }
        }

        return result;
    }
}
