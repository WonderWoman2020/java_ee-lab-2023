package user.dto.function;

import user.dto.GetUsersResponse;
import user.entity.User;

import java.util.List;
import java.util.function.Function;



public class UsersToResponseFunction implements Function<List<User>, GetUsersResponse> {

    @Override
    public GetUsersResponse apply(List<User> entities) {
        return GetUsersResponse.builder()
                .users(entities.stream()
                        .map(user -> GetUsersResponse.User.builder()
                                .id(user.getId())
                                .nick(user.getNick())
                                .build())
                        .toList())
                .build();
    }

}