package user.dto.function;

import user.dto.GetUserResponse;
import user.entity.User;

import java.util.function.Function;

public class UserToResponseFunction implements Function<User, GetUserResponse> {
    @Override
    public GetUserResponse apply(User user) {
        return GetUserResponse.builder()
                .id(user.getId())
                .nick(user.getNick())
                .login(user.getLogin())
                .birthDate(user.getBirthDate())
                .roles(user.getRoles())
                .reputation(user.getReputation())
                .build();
    }
}
