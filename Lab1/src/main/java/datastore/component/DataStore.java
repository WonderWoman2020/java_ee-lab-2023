package datastore.component;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import lombok.NoArgsConstructor;
import lombok.extern.java.Log;
import serialization.component.CloningUtility;
import skill.entity.Skill;
import user.entity.User;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Log
@ApplicationScoped
@NoArgsConstructor(force = true)
public class DataStore {

    /**
     * Set of all users.
     */
    private final Set<User> users = new HashSet<>();

    private final Set<Skill> skills = new HashSet<>();

    /**
     * Component used for creating deep copies.
     */
    private final CloningUtility cloningUtility;

    /**
     * @param cloningUtility component used for creating deep copies
     */
    @Inject
    public DataStore(CloningUtility cloningUtility) {
        this.cloningUtility = cloningUtility;
    }

    /**
     * Deletes existing character.
     *
     * @param id id of character to be deleted
     * @throws IllegalArgumentException if character with provided id does not exist
     */
    /*public synchronized void deleteCharacter(UUID id) throws IllegalArgumentException {
        if (!characters.removeIf(character -> character.getId().equals(id))) {
            throw new IllegalArgumentException("The character with id \"%s\" does not exist".formatted(id));
        }
    }*/

    /**
     * Seeks for all users.
     *
     * @return list (can be empty) of all users
     */
    public synchronized List<User> findAllUsers() {
        return users.stream()
                .map(cloningUtility::clone)
                .collect(Collectors.toList());
    }

    /**
     * Stores new user.
     *
     * @param value new user to be stored
     * @throws IllegalArgumentException if user with provided id already exists
     */
    public synchronized void createUser(User value) throws IllegalArgumentException {
        if (users.stream().anyMatch(character -> character.getId().equals(value.getId()))) {
            throw new IllegalArgumentException("The user id \"%s\" is not unique".formatted(value.getId()));
        }
        users.add(cloningUtility.clone(value));
    }

    /**
     * Updates existing user.
     *
     * @param value user to be updated
     * @throws IllegalArgumentException if user with the same id does not exist
     */
    public synchronized void updateUser(User value) throws IllegalArgumentException {
        if (users.removeIf(character -> character.getId().equals(value.getId()))) {
            users.add(cloningUtility.clone(value));
        } else {
            throw new IllegalArgumentException("The user with id \"%s\" does not exist".formatted(value.getId()));
        }
    }


    public synchronized List<Skill> findAllSkills() {
        return skills.stream()
                .map(cloningUtility::clone)
                .collect(Collectors.toList());
    }

    public synchronized void createSkill(Skill value) throws IllegalArgumentException {
        if (skills.stream().anyMatch(character -> character.getId().equals(value.getId()))) {
            throw new IllegalArgumentException("The skill id \"%s\" is not unique".formatted(value.getId()));
        }
        skills.add(cloningUtility.clone(value));
    }

    public synchronized void updateSkill(Skill value) throws IllegalArgumentException {
        if (skills.removeIf(character -> character.getId().equals(value.getId()))) {
            skills.add(cloningUtility.clone(value));
        } else {
            throw new IllegalArgumentException("The skill with id \"%s\" does not exist".formatted(value.getId()));
        }
    }
}
