package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.lesson.Lesson;
import seedu.address.model.lesson.LessonCode;
import seedu.address.model.person.Student;

public class EnrollCommandTest {

    private Model model;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    }

    @Test
    public void execute_validEnrollment_success() throws CommandException {
        LessonCode code = new LessonCode("Science-P2-Wed-1230");
        EnrollCommand enrollCommand = new EnrollCommand(INDEX_FIRST_PERSON, code.value);

        Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        Student studentToEnroll = expectedModel.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());

        Lesson testLesson = expectedModel.searchLessons(code).get();
        testLesson.addStudent(studentToEnroll);
        String expectedMessage = String.format(EnrollCommand.MESSAGE_SUCCESS, studentToEnroll.getName(), testLesson);

        assertCommandSuccess(enrollCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void equals() {
        EnrollCommand enrollFirstCommand = new EnrollCommand(INDEX_FIRST_PERSON, "l/Science-P5-Wed-1230");
        EnrollCommand enrollSecondCommand = new EnrollCommand(INDEX_SECOND_PERSON, "l/Science-P5-Wed-1230");

        // same object -> returns true
        assertEquals(enrollFirstCommand, enrollFirstCommand);

        // same values -> returns true
        EnrollCommand enrollFirstCommandCopy = new EnrollCommand(INDEX_FIRST_PERSON, "l/Science-P5-Wed-1230");
        assertEquals(enrollFirstCommand, enrollFirstCommandCopy);

        // different types -> returns false
        assertNotEquals(1, enrollFirstCommand);

        // null -> returns false
        assertNotEquals(null, enrollFirstCommand);

        // different person -> returns false
        assertNotEquals(enrollFirstCommand, enrollSecondCommand);
    }
}
