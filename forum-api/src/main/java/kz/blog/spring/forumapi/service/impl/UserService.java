package kz.blog.spring.forumapi.service.impl;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import kz.blog.spring.forumapi.model.User;
import kz.blog.spring.forumapi.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;

@Service
public class UserService implements IUserService {
    @Autowired
    private RestTemplate restTemplate;

    @Override
    @HystrixCommand(
            fallbackMethod = "getByUsernameFallback",
            threadPoolKey = "getByUsername",
            threadPoolProperties = {
                    @HystrixProperty(name="coreSize", value="100"),
                    @HystrixProperty(name="maxQueueSize", value="50"),
            })
    public User getByUsername(String username) {
        return restTemplate.getForObject("http://user-api-app/user/getByUsername/" + username, User.class);
    }

    public User getByUsernameFallback(String username) {
        User user = new User();
        user.setId(0L);
        user.setUsername("Unknown username");
        user.setName("Unknown name");
        user.setSurname("Unknown surname");
        user.setPassword("Password");
        user.setRoles(new ArrayList<>());
        return user;
    }
}
