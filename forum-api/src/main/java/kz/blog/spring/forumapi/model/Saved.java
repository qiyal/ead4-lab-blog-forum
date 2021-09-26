package kz.blog.spring.forumapi.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Saved {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String ownerId;

    @OneToMany
    @JoinColumn(name = "saved_id")
    private List<SavedPost> postsIds;
}
