package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.testutil.TypicalModules;

/**
 * Contains integration tests (interaction with the Model) and unit tests for HomeCommand.
 */
public class HomeCommandTest {

    private Model model;
    private Model expectedModel;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(TypicalModules.getTypicalBuddy(), new UserPrefs());
        expectedModel = new ModelManager(model.getBuddy(), new UserPrefs());
    }

    @Test
    public void execute_showsEverything() {
        assertCommandSuccess(new HomeCommand(), model, HomeCommand.MESSAGE_SUCCESS, expectedModel);
    }
}
