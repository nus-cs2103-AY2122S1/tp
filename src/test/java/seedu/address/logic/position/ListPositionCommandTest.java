package seedu.address.logic.position;

import static seedu.address.logic.position.CommandTestUtil.assertListCommandSuccess;
import static seedu.address.logic.position.CommandTestUtil.showPositionAtIndex;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_POSITION;
import static seedu.address.testutil.TypicalPersons.getTypicalHrManager;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;

/**
 * Contains integration tests (interaction with the Model) and unit tests for ListPositionCommand.
 */
public class ListPositionCommandTest {

    private Model model;
    private Model expectedModel;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalHrManager(), new UserPrefs());
        expectedModel = new ModelManager(model.getHrManager(), new UserPrefs());
    }

    @Test
    public void execute_listIsNotFiltered_showsSameList() {
        assertListCommandSuccess(new ListPositionCommand(), model, ListPositionCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_listIsFiltered_showsEverything() {
        showPositionAtIndex(model, INDEX_FIRST_POSITION);
        assertListCommandSuccess(new ListPositionCommand(), model, ListPositionCommand.MESSAGE_SUCCESS, expectedModel);
    }
}
