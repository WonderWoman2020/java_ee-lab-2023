package tutorial.dto.function;

import tutorial.dto.PatchTutorialRequest;
import tutorial.entity.Tutorial;

import java.util.function.BiFunction;

public class UpdateTutorialWithRequestFunction implements BiFunction<Tutorial, PatchTutorialRequest, Tutorial> {

    @Override
    public Tutorial apply(Tutorial entity, PatchTutorialRequest request) {
        return Tutorial.builder()
                .id(entity.getId())
                .title(request.getTitle())
                .description(request.getDescription())
                .duration(request.getDuration())
                .skill(entity.getSkill())
                .build();
    }
}
