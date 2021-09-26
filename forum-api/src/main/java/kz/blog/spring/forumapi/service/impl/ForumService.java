package kz.blog.spring.forumapi.service.impl;

import kz.blog.spring.forumapi.model.Forum;
import kz.blog.spring.forumapi.repository.ForumRepository;
import kz.blog.spring.forumapi.service.IForumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ForumService implements IForumService {
    @Autowired
    private ForumRepository forumRepository;

    @Override
    public Forum getForumById(Long forumId) {
        return forumRepository.getForumById(forumId);
    }

    @Override
    public List<Forum> getAllForum() {
        return forumRepository.findAll();
    }

    @Override
    public List<Forum> searchForumByTitle(String title) {
        return forumRepository.getForumsByTitleLike(title);
    }
}
