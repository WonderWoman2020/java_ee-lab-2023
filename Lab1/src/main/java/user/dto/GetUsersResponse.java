package user.dto;

import lombok.*;

import java.util.List;
import java.util.UUID;

/**
 * Just a contract class, contains only data and only these fields from entity class, which we want to
 * show to someone, who sent us a request. Objects like this are returned from entity controllers to the main
 * controller, servlet. The servlet writes their data to response as a plain text or formatted, for example in a JSON format.
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
public class GetUsersResponse {
    @Getter
    @Setter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    @ToString
    @EqualsAndHashCode
    public static class User {

        private UUID id;
        private String nick;

    }

    @Singular
    private List<User> users;

}
