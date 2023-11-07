package user.controller.api;

import skill.dto.PutSkillRequest;
import user.dto.GetUserResponse;
import user.dto.GetUsersResponse;
import user.dto.PutUserRequest;

import java.io.InputStream;
import java.util.UUID;

public interface UserController {

    GetUsersResponse getUsers();

    GetUserResponse getUser(UUID uuid);

    void deleteUser(UUID id);

    void putUser(UUID id, PutUserRequest request);

    public byte[] getUserAvatar(UUID id);

    public void putUserAvatar(UUID id, InputStream portrait);
    void deleteUserAvatar(UUID id);

    //GetCharactersResponse getUserTutorials(UUID id);

    //void putUser(UUID id, PutCharacterRequest request);

    //void patchUser(UUID id, PatchCharacterRequest request);


}
