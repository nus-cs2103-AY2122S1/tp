package tutoraid.logic.commands;

import static tutoraid.commons.core.Messages.MESSAGE_LESSONS_LISTED_OVERVIEW;
import static tutoraid.commons.core.Messages.MESSAGE_STUDENTS_LISTED_OVERVIEW;
import static tutoraid.logic.commands.CommandTestUtil.assertCommandSuccess;

import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import tutoraid.model.Model;
import tutoraid.model.ModelManager;
import tutoraid.model.UserPrefs;
import tutoraid.model.lesson.LessonNameContainsSubstringsPredicate;
import tutoraid.model.student.NameContainsSubstringsPredicate;
import tutoraid.testutil.TypicalLessons;
import tutoraid.testutil.TypicalStudents;

/**
 * Contains integration tests (interaction with the Model) for {@code FindCommand}.
 */
public class FindCommandIntegrationTest {

    private Model model;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(TypicalStudents.getTypicalStudentBook(),
                TypicalLessons.getTypicalLessonBook(), new UserPrefs());
    }

    /**
     * Parses {@code userInput} into a {@code NameContainsSubstringsPredicate}.
     */
    private NameContainsSubstringsPredicate prepareStudentPredicate(String userInput) {
        return new NameContainsSubstringsPredicate(Arrays.asList(userInput.split("\\s+")));
    }

    /**
     * Parses {@code userInput} into a {@code NameContainsSubstringsPredicate}.
     */
    private LessonNameContainsSubstringsPredicate prepareLessonPredicate(String userInput) {
        return new LessonNameContainsSubstringsPredicate(Arrays.asList(userInput.split("\\s+")));
    }

    @Test
    public void execute_newStudentFind_success() {
        String keyWord = new String("elle");
        FindStudentCommand command = new FindStudentCommand(prepareStudentPredicate(keyWord));

        Model expectedModel = new ModelManager(model.getStudentBook(), model.getLessonBook(), new UserPrefs());
        expectedModel.updateFilteredStudentList(prepareStudentPredicate(keyWord));

        assertCommandSuccess(command, model, String.format(MESSAGE_STUDENTS_LISTED_OVERVIEW, 1), expectedModel);
    }

    @Test
    public void execute_newLessonFind_success() {
        String keyWord = new String("maths");
        FindLessonCommand command = new FindLessonCommand(prepareLessonPredicate(keyWord));

        Model expectedModel = new ModelManager(model.getStudentBook(), model.getLessonBook(), new UserPrefs());
        expectedModel.updateFilteredLessonList(prepareLessonPredicate(keyWord));

        assertCommandSuccess(command, model, String.format(MESSAGE_LESSONS_LISTED_OVERVIEW, 1), expectedModel);
    }

}

