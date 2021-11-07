package seedu.academydirectory.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.academydirectory.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.academydirectory.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.academydirectory.logic.commands.CommandTestUtil.showStudentAtIndex;
import static seedu.academydirectory.testutil.TypicalIndexes.INDEX_FIRST_STUDENT;
import static seedu.academydirectory.testutil.TypicalIndexes.INDEX_SECOND_STUDENT;
import static seedu.academydirectory.testutil.TypicalIndexes.INDEX_THIRD_STUDENT;
import static seedu.academydirectory.testutil.TypicalStudents.getTypicalAcademyDirectory;

import org.junit.jupiter.api.Test;

import seedu.academydirectory.commons.core.Messages;
import seedu.academydirectory.commons.core.index.Index;
import seedu.academydirectory.logic.commands.exceptions.CommandException;
import seedu.academydirectory.model.UserPrefs;
import seedu.academydirectory.model.VersionedModel;
import seedu.academydirectory.model.VersionedModelManager;
import seedu.academydirectory.model.student.Student;

public class ViewCommandTest {
    private final VersionedModel model = new VersionedModelManager(getTypicalAcademyDirectory(), new UserPrefs());

    @Test
    public void valid_view_command() {
        ViewCommand viewCommand1 = new ViewCommand(INDEX_FIRST_STUDENT);
        ViewCommand viewCommand2 = new ViewCommand(INDEX_SECOND_STUDENT);
        ViewCommand viewCommand3 = new ViewCommand(INDEX_THIRD_STUDENT);

        // assert that two view commands are equal if the indices are equal
        assertEquals(viewCommand1, new ViewCommand(INDEX_FIRST_STUDENT));
        assertEquals(viewCommand2, new ViewCommand(INDEX_SECOND_STUDENT));

        // assert that view command is equal to itself
        assertEquals(viewCommand3, viewCommand3);

        // assert that two view commands are not equal if indices are different
        assertNotEquals(viewCommand3, new ViewCommand(INDEX_FIRST_STUDENT));
        assertNotEquals(viewCommand1, viewCommand2);

        // assert that a view command is not equal to a different type
        assertNotEquals(viewCommand2, "Life is good");

        // assert that view command is not equal to null
        assertNotEquals(viewCommand3, null);
    }

    @Test
    public void valid_execution() {
        // view command parse successfully for random student
        ViewCommand viewCommand3 = new ViewCommand(INDEX_SECOND_STUDENT);
        Student studentToGet = model.getFilteredStudentList().get(INDEX_SECOND_STUDENT.getZeroBased());
        String expectedMessage3 = String.format(ViewCommand.MESSAGE_SUCCESS, studentToGet.getName());
        assertCommandSuccess(viewCommand3, model, expectedMessage3, model);

        // view command parses successfully for last student
        Index lastStudent = Index.fromOneBased(model.getFilteredStudentList().size());
        Student studentToGet1 = model.getFilteredStudentList().get(lastStudent.getZeroBased());
        ViewCommand viewCommand4 = new ViewCommand(lastStudent);
        String expectedMessage4 = String.format(ViewCommand.MESSAGE_SUCCESS, studentToGet1.getName());
        assertCommandSuccess(viewCommand4, model, expectedMessage4, model);

        // execution is valid when list is increased
        showStudentAtIndex(model, INDEX_FIRST_STUDENT);
        ViewCommand viewCommand1 = new ViewCommand(INDEX_FIRST_STUDENT);
        Student student1 = model.getFilteredStudentList().get(INDEX_FIRST_STUDENT.getZeroBased());
        String expectedMessage1 = String.format(ViewCommand.MESSAGE_SUCCESS, student1.getName());
        assertCommandSuccess(viewCommand1, model, expectedMessage1, model);
    }

    @Test
    public void invalid_execution() {
        // execution is invalid when list is reduced
        showStudentAtIndex(model, INDEX_FIRST_STUDENT);
        assertTrue(INDEX_SECOND_STUDENT.getZeroBased() < model.getAcademyDirectory().getStudentList().size());
        ViewCommand viewCommand3 = new ViewCommand(INDEX_SECOND_STUDENT);
        assertCommandFailure(viewCommand3, model, Messages.MESSAGE_INVALID_STUDENT_DISPLAYED_INDEX);


        Index lastStudentPlusOne = Index.fromOneBased(model.getFilteredStudentList().size() + 1);
        ViewCommand viewCommand5 = new ViewCommand(lastStudentPlusOne);
        // assert throws if index > size
        assertThrows(CommandException.class, () -> viewCommand5.execute(model));
        assertCommandFailure(viewCommand5, model, Messages.MESSAGE_INVALID_STUDENT_DISPLAYED_INDEX);

        Index validIndex1 = Index.fromOneBased(model.getFilteredStudentList().size() + 100);
        ViewCommand viewCommand1 = new ViewCommand(validIndex1);
        // view command for very high index
        assertThrows(CommandException.class, () -> viewCommand1.execute(model));
    }
}
