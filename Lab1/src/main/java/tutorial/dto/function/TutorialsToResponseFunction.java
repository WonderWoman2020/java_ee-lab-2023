package tutorial.dto.function;

import tutorial.dto.GetTutorialsResponse;
import tutorial.entity.Tutorial;
import user.dto.GetUsersResponse;


import java.util.List;
import java.util.function.Function;

public class TutorialsToResponseFunction implements Function<List<Tutorial>, GetTutorialsResponse> {
    @Override
    public GetTutorialsResponse apply(List<Tutorial> entities) {
        return GetTutorialsResponse.builder()
                .tutorials(entities.stream()
                        .map(tutorial -> GetTutorialsResponse.Tutorial.builder()
                                .id(tutorial.getId())
                                .title(tutorial.getTitle())
                                .build())
                        .toList())
                .build();
    }
}
