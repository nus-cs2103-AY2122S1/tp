package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.lesson.Lesson;
import seedu.address.model.person.Student;

public class UnenrollCommandTest {

    private Model model;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    }

    @Test
    public void execute_validUnenrollment_success() {
        String code = "Mathematics-S2-Tue-0930";
        UnenrollCommand unenrollCommand = new UnenrollCommand(INDEX_SECOND_PERSON, code);

        Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        Student studentToUnenroll = expectedModel.getFilteredPersonList().get(INDEX_SECOND_PERSON.getZeroBased());

        Lesson testLessonOne = expectedModel.searchLessons(code);
        testLessonOne.removeStudent(studentToUnenroll);
        String expectedMessage = String.format(UnenrollCommand.MESSAGE_UNENROLL_STUDENT_SUCCESS,
                studentToUnenroll.getName(),
                testLessonOne);

        assertCommandSuccess(unenrollCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void equals() {
        UnenrollCommand unenrollFirstCommand = new UnenrollCommand(INDEX_FIRST_PERSON, "l/Science-P5-Wed-1230");
        UnenrollCommand unenrollSecondCommand = new UnenrollCommand(INDEX_SECOND_PERSON, "l/Science-P5-Wed-1230");

        // same object -> returns true
        assertTrue(unenrollFirstCommand.equals(unenrollFirstCommand));

        // same values -> returns true
        UnenrollCommand enrollFirstCommandCopy = new UnenrollCommand(INDEX_FIRST_PERSON, "l/Science-P5-Wed-1230");
        assertTrue(unenrollFirstCommand.equals(enrollFirstCommandCopy));

        // different types -> returns false
        assertFalse(unenrollFirstCommand.equals(1));

        // null -> returns false
        assertFalse(unenrollFirstCommand.equals(null));

        // different person -> returns false
        assertFalse(unenrollFirstCommand.equals(unenrollSecondCommand));
    }
}
