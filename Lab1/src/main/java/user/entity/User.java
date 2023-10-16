package user.entity;

import tutorial.entity.Tutorial;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public class User {
    private UUID id;
    private String nick;
    private String login;
    private String password;
    private LocalDate birthDate;

    /**
     * Security roles
     */
    private List<String> roles;

    /**
     * Likes from other people if they liked tutorials from that certain person
     */
    private int reputation;

    /**
     * Created tutorials
     */
    private List<Tutorial> tutorials;
}
