package seedu.programmer.logic.commands;

import static seedu.programmer.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.programmer.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.programmer.testutil.TypicalIndexes.NUMBER_SECOND_LAB;
import static seedu.programmer.testutil.TypicalLabs.LAB_6;
import static seedu.programmer.testutil.TypicalLabs.getTypicalLabList;
import static seedu.programmer.testutil.TypicalStudents.getTypicalProgrammerError;

import org.junit.jupiter.api.Test;

import seedu.programmer.model.Model;
import seedu.programmer.model.ModelManager;
import seedu.programmer.model.UserPrefs;
import seedu.programmer.model.student.Lab;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code DeleteLabCommand}.
 */
public class DeleteLabCommandTest {

    private Model model = new ModelManager(getTypicalProgrammerError(), new UserPrefs());

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
}
