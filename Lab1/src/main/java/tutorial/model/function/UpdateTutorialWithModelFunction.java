package tutorial.model.function;

import lombok.SneakyThrows;
import tutorial.entity.Tutorial;
import tutorial.model.TutorialEditModel;

import java.io.Serializable;
import java.util.function.BiFunction;

/**
 * Returns new instance of {@link Tutorial} based on provided value and updated with values from
 * {@link TutorialEditModel}.
 */
public class UpdateTutorialWithModelFunction implements BiFunction<Tutorial, TutorialEditModel, Tutorial>, Serializable {

    @Override
    @SneakyThrows
    public Tutorial apply(Tutorial entity, TutorialEditModel request) {
        return Tutorial.builder()
                .id(entity.getId())
                .title(request.getTitle())
                .duration(request.getDuration())
                .description(request.getDescription())
                .skill(entity.getSkill())
                .creationDateTime(entity.getCreationDateTime())
                .build();
    }

}
