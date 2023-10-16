package user.controller.simple;

import user.controller.api.UserController;
import user.dto.GetUserResponse;
import user.dto.GetUsersResponse;
import user.dto.function.UserToResponseFunction;
import user.dto.function.UsersToResponseFunction;
import user.entity.User;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class UserSimpleController implements UserController {
    @Override
    public GetUsersResponse getUsers() {
        List<User> users = new ArrayList<>();
        users.add(User.builder()
                .id(UUID.fromString("12345678-AAAA-AAAA-AAAA-123456789ABC"))
                .nick("First!")
                .login("haha-login")
                .password("Don'tHackMe!123")
                .birthDate(LocalDate.now())
                .roles(null)
                .reputation(10)
                .tutorials(null)
                .build());

        return new UsersToResponseFunction().apply(users);
    }

    @Override
    public GetUserResponse getUser(UUID uuid) {
        User user = User.builder()
                .id(UUID.fromString("12345678-BBBB-BBBB-BBBB-123456789ABC"))
                .nick("Second :(")
                .login("im-the-one")
                .password("pass")
                .birthDate(LocalDate.now())
                .roles(null)
                .reputation(10)
                .tutorials(null)
                .build();
        return new UserToResponseFunction().apply(user);
    }
}
