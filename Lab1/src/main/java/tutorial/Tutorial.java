package tutorial;

import skill.Skill;
import user.User;

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
