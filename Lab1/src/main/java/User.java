import java.time.LocalDate;
import java.util.List;

public class User {
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
