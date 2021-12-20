package kz.iitu.spring.notifyapp.model;

import lombok.*;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AddUserForumRequest {
    private String userId;
    private Forum forum;
}
