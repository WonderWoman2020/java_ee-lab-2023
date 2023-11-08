package skill.model.function;

import skill.entity.Skill;
import skill.model.SkillsModel;

import java.util.List;
import java.util.function.Function;

public class SkillsToModelFunction implements Function<List<Skill>, SkillsModel> {

    @Override
    public SkillsModel apply(List<Skill> entity) {
        return SkillsModel.builder()
                .skills(entity.stream()
                        .map(skill -> SkillsModel.Skill.builder()
                                .id(skill.getId())
                                .name(skill.getName())
                                .build())
                        .toList())
                .build();
    }

}
