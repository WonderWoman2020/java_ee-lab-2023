package user.controller.rest;

//import controller.exception.BadRequestException;
//import controller.exception.NotFoundException;

import jakarta.enterprise.inject.Alternative;
import jakarta.inject.Inject;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.ws.rs.BadRequestException;
import jakarta.ws.rs.NotFoundException;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriBuilder;
import jakarta.ws.rs.core.UriInfo;
import skill.dto.function.UpdateSkillWithRequestFunction;
import user.controller.api.UserController;
import user.dto.GetUserResponse;
import user.dto.GetUsersResponse;
import user.dto.PatchUserRequest;
import user.dto.PutUserRequest;
import user.dto.function.RequestToUserFunction;
import user.dto.function.UpdateUserWithRequestFunction;
import user.dto.function.UserToResponseFunction;
import user.dto.function.UsersToResponseFunction;
import user.service.UserService;

import java.io.InputStream;
import java.util.UUID;

@Alternative
@Path("")//Annotation required by the specification.
public class UserRestController implements UserController {

    private UserService service;

    /**
     * Allows to create {@link UriBuilder} based on current request.
     */
    private final UriInfo uriInfo;

    /**
     * Current HTTP Servlet response.
     */
    private HttpServletResponse response;

    @Context
    public void setResponse(HttpServletResponse response) {
        //ATM in this implementation only HttpServletRequest can be injected with CDI so JAX-RS injection is used.
        this.response = response;
    }
    @Inject
    public UserRestController(UserService service,
                              @SuppressWarnings("CdiInjectionPointsInspection") UriInfo uriInfo) {
        this.service = service;
        this.uriInfo = uriInfo;
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
    public void deleteUser(UUID id) {
        service.find(id).ifPresentOrElse(
                entity -> service.delete(entity.getId()),
                () -> {
                    throw new NotFoundException();
                }
        );
    }

    @Override
    public void putUser(UUID id, PutUserRequest request) {
        try {
            service.create(new RequestToUserFunction().apply(id, request));

            response.setHeader("Location", uriInfo.getBaseUriBuilder()
                    .path(UserController.class, "getUser")
                    .build(id)
                    .toString());
            //This can be done with Response builder but requires method different return type.
            //Calling HttpServletResponse#setStatus(int) is ignored.
            //Calling HttpServletResponse#sendError(int) causes response headers and body looking like error.
            throw new WebApplicationException(Response.Status.CREATED);

        } catch (IllegalArgumentException ex) {
            throw new BadRequestException(ex);
        }
    }

    @Override
    public void patchUser(UUID id, PatchUserRequest request) {
        service.find(id).ifPresentOrElse(
                entity -> service.update(new UpdateUserWithRequestFunction().apply(entity, request)),
                () -> {
                    throw new NotFoundException();
                }
        );
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
