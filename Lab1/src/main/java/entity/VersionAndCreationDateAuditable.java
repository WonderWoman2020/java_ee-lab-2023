package entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

/**
 * Base super class providing support for optimistic locking version and creation date.
 */
@MappedSuperclass
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
public class VersionAndCreationDateAuditable {

    /**
     * Edit version fore optimistic locking.
     */
    @Version
    private Long version;

    /**
     * Creation date.
     */
    @Column(name = "creation_date_time")
    private LocalDateTime creationDateTime;

    /**
     * Edition date.
     */
    @Column(name = "edition_date_time")
    private LocalDateTime editionDateTime;

    /**
     * Update creation datetime.
     */
    @PrePersist
    public void updateCreationDateTime() {
        creationDateTime = LocalDateTime.now();
    }

    @PreUpdate
    public void updateEditionDateTime() {
        editionDateTime = LocalDateTime.now();
    }

}
