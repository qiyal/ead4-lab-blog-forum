package kz.blog.spring.forumapi.service;

import kz.blog.spring.forumapi.model.Forum;

import java.util.List;

public interface IForumService {
    Forum getForumById(Long forumId);
    List<Forum> getAllForum();
    List<Forum> searchForumByTitle(String title);
    Forum createForum(Forum forum);
    void deleteForum(Long forumId);
    Forum updateForumOwnerByUsername(Long forumId, String username);
    Forum addMember(Long forumId, String username);
    Forum removeMember(Long forumId, Long memberId);
}
