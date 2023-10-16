package user.controller.api;

import user.dto.GetUsersResponse;

import java.util.UUID;

public interface UserController {

    GetUsersResponse getUsers();

    //GetCharactersResponse getUserTutorials(UUID id);

    //GetCharacterResponse getUser(UUID uuid);

    //void putUser(UUID id, PutCharacterRequest request);

    //void patchUser(UUID id, PatchCharacterRequest request);

    //void deleteUser(UUID id);


}
