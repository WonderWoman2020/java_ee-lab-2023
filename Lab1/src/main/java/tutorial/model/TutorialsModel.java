package tutorial.model;

import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

/**
 * JSF view model class in order to not use entity classes. Represents list of skills to be displayed.
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
public class TutorialsModel implements Serializable {

    /**
     * Represents single tutorial in list.
     */
    @Getter
    @Setter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    @ToString
    @EqualsAndHashCode
    public static class Tutorial {

        private UUID id;
        private String title;
        /**
         * Entity version.
         */
        private Long version;

        /**
         * Creation datetime.
         */
        private LocalDateTime creationDateTime;

        /**
         * Edition datetime.
         */
        private LocalDateTime editionDateTime;

    }

    @Singular
    private List<Tutorial> tutorials;

}
