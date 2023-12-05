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
                        .map(tutorial -> TutorialsModel.Tutorial.builder()
                                .id(tutorial.getId())
                                .title(tutorial.getTitle())
                                //.version(tutorial.getVersion())
                                .creationDateTime(tutorial.getCreationDateTime())
                                .editionDateTime(tutorial.getEditionDateTime())
                                .build())
                        .toList())
                .build();
    }

}
