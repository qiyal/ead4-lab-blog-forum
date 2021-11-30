package kz.blog.spring.forumapi.producer;

import kz.blog.spring.forumapi.model.AddUserForumRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class ForumProducer {

    private static final String TOPIC = "forum_requests";

    @Autowired
    private KafkaTemplate<String, AddUserForumRequest> kafkaTemplate;

    public String forumRequestNotify(AddUserForumRequest addUserForumRequest) {
        System.out.println(String.format("#### -> Producing forum request to notification service -> %s", addUserForumRequest));
        this.kafkaTemplate.send(TOPIC, addUserForumRequest);
        return "Successfully";
    }
}
