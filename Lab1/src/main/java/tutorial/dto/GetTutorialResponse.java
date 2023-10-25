package tutorial.dto;

import lombok.*;
import skill.entity.Skill;
import user.entity.User;

import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
public class GetTutorialResponse {

    @Getter
    @Setter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    @ToString
    @EqualsAndHashCode
    public static class Skill {

        private UUID id;

        private String name;

    }

    private UUID id;
    private String title;

    /**
     * Content of the tutorial
     */
    private String description;

    /**
     * Estimated learning duration in hours
     */
    private int duration;

    private Skill skill;
}
