package tutorial.model.function;

import tutorial.entity.Tutorial;
import tutorial.model.TutorialsModel;

import java.util.List;
import java.util.function.Function;

public class TutorialsToModelFunction implements Function<List<Tutorial>, TutorialsModel> {

    @Override
    public TutorialsModel apply(List<Tutorial> entity) {
        return TutorialsModel.builder()
                .tutorials(entity.stream()
                        .map(skill -> TutorialsModel.Tutorial.builder()
                                .id(skill.getId())
                                .title(skill.getTitle())
                                .build())
                        .toList())
                .build();
    }

}
