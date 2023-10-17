package user.entity;

import lombok.*;
import lombok.experimental.SuperBuilder;
import tutorial.entity.Tutorial;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString()
@EqualsAndHashCode()
public class User implements Serializable {
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
    @Singular
    private List<Tutorial> tutorials;
}
