package kz.blog.spring.forumapi.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class ForumMember {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "forum_id")
    private Long forumId;
    private Long memberId;

    public ForumMember(Long forumId, Long memberId) {
        this.forumId = forumId;
        this.memberId = memberId;
    }
}
