package tutorial.entity;

import skill.entity.Skill;
import user.entity.User;

import java.util.UUID;

public class Tutorial {

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
