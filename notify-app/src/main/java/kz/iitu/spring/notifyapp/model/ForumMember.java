package kz.iitu.spring.notifyapp.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ForumMember {
    private Long id;
    private Long forumId;
    private Long memberId;

    public ForumMember(Long forumId, Long memberId) {
        this.forumId = forumId;
        this.memberId = memberId;
    }
}
