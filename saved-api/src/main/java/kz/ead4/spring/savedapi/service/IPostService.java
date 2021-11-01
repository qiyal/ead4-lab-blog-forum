package kz.ead4.spring.savedapi.service;

import kz.ead4.spring.savedapi.model.PostList;

import java.util.List;

public interface IPostService {
    PostList getPostIds(List<Long> postIds);
}
