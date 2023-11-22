package user.controller.api;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import skill.dto.PatchSkillRequest;
import skill.dto.PutSkillRequest;
import user.dto.GetUserResponse;
import user.dto.GetUsersResponse;
import user.dto.PatchUserRequest;
import user.dto.PutUserRequest;

import java.io.InputStream;
import java.util.UUID;

@Path("")
public interface UserController {

    @GET
    @Path("/users")
    @Produces(MediaType.APPLICATION_JSON)
    GetUsersResponse getUsers();

    @GET
    @Path("/users/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    GetUserResponse getUser(@PathParam("id") UUID uuid);

    @DELETE
    @Path("/users/{id}")
    void deleteUser(@PathParam("id") UUID id);

    @PUT
    @Path("/users/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    void putUser(@PathParam("id") UUID id, PutUserRequest request);

    @PATCH
    @Path("/users/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    void patchUser(@PathParam("id") UUID id, PatchUserRequest request);


    @GET
    @Path("/users/{id}/avatar")
    @Produces("image/png")
    public byte[] getUserAvatar(@PathParam("id") UUID id);

    @PUT
    @Path("/users/{id}/avatar")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    public void putUserAvatar(@PathParam("id") UUID id,
                              @SuppressWarnings("RestParamTypeInspection") @FormParam("portrait") InputStream portrait);

    @DELETE
    @Path("/users/{id}/avatar")
    void deleteUserAvatar(@PathParam("id") UUID id);

}
