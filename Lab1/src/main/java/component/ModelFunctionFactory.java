package component;

import jakarta.enterprise.context.ApplicationScoped;
import skill.model.function.SkillToModelFunction;
import skill.model.function.SkillsToModelFunction;
import tutorial.model.function.ModelToTutorialFunction;
import tutorial.model.function.TutorialToModelFunction;
import tutorial.model.function.TutorialsToModelFunction;

import java.util.function.Function;

/**
 * Factor for creating {@link Function} implementation for converting between various objects used in different layers.
 * Instead of injecting multiple function objects single factory is injected.
 */
@ApplicationScoped
public class ModelFunctionFactory {

    public SkillsToModelFunction skillsToModel() {
        return new SkillsToModelFunction();
    }

    public SkillToModelFunction skillToModel() {return new SkillToModelFunction();}

    public TutorialsToModelFunction tutorialsToModel() {
        return new TutorialsToModelFunction();
    }

    public TutorialToModelFunction tutorialToModel() {return new TutorialToModelFunction();}

    public ModelToTutorialFunction modelToTutorial() {return new ModelToTutorialFunction();}

}
