package user.controller.simple;

import user.controller.api.UserController;
import user.dto.GetUserResponse;
import user.dto.GetUsersResponse;
import user.dto.function.UserToResponseFunction;
import user.dto.function.UsersToResponseFunction;

import java.io.InputStream;
import java.util.UUID;

import controller.exception.*;
import user.entity.User;
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

    @Override
    public byte[] getUserAvatar(UUID id) {
        return service.findAvatar(id); /*service.find(id)
                .map(User::getAvatar)
                .orElseThrow(NotFoundException::new);*/
    }

    @Override
    public void putUserAvatar(UUID id, InputStream portrait) {
        service.find(id).ifPresentOrElse(
                entity -> service.updateAvatar(id, portrait),
                () -> {
                    throw new NotFoundException();
                }
        );
    }

}
