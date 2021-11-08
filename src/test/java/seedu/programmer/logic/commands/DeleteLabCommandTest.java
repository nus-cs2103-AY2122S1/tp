package seedu.programmer.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.programmer.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.programmer.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.programmer.testutil.TypicalIndexes.NUMBER_SECOND_LAB;
import static seedu.programmer.testutil.TypicalLabs.LAB_6;
import static seedu.programmer.testutil.TypicalLabs.getTypicalLabList;
import static seedu.programmer.testutil.TypicalStudents.getTypicalProgrammerError;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import seedu.programmer.model.Model;
import seedu.programmer.model.ModelManager;
import seedu.programmer.model.ProgrammerError;
import seedu.programmer.model.UserPrefs;
import seedu.programmer.model.student.Lab;
import seedu.programmer.testutil.TypicalLabs;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code DeleteLabCommand}.
 */
public class DeleteLabCommandTest {

    private static Model model;
    private static DeleteLabCommand sampleCommandA;
    private static Lab sampleLabA;

    @BeforeAll
    public static void oneTimeSetUp() {
        model = new ModelManager(getTypicalProgrammerError(), new UserPrefs());
        sampleLabA = TypicalLabs.LAB_1;
        sampleCommandA = new DeleteLabCommand(sampleLabA);
    }

    @Test
    public void execute_validTitle_success() {
        Lab labToDelete = getTypicalLabList().get(NUMBER_SECOND_LAB);
        DeleteLabCommand deleteLabCommand = new DeleteLabCommand(labToDelete);

        String expectedMessage = String.format(DeleteLabCommand.MESSAGE_DEL_LAB_SUCCESS, labToDelete);

        ModelManager expectedModel = new ModelManager(model.getProgrammerError(), new UserPrefs());

        assertCommandSuccess(deleteLabCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidTitle_failure() {
        Lab labToDelete = LAB_6;
        DeleteLabCommand deleteLabCommand = new DeleteLabCommand(labToDelete);

        String expectedMessage = String.format(Lab.MESSAGE_LAB_NOT_EXISTS, labToDelete);

        assertCommandFailure(deleteLabCommand, model, expectedMessage);
    }

    @Test
    public void execute_noStudent_failure() {
        Lab labToDelete = TypicalLabs.LAB_1;
        DeleteLabCommand deleteLabCommand = new DeleteLabCommand(labToDelete);

        String expectedMessage = String.format(DeleteLabCommand.MESSAGE_NO_STUDENT, labToDelete);

        ModelManager emptyModel = new ModelManager(new ProgrammerError(), new UserPrefs());

        assertCommandFailure(deleteLabCommand, emptyModel, expectedMessage);
    }

    @Test
    public void equals_sameLab_returnsTrue() {
        DeleteLabCommand sampleCommandACopy = new DeleteLabCommand(sampleLabA);
        assertEquals(sampleCommandA, sampleCommandACopy);
    }
}
