package skill.dto.function;

import skill.dto.PatchSkillRequest;
import skill.entity.Skill;
import tutorial.dto.PatchTutorialRequest;
import tutorial.entity.Tutorial;

import java.util.function.BiFunction;

public class UpdateSkillWithRequestFunction implements BiFunction<Skill, PatchSkillRequest, Skill> {

    @Override
    public Skill apply(Skill entity, PatchSkillRequest request) {
        return Skill.builder()
                .id(entity.getId())
                .name(request.getName())
                .description(request.getDescription())
                .level(request.getLevel())
                .tags(request.getTags())
                .favouriteRank(request.getFavouriteRank())
                .build();
    }
}
