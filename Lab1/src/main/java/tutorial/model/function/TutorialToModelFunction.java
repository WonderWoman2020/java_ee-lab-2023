package tutorial.model.function;


import tutorial.entity.Tutorial;
import tutorial.model.TutorialModel;

import java.io.Serializable;
import java.util.function.Function;

public class TutorialToModelFunction implements Function<Tutorial, TutorialModel>, Serializable {

    @Override
    public TutorialModel apply(Tutorial entity) {
        return TutorialModel.builder()
                .author(entity.getAuthor().getNick())
                .skill(entity.getSkill().getName())
                .title(entity.getTitle())
                .description(entity.getDescription())
                .duration(entity.getDuration())
                .build();
    }

}
