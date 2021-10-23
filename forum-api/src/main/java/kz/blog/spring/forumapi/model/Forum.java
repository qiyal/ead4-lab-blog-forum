package kz.blog.spring.forumapi.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Forum {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private Long ownerId;

    @ApiModelProperty(notes = "List of members in forum")
    @OneToMany
    @JoinColumn(name = "forum_id")
    private List<ForumMember> membersIds;
}
