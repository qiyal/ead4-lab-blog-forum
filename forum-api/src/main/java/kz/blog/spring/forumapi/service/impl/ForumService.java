package kz.blog.spring.forumapi.service.impl;

import kz.blog.spring.forumapi.model.Forum;
import kz.blog.spring.forumapi.model.ForumMember;
import kz.blog.spring.forumapi.model.User;
import kz.blog.spring.forumapi.repository.ForumRepository;
import kz.blog.spring.forumapi.service.IForumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
//import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class ForumService implements IForumService {
    @Autowired
    private ForumRepository forumRepository;
//    @Autowired
//    private RestTemplate restTemplate;
    @Autowired
    private UserService userService;

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
        return forumRepository.getForumsByTitleIsLike("%" + title + "%");
    }

    @Override
    public Forum createForum(Forum forum) {
        return forumRepository.saveAndFlush(forum);
    }

    @Override
    public void deleteForum(Long forumId) {
        forumRepository.deleteById(forumId);
    }

    @Override
    public Forum updateForumOwnerByUsername(Long forumId, String username) {
        Forum forum = forumRepository.getForumById(forumId);

        User user = userService.getByUsername(username);

        if (user != null && user.getId() != null) {
            forum.setOwnerId(user.getId());
            return forumRepository.saveAndFlush(forum);
        }
        return null;
    }

    @Override
    public Forum addMember(Long forumId, String username) {
        Forum forum = forumRepository.getForumById(forumId);

        User user = userService.getByUsername(username);

        if (user != null && user.getId() != null) {
            boolean has = false;

            for (ForumMember member : forum.getMembersIds()) {
                if (member.getMemberId().equals(user.getId())) {
                    has = true;
                    break;
                }
            }

            if (!has) {
                forum.getMembersIds().add(new ForumMember(forum.getId(), user.getId()));
                return forumRepository.saveAndFlush(forum);
            }
        }
        return null;
    }

    @Override
    public Forum removeMember(Long forumId, Long memberId) {
        Forum forum = forumRepository.getForumById(forumId);

        boolean has = false;
        System.out.println(forum.getMembersIds().size());
        for (int i = 0; i < forum.getMembersIds().size(); i++) {
            if (forum.getMembersIds().get(i).getMemberId().equals(memberId)) {
                forum.getMembersIds().remove(i);
                has = true;
                System.out.println(forum.getMembersIds().size());
                break;
            }
        }

        if (has) {
            return forumRepository.saveAndFlush(forum);
        }

        return null;
    }
}
