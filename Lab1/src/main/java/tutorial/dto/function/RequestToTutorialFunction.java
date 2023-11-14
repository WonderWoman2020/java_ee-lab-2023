package tutorial.dto.function;

import skill.entity.Skill;
import tutorial.dto.PutTutorialRequest;
import tutorial.entity.Tutorial;

import java.util.Map;
import java.util.UUID;
import java.util.function.BiFunction;

public class RequestToTutorialFunction implements BiFunction<Map<String, UUID>, PutTutorialRequest, Tutorial> {

    @Override
    public Tutorial apply(Map<String, UUID> uuids, PutTutorialRequest request) {
        return Tutorial.builder()
                .id(uuids.get("tutorialId"))
                .title(request.getTitle())
                .description(request.getDescription())
                .duration(request.getDuration())
                .skill(Skill.builder()
                        .id(uuids.get("skillId"))
                        .build())
                .build();
    }
}
