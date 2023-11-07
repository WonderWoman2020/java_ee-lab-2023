package user.dto;

import lombok.*;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
public class PutUserRequest {

    private UUID id;
    private String nick;
    private LocalDate birthDate;

    /**
     * Security roles
     */
    private List<String> roles;

    /**
     * Likes from other people if they liked tutorials from that certain person
     */
    private int reputation;
}
