package user.controller.simple;

import user.controller.api.UserController;
import user.dto.GetUserResponse;
import user.dto.GetUsersResponse;
import user.dto.function.UserToResponseFunction;
import user.dto.function.UsersToResponseFunction;

import java.util.UUID;

import controller.exception.*;
import user.service.UserService;

public class UserSimpleController implements UserController {

    private UserService service;

    public UserSimpleController(UserService service) {
        this.service = service;
    }
    @Override
    public GetUsersResponse getUsers() {
        return new UsersToResponseFunction().apply(service.findAll());
    }

    @Override
    public GetUserResponse getUser(UUID uuid) {
        return service.find(uuid).map(new UserToResponseFunction())
                .orElseThrow(NotFoundException::new);
    }

}
