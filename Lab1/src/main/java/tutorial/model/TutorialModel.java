package tutorial.model;

import lombok.*;
import skill.entity.DifficultyLevel;
import skill.entity.Skill;
import user.entity.User;

import java.util.List;
import java.util.UUID;

/**
 * JSF view model class in order to not use entity classes. Represents single tutorial to be displayed.
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
public class TutorialModel {

    private String title;
    private String author;
    private String skill;

    /**
     * Content of the tutorial
     */
    private String description;

    /**
     * Estimated learning duration in hours
     */
    private int duration;

}
