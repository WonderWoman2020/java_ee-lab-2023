package user.dto.function;

import skill.dto.PatchSkillRequest;
import skill.entity.Skill;
import user.dto.PatchUserRequest;
import user.entity.User;

import java.util.function.BiFunction;

public class UpdateUserWithRequestFunction implements BiFunction<User, PatchUserRequest, User> {

    @Override
    public User apply(User entity, PatchUserRequest request) {
        return User.builder()
                .id(entity.getId())
                .nick(request.getNick())
                .birthDate(request.getBirthDate())
                .roles(request.getRoles())
                .reputation(request.getReputation())
                .build();
    }
}
