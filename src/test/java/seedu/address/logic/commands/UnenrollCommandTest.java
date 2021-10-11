package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
import static seedu.address.testutil.TypicalTuition.getTypicalAddressBook;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.lesson.Lesson;
import seedu.address.model.lesson.LessonCode;
import seedu.address.model.person.Student;

public class UnenrollCommandTest {

    private Model model;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    }

    @Test
    public void execute_validUnenrollment_success() {
        LessonCode code = new LessonCode("Mathematics-S2-Tue-0930");
        UnenrollCommand unenrollCommand = new UnenrollCommand(INDEX_SECOND_PERSON, code.value);

        Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        Student studentToUnenroll = expectedModel.getFilteredPersonList().get(INDEX_SECOND_PERSON.getZeroBased());

        Lesson testLesson = expectedModel.searchLessons(code).get();
        testLesson.removeStudent(studentToUnenroll);
        String expectedMessage = String.format(UnenrollCommand.MESSAGE_UNENROLL_STUDENT_SUCCESS,
                studentToUnenroll.getName(),
                testLesson);

        assertCommandSuccess(unenrollCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void equals() {
        UnenrollCommand unenrollFirstCommand = new UnenrollCommand(INDEX_FIRST_PERSON, "l/Science-P5-Wed-1230");
        UnenrollCommand unenrollSecondCommand = new UnenrollCommand(INDEX_SECOND_PERSON, "l/Science-P5-Wed-1230");

        // same object -> returns true
        assertEquals(unenrollFirstCommand, unenrollFirstCommand);

        // same values -> returns true
        UnenrollCommand enrollFirstCommandCopy = new UnenrollCommand(INDEX_FIRST_PERSON, "l/Science-P5-Wed-1230");
        assertEquals(unenrollFirstCommand, enrollFirstCommandCopy);

        // different types -> returns false
        assertNotEquals(1, unenrollFirstCommand);

        // null -> returns false
        assertNotEquals(null, unenrollFirstCommand);

        // different person -> returns false
        assertNotEquals(unenrollFirstCommand, unenrollSecondCommand);
    }
}
