package kz.ead4.spring.savedapi.model;

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
public class Saved {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private Long ownerId;

    @ApiModelProperty(notes = "List of posts in saved")
    @OneToMany
    @JoinColumn(name = "saved_id")
    private List<SavedPost> postsIds;
}
