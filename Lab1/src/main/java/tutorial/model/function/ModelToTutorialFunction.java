package tutorial.model.function;

import lombok.SneakyThrows;
import skill.entity.Skill;
import tutorial.entity.Tutorial;
import tutorial.model.TutorialCreateModel;

import java.io.Serializable;
import java.util.function.Function;

/**
 * Converts {@link TutorialCreateModel} to {@link Tutorial}.
 */
public class ModelToTutorialFunction implements Function<TutorialCreateModel, Tutorial>, Serializable {

    @Override
    @SneakyThrows
    public Tutorial apply(TutorialCreateModel model) {
        return Tutorial.builder()
                .id(model.getId())
                .title(model.getTitle())
                .description(model.getDescription())
                .duration(model.getDuration())
                .skill(Skill.builder()
                        .id(model.getSkill()
                                .getId())
                        .build())
                .build();
    }

}
