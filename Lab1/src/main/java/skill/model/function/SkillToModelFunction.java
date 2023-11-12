package skill.model.function;


import skill.entity.Skill;
import skill.model.SkillModel;

import java.io.Serializable;
import java.util.function.Function;

public class SkillToModelFunction implements Function<Skill, SkillModel>, Serializable {

    @Override
    public SkillModel apply(Skill entity) {
        return SkillModel.builder()
                .id(entity.getId())
                .name(entity.getName())
                .description(entity.getDescription())
                .tags(entity.getTags())
                .favouriteRank(entity.getFavouriteRank())
                .level(entity.getLevel())
                .build();
    }

}
