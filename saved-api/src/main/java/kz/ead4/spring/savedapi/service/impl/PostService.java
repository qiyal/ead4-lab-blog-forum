package kz.ead4.spring.savedapi.service.impl;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import kz.ead4.spring.savedapi.model.Post;
import kz.ead4.spring.savedapi.model.PostList;
import kz.ead4.spring.savedapi.service.IPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class PostService implements IPostService {
    @Autowired
    private RestTemplate restTemplate;

    @Override
    @HystrixCommand(
            fallbackMethod = "getPostIdsFallback",
            threadPoolKey = "getPostIds",
            threadPoolProperties = {
                    @HystrixProperty(name="coreSize", value="100"),
                    @HystrixProperty(name="maxQueueSize", value="50"),
            })
    public PostList getPostIds(List<Long> postIds) {
        return restTemplate.postForObject("http://post-api-app/post/public/getPostIds", postIds, PostList.class);
    }

    public PostList getPostIdsFallback(List<Long> postIds) {
        return new PostList(new ArrayList<>());
    }

    @Override
    public PostList getPostList(List<Long> postIds) {
        return restTemplate.postForObject("http://post-api-app/post/public/getPostIds", postIds, PostList.class);
    }

    public List<Post> getPostListFallback(List<Long> postIds) {
        return new ArrayList<>();
    }
}
