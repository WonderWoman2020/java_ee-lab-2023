package skill.entity;

import tutorial.entity.Tutorial;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;


public class Skill implements Serializable {

    private UUID id;
    private String name;
    private String description;
    private DifficultyLevel level;

    /**
     * Tags like Sport, Recreation, Hobby, Fun, so the skills could be filtered and searched by combinations of these
     */
    private List<String> tags;

    /**
     * How many users liked that skill, are interested in it
     */
    private int favouriteRank;

    /**
     * Tutorials about that skill
     */
    private List<Tutorial> tutorials;

}
