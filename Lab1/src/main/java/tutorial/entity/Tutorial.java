package tutorial.entity;

import jakarta.persistence.*;
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
@Entity
@Table(name = "tutorials")
public class Tutorial implements Serializable {

    @Id
    private UUID id;
    private String title;
    private User author;
    @ManyToOne
    @JoinColumn(name = "skill")
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
