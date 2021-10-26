package kz.blog.spring.forumapi.service;

import kz.blog.spring.forumapi.model.User;

public interface IUserService {
    User getByUsername(String username);
}
