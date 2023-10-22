package user.controller.api;

import user.dto.GetUserResponse;
import user.dto.GetUsersResponse;

import java.io.InputStream;
import java.util.UUID;

public interface UserController {

    GetUsersResponse getUsers();

    GetUserResponse getUser(UUID uuid);

    public byte[] getUserAvatar(UUID id);

    public void putUserAvatar(UUID id, InputStream portrait);
    void deleteUserAvatar(UUID id);

    //GetCharactersResponse getUserTutorials(UUID id);

    //void putUser(UUID id, PutCharacterRequest request);

    //void patchUser(UUID id, PatchCharacterRequest request);

    //void deleteUser(UUID id);


}
