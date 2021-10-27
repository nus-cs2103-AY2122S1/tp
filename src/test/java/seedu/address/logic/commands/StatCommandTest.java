package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;

/**
 * Contains integration tests (interaction with the Model) and unit tests for ListCommand.
 */
public class StatCommandTest {

    private static final String EXPECTED_MESSAGE = "Tag Count: \n"
        + "[friends] : 2\n"
        + "[owesMoney] : 1\n"
        + "\n"
        + "Type Count: \n"
        + "student : 3\n"
        + "\n"
        + "Tutorial ID Count: \n"
        + "02 : 1\n"
        + "01 : 1\n"
        + "00 : 1\n";

    private Model model;
    private Model expectedModel;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
    }

    @Test
    public void execute_showsExpectedMessage() {
        assertCommandSuccess(new StatCommand(), model, EXPECTED_MESSAGE, expectedModel);
    }

}
