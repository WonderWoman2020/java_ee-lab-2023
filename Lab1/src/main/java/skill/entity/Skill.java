package skill.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import tutorial.entity.Tutorial;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;


@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString()
@EqualsAndHashCode()
@Entity
@Table(name = "skills")
public class Skill implements Serializable {

    @Id
    private UUID id;
    private String name;
    private String description;
    private DifficultyLevel level;

    /**
     * Tags like Sport, Recreation, Hobby, Fun, so the skills could be filtered and searched by combinations of these
     */
    private List<String> tags;

    /**
     * How many users liked that skill, are interested in it
     */
    private int favouriteRank;

    /**
     * Tutorials about that skill
     */
    @Singular
    @ToString.Exclude//It's common to exclude lists from toString
    @EqualsAndHashCode.Exclude
    @OneToMany(mappedBy = "skill", cascade = CascadeType.REMOVE)
    private List<Tutorial> tutorials;

}
