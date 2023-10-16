package user.controller.api;

import user.dto.GetUserResponse;
import user.dto.GetUsersResponse;

import java.util.UUID;

public interface UserController {

    GetUsersResponse getUsers();

    GetUserResponse getUser(UUID uuid);

    //GetCharactersResponse getUserTutorials(UUID id);

    //void putUser(UUID id, PutCharacterRequest request);

    //void patchUser(UUID id, PatchCharacterRequest request);

    //void deleteUser(UUID id);


}
