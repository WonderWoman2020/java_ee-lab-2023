package tutorial.dto.function;

import tutorial.dto.GetTutorialResponse;
import tutorial.entity.Tutorial;

import java.util.function.Function;

public class TutorialToResponseFunction implements Function<Tutorial, GetTutorialResponse> {
    @Override
    public GetTutorialResponse apply(Tutorial entity) {
        return GetTutorialResponse.builder()
                .id(entity.getId())
                .title(entity.getTitle())
                .description(entity.getDescription())
                .duration(entity.getDuration())
                .skill(GetTutorialResponse.Skill.builder()
                        .id(entity.getSkill().getId())
                        .name(entity.getSkill().getName())
                        .build())
                .build();
    }
}
