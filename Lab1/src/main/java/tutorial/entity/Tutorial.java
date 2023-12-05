package tutorial.entity;

import entity.VersionAndCreationDateAuditable;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import skill.entity.Skill;
import user.entity.User;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString()
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "tutorials")
public class Tutorial extends VersionAndCreationDateAuditable implements Serializable {

    @Id
    private UUID id;
    private String title;

    @ManyToOne
    @JoinColumn(name = "author")
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

    /**
     * This method is required due the bug in EclipseLink: https://www.eclipse.org/forums/index.php/t/820662/
     */
    @PrePersist
    @Override
    public void updateCreationDateTime() {
        super.updateCreationDateTime();
    }

    @PreUpdate
    public void updateEditionDateTime() {
        super.updateEditionDateTime();
    }
}
