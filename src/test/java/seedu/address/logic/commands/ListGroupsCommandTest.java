package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalStudents.getTypicalCsBook;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;

/**
 * Contains integration tests (interaction with the Model) and unit tests for ListGroupsCommand.
 */
public class ListGroupsCommandTest {
    private Model model;
    private Model expectedModel;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalCsBook(), new UserPrefs());
        expectedModel = new ModelManager(model.getCsBook(), new UserPrefs());
    }

    @Test
    public void execute_listIsFiltered_success() {
        assertCommandSuccess(new ListGroupsCommand(), model, ListGroupsCommand.MESSAGE_SUCCESS, expectedModel);
    }
}
