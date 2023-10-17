package user.controller.simple;

import datastore.component.DataStore;
import lombok.Getter;
import lombok.Setter;
import user.controller.api.UserController;
import user.dto.GetUserResponse;
import user.dto.GetUsersResponse;
import user.dto.function.UserToResponseFunction;
import user.dto.function.UsersToResponseFunction;
import user.entity.User;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import controller.exception.*;

@Getter // tymczas.
@Setter // tymczas.
public class UserSimpleController implements UserController {

    /**
     * Underlying data store. In future should be replaced with database connection.
     */
    //private final DataStore store = null; // tymczas.
    private DataStore store; // tymczas.
    @Override
    public GetUsersResponse getUsers() {
        return new UsersToResponseFunction().apply(findAll());
    }

    @Override
    public GetUserResponse getUser(UUID uuid) {
        // tu potem będzie service.find()
        return find(uuid).map(new UserToResponseFunction())
                .orElseThrow(NotFoundException::new);
    }

    //tymczas
    public Optional<User> find(UUID id) {

        //return characterRepository.find(id);
       return store.findAllUsers().stream()
                .filter(user -> user.getId().equals(id))
                .findFirst();
    }

    public List<User> findAll() {

        //return characterRepository.findAll();
        return store.findAllUsers();
    }

    public void create(User user) {

        //characterRepository.create(user);
        store.createUser(user);
    }

    public void update(User user) {

        //characterRepository.update(character);
        store.updateUser(user);
    }

    /**
     * Deletes existing character.
     *
     * @param id existing character's id to be deleted
     */
    /*public void delete(UUID id) {
        characterRepository.delete(characterRepository.find(id).orElseThrow());
    }*/

    /**
     * Updates portrait of the character.
     *
     * @param id character's id
     * @param is input stream containing new portrait
     */
    /*public void updatePortrait(UUID id, InputStream is) {
        characterRepository.find(id).ifPresent(character -> {
            try {
                character.setPortrait(is.readAllBytes());
                characterRepository.update(character);
            } catch (IOException ex) {
                throw new IllegalStateException(ex);
            }
        });
    }*/
}
