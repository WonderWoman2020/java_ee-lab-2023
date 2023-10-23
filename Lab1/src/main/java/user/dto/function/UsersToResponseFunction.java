package user.dto.function;

import user.dto.GetUsersResponse;
import user.entity.User;

import java.util.List;
import java.util.function.Function;


/**
 * Function to translate an entity class to a contract class, which contains only the fields that we are willing
 * to show to someone who sent as a request
 */

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