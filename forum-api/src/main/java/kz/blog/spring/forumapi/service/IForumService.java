package kz.blog.spring.forumapi.service;

import kz.blog.spring.forumapi.model.Forum;

import java.util.List;

public interface IForumService {
    Forum getForumById(Long forumId);
    List<Forum> getAllForum();
    List<Forum> searchForumByTitle(String title);
}
