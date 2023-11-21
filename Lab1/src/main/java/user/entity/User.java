package user.entity;

import jakarta.persistence.*;
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
@Entity
@Table(name = "users")
public class User implements Serializable {

    @Id
    private UUID id;

    private String nick;

    private String login;

    @ToString.Exclude
    private String password;

    private LocalDate birthDate;

    /**
     * Security roles
     */
    @CollectionTable(name = "users__roles", joinColumns = @JoinColumn(name = "id"))
    @Column(name = "role")
    @ElementCollection(fetch = FetchType.EAGER)
    private List<String> roles;

    /**
     * Likes from other people if they liked tutorials from that certain person
     */
    private int reputation;

    /**
     * Created tutorials
     */
    @Singular
    @ToString.Exclude//It's common to exclude lists from toString
    @EqualsAndHashCode.Exclude
    @OneToMany(mappedBy = "author", cascade = CascadeType.REMOVE)
    private List<Tutorial> tutorials;
}
