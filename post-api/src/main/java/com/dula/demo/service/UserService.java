package com.dula.demo.service;

import com.dula.demo.model.User;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class UserService {
    @Autowired
    private RestTemplate restTemplate;

    @HystrixCommand(
            fallbackMethod = "getByUsernameFallback",
            threadPoolKey = "getByUsername",
            threadPoolProperties = {
                    @HystrixProperty(name="coreSize", value="100"),
                    @HystrixProperty(name="maxQueueSize", value="50"),
            })
    public User getByUsername(String username) {
        return restTemplate.getForObject("http://user-api-app/user/public/getByUsername/" + username, User.class);
    }

    public User getByUsernameFallback(String username) {
        User user = new User();
        user.setId(0L);
        user.setUsername("Not found " + username);
        user.setName("Not found " + username);
        user.setSurname("Not found " + username);
        user.setPassword("Not found " + username);
        return user;
    }
}
