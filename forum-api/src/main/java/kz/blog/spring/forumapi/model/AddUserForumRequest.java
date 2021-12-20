package kz.blog.spring.forumapi.model;

import lombok.*;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class AddUserForumRequest {
    private String userId;
    private Forum forum;
}
