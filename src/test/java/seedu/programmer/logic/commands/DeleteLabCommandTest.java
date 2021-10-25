package seedu.programmer.logic.commands;

import org.junit.jupiter.api.Test;
import seedu.programmer.commons.core.Messages;
import seedu.programmer.commons.core.index.Index;
import seedu.programmer.model.Model;
import seedu.programmer.model.ModelManager;
import seedu.programmer.model.UserPrefs;
import seedu.programmer.model.student.Lab;
import seedu.programmer.model.student.Student;

import static org.junit.jupiter.api.Assertions.*;
import static seedu.programmer.logic.commands.CommandTestUtil.*;
import static seedu.programmer.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.programmer.testutil.TypicalIndexes.*;
import static seedu.programmer.testutil.TypicalLabs.LAB6;
import static seedu.programmer.testutil.TypicalStudents.getTypicalProgrammerError;
import static seedu.programmer.testutil.TypicalLabs.getTypicalLabList;

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
        //expectedModel.deleteLab(labToDelete);

        assertCommandSuccess(deleteLabCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidTitle_failure() {
        Lab labToDelete = LAB6;
        DeleteLabCommand deleteLabCommand = new DeleteLabCommand(labToDelete);

        String expectedMessage = String.format(DeleteLabCommand.MESSAGE_LAB_DOES_NOT_EXIST, labToDelete);

        assertCommandFailure(deleteLabCommand, model, expectedMessage);
    }
}
