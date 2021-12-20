package kz.iitu.spring.notifyapp.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Forum {
    private Long id;
    private String title;
    private Long ownerId;
    private List<ForumMember> membersIds;
}
