package skill.dto.function;

import skill.dto.PutSkillRequest;
import skill.entity.Skill;

import java.util.UUID;
import java.util.function.BiFunction;

public class RequestToSkillFunction implements BiFunction<UUID, PutSkillRequest, Skill> {

    @Override
    public Skill apply(UUID uuid, PutSkillRequest request) {
        return Skill.builder()
                .id(uuid)
                .name(request.getName())
                .description(request.getDescription())
                .tags(request.getTags())
                .level(request.getLevel())
                .favouriteRank(request.getFavouriteRank())
                .build();
    }
}
