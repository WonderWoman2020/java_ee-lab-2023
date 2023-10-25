package skill.dto.function;

import skill.dto.GetSkillResponse;
import skill.entity.Skill;

import java.util.function.Function;

public class SkillToResponseFunction implements Function<Skill, GetSkillResponse> {

    @Override
    public GetSkillResponse apply(Skill skill) {
        return GetSkillResponse.builder()
                .id(skill.getId())
                .name(skill.getName())
                .level(skill.getLevel())
                .description(skill.getDescription())
                .tags(skill.getTags())
                .favouriteRank(skill.getFavouriteRank())
                .build();
    }
}
