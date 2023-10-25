package tutorial.dto;

import lombok.*;
import user.dto.GetUsersResponse;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
public class GetTutorialsResponse {

    @Getter
    @Setter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    @ToString
    @EqualsAndHashCode
    public static class Tutorial {

        private UUID id;
        private String title;

    }

    @Singular
    private List<Tutorial> tutorials;
}
