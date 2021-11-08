package seedu.address.logic.commands.add;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.preparePredicate;
import static seedu.address.testutil.TypicalModules.getTypicalModBook;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.module.Module;
import seedu.address.model.module.ModuleCode;
import seedu.address.model.module.exam.Exam;
import seedu.address.model.module.lesson.Lesson;
import seedu.address.model.module.predicates.HasModuleCodePredicate;
import seedu.address.testutil.builders.ExamBuilder;
import seedu.address.testutil.builders.LessonBuilder;
import seedu.address.testutil.builders.ModuleBuilder;

/**
 * Contains integration tests (interaction with the Model) for {@code AddCommand}.
 */
public class AddCommandIntegrationTest {

    private Model model;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalModBook(), new UserPrefs());
    }

    @Test
    public void execute_newModule_success() {
        Module validModule = new ModuleBuilder().withCode("CS0202").build();

        Model expectedModel = new ModelManager(model.getModBook(), new UserPrefs());
        expectedModel.addModule(validModule);

        assertCommandSuccess(new AddModCommand(validModule), model,
                String.format(AddModCommand.MESSAGE_SUCCESS, validModule), expectedModel);
    }

    @Test
    public void execute_newLesson_success() {
        Lesson validLesson = new LessonBuilder().build();
        Module validModule = new ModuleBuilder().build();
        ModuleCode validModuleCode = validModule.getCode();
        HasModuleCodePredicate predicate = preparePredicate(validModuleCode.toString());
        model.updateFilteredModuleList(predicate);

        Model expectedModel = new ModelManager(model.getModBook(), new UserPrefs());
        expectedModel.addLessonToModule(validModule, validLesson);
        expectedModel.updateFilteredModuleList(predicate);
        String expectedMessage = String.format(AddLessonCommand.MESSAGE_SUCCESS, validLesson);

        assertCommandSuccess(new AddLessonCommand(validLesson), model,
                expectedMessage, expectedModel);
    }

    @Test
    public void execute_newExam_success() {
        Exam validExam = new ExamBuilder().build();
        Module validModule = new ModuleBuilder().build();
        ModuleCode validModuleCode = validModule.getCode();
        HasModuleCodePredicate predicate = preparePredicate(validModuleCode.toString());
        model.updateFilteredModuleList(predicate);

        Model expectedModel = new ModelManager(model.getModBook(), new UserPrefs());
        expectedModel.addExamToModule(validModule, validExam);
        expectedModel.updateFilteredModuleList(predicate);
        String expectedMessage = String.format(AddExamCommand.MESSAGE_SUCCESS, validExam);

        assertCommandSuccess(new AddExamCommand(validExam), model,
                expectedMessage, expectedModel);
    }

    @Test
    public void execute_duplicateModule_throwsCommandException() {
        Module moduleInList = model.getModBook().getModuleList().get(0);
        assertCommandFailure(new AddModCommand(moduleInList), model, AddModCommand.MESSAGE_DUPLICATE_MODULE);
    }

    @Test
    public void execute_duplicateLesson_throwsCommandException() {
        Module moduleInList = model.getModBook().getModuleList().get(0);
        Lesson lessonInList = moduleInList.getLessons().get(0);
        model.updateFilteredModuleList(preparePredicate(moduleInList.getCode().toString()));
        assertCommandFailure(new AddLessonCommand(lessonInList), model,
                AddLessonCommand.MESSAGE_DUPLICATE_LESSON);
    }

    @Test
    public void execute_duplicateExam_throwsCommandException() {
        Module moduleInList = model.getModBook().getModuleList().get(0);
        Exam examInList = moduleInList.getExams().get(0);
        model.updateFilteredModuleList(preparePredicate(moduleInList.getCode().toString()));
        assertCommandFailure(new AddExamCommand(examInList), model, AddExamCommand.MESSAGE_DUPLICATE_EXAM);
    }
}
