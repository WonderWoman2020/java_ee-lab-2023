package skill.dto.function;

import skill.dto.GetSkillResponse;
import skill.dto.GetSkillsResponse;
import skill.entity.Skill;
import user.dto.GetUsersResponse;

import java.util.List;
import java.util.function.Function;

public class SkillsToResponseFunction implements Function<List<Skill>, GetSkillsResponse> {

    @Override
    public GetSkillsResponse apply(List<Skill> entities) {
        return GetSkillsResponse.builder()
                .skills(entities.stream()
                        .map(skill -> GetSkillsResponse.Skill.builder()
                                .id(skill.getId())
                                .name(skill.getName())
                                .build())
                        .toList())
                .build();
    }
}
