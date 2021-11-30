package kz.iitu.spring.notifyapp.consume;

import kz.iitu.spring.notifyapp.model.AddUserForumRequest;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class Consume {

    @KafkaListener(topics = "forum_requests", groupId = "forum_id")
    public void consume(AddUserForumRequest addUserForumRequest) throws IOException {
        System.out.println(String.format("#### -> Notify user by email: -> %s",
                "User " + addUserForumRequest.getUserId() + " purchased book "
                        + addUserForumRequest.getForum().toString()));
    }
}
