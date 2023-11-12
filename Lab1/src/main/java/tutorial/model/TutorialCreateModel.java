package tutorial.model;

import jakarta.servlet.http.Part;
import lombok.*;
import skill.model.SkillModel;

import java.util.UUID;

/**
 * JSF view model class in order to not use entity classes. Represents new character to be created. Includes oll
 * fields which can be used in character creation.
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
public class TutorialCreateModel {

    private UUID id;

    private SkillModel skill;

    private String title;

    /**
     * Content of the tutorial
     */
    private String description;

    /**
     * Estimated learning duration in hours
     */
    private int duration;

}
