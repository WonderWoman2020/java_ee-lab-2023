package tutorial.entity;

import lombok.*;
import lombok.experimental.SuperBuilder;
import skill.entity.Skill;
import user.entity.User;

import java.io.Serializable;
import java.util.UUID;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString()
@EqualsAndHashCode()
public class Tutorial implements Serializable {

    private UUID id;
    private String title;
    private User author;
    private Skill skill;

    /**
     * Content of the tutorial
     */
    private String description;

    /**
     * Estimated learning duration in hours
     */
    private int duration;
}
