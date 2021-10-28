package seedu.tuitione.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static seedu.tuitione.commons.core.Messages.MESSAGE_INVALID_LESSON_DISPLAYED_INDEX;
import static seedu.tuitione.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.tuitione.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.tuitione.logic.commands.RosterCommand.MESSAGE_ENROLLED_STUDENT_HEADER;
import static seedu.tuitione.logic.commands.RosterCommand.MESSAGE_ROSTER_LESSON_SUCCESS;
import static seedu.tuitione.testutil.TypicalIndexes.INDEX_FIFTH_LESSON;
import static seedu.tuitione.testutil.TypicalIndexes.INDEX_FIRST_LESSON;
import static seedu.tuitione.testutil.TypicalIndexes.INDEX_FOURTH_LESSON;
import static seedu.tuitione.testutil.TypicalIndexes.INDEX_THIRD_LESSON;
import static seedu.tuitione.testutil.TypicalTuition.getTypicalTuitione;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.tuitione.commons.core.index.Index;
import seedu.tuitione.model.Model;
import seedu.tuitione.model.ModelManager;
import seedu.tuitione.model.UserPrefs;
import seedu.tuitione.model.lesson.Lesson;
import seedu.tuitione.model.lesson.LessonCode;
import seedu.tuitione.model.lesson.LessonIsOfSpecifiedLessonCode;
import seedu.tuitione.model.student.Student;
import seedu.tuitione.model.student.StudentIsOfSpecifiedLessonCode;

public class RosterCommandTest {

    private Model model;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalTuitione(), new UserPrefs());
    }

    @Test
    public void execute_invalidLessonIndex_throwsCommandException() {
        Index invalidIndex = Index.fromOneBased(model.getFilteredLessonList().size() + 1);

        RosterCommand rosterCommand = new RosterCommand(invalidIndex);

        assertCommandFailure(rosterCommand, model, MESSAGE_INVALID_LESSON_DISPLAYED_INDEX);
    }

    @Test
    public void execute_validLessonIndexWithStudents_success() {
        Lesson lesson = model.getFilteredLessonList().get(INDEX_THIRD_LESSON.getZeroBased());
        LessonCode lc = lesson.getLessonCode();
        RosterCommand rosterCommand = new RosterCommand(INDEX_THIRD_LESSON);

        Model expectedModel = new ModelManager(model.getTuitione(), new UserPrefs());
        expectedModel.updateFilteredLessonList(new LessonIsOfSpecifiedLessonCode(lc));
        expectedModel.updateFilteredStudentList(new StudentIsOfSpecifiedLessonCode(lc));
        List<Student> expectedModelFilteredStudents = expectedModel.getFilteredStudentList();

        // Build command success string message
        StringBuilder expectedMessage = new StringBuilder();
        expectedMessage.append(String.format(MESSAGE_ROSTER_LESSON_SUCCESS, lesson.getLessonCode(),
                expectedModelFilteredStudents.size()));

        if (!expectedModelFilteredStudents.isEmpty()) {
            expectedMessage.append(MESSAGE_ENROLLED_STUDENT_HEADER);
            expectedModelFilteredStudents.stream()
                    .map(s -> s.getName().fullName)
                    .sorted()
                    .forEach(n -> expectedMessage.append(n).append(", "));
            expectedMessage.delete(expectedMessage.length() - 2, expectedMessage.length());
        }

        assertCommandSuccess(rosterCommand, model, expectedMessage.toString(), expectedModel);
    }

    @Test
    public void execute_validLessonIndexWithoutStudents_success() {
        Lesson lesson = model.getFilteredLessonList().get(INDEX_FIRST_LESSON.getZeroBased());
        LessonCode lc = lesson.getLessonCode();
        RosterCommand rosterCommand = new RosterCommand(INDEX_FIRST_LESSON);

        Model expectedModel = new ModelManager(model.getTuitione(), new UserPrefs());
        expectedModel.updateFilteredLessonList(new LessonIsOfSpecifiedLessonCode(lc));
        expectedModel.updateFilteredStudentList(new StudentIsOfSpecifiedLessonCode(lc));
        List<Student> expectedModelFilteredStudents = expectedModel.getFilteredStudentList();

        // Build command success string message
        String expectedMessage = String.format(MESSAGE_ROSTER_LESSON_SUCCESS, lesson.getLessonCode(),
                expectedModel.getFilteredStudentList().size());

        assertCommandSuccess(rosterCommand, model, expectedMessage.toString(), expectedModel);
    }

    @Test
    public void equals() {

        RosterCommand rosterCommandOne = new RosterCommand(INDEX_FOURTH_LESSON);
        RosterCommand rosterCommandTwo = new RosterCommand(INDEX_FIFTH_LESSON);

        assertEquals(rosterCommandOne, rosterCommandOne);

        assertEquals(rosterCommandTwo, rosterCommandTwo);

        RosterCommand rosterCommandOneCopy = new RosterCommand(INDEX_FOURTH_LESSON);
        assertEquals(rosterCommandOne, rosterCommandOneCopy);

        RosterCommand rosterCommandTwoCopy = new RosterCommand(INDEX_FIFTH_LESSON);
        assertEquals(rosterCommandTwo, rosterCommandTwoCopy);

        assertNotEquals(rosterCommandOne, rosterCommandTwo);

        assertNotEquals(rosterCommandOne, 1);

        assertNotEquals(rosterCommandOne, null);
    }

}
