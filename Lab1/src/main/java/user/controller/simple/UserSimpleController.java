package user.controller.simple;

import user.controller.api.UserController;
import user.dto.GetUsersResponse;
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
}
