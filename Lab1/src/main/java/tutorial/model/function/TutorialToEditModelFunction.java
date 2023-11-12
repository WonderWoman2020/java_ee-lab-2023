package tutorial.model.function;


import tutorial.entity.Tutorial;
import tutorial.model.TutorialEditModel;

import java.io.Serializable;
import java.util.function.Function;

public class TutorialToEditModelFunction implements Function<Tutorial, TutorialEditModel>, Serializable {

    @Override
    public TutorialEditModel apply(Tutorial entity) {
        return TutorialEditModel.builder()
                .title(entity.getTitle())
                .description(entity.getDescription())
                .duration(entity.getDuration())
                .build();
    }

}
