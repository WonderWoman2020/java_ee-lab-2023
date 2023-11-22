package user.dto.function;

import skill.dto.PutSkillRequest;
import skill.entity.Skill;
import user.dto.PutUserRequest;
import user.entity.User;

import java.util.UUID;
import java.util.function.BiFunction;

public class RequestToUserFunction implements BiFunction<UUID, PutUserRequest, User>  {

    @Override
    public User apply(UUID uuid, PutUserRequest request) {
        return User.builder()
                .id(uuid)
                .nick(request.getNick())
                .login(request.getLogin())
                .password(request.getPassword())
                .birthDate(request.getBirthDate())
                .reputation(request.getReputation())
                .roles(request.getRoles())
                .build();
    }
}
