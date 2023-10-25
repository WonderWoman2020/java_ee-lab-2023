package user.controller.simple;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import user.controller.api.UserController;
import user.dto.GetUserResponse;
import user.dto.GetUsersResponse;
import user.dto.function.UserToResponseFunction;
import user.dto.function.UsersToResponseFunction;

import java.io.InputStream;
import java.util.UUID;

import controller.exception.*;
import user.service.UserService;

@RequestScoped
public class UserSimpleController implements UserController {

    private UserService service;

    @Inject
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
        if(service.find(id).isEmpty())
            throw new NotFoundException();

        byte[] avatar = service.findAvatar(id);
        if(avatar == null)
            throw new NotFoundException();

        return avatar;
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

    @Override
    public void deleteUserAvatar(UUID id) {
        if(service.find(id).isEmpty())
            throw new NotFoundException();

        service.deleteAvatar(id);
    }

}
