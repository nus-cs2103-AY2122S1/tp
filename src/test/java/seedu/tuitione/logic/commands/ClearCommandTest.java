package seedu.tuitione.logic.commands;

import static seedu.tuitione.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.tuitione.testutil.TypicalStudents.getTypicalTuitione;

import org.junit.jupiter.api.Test;

import seedu.tuitione.model.Model;
import seedu.tuitione.model.ModelManager;
import seedu.tuitione.model.Tuitione;
import seedu.tuitione.model.UserPrefs;

public class ClearCommandTest {

    @Test
    public void execute_emptyTuitione_success() {
        Model model = new ModelManager();
        Model expectedModel = new ModelManager();

        assertCommandSuccess(new ClearCommand(), model, ClearCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_nonEmptyTuitione_success() {
        Model model = new ModelManager(getTypicalTuitione(), new UserPrefs());
        Model expectedModel = new ModelManager(getTypicalTuitione(), new UserPrefs());
        expectedModel.setTuitione(new Tuitione());

        assertCommandSuccess(new ClearCommand(), model, ClearCommand.MESSAGE_SUCCESS, expectedModel);
    }

}
