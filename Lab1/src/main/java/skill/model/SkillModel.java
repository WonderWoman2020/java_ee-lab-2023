package skill.model;

import lombok.*;
import skill.entity.DifficultyLevel;

import java.util.List;
import java.util.UUID;

/**
 * JSF view model class in order to not use entity classes. Represents single skill to be displayed.
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
public class SkillModel {

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

}
