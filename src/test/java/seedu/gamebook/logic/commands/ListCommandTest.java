package seedu.gamebook.logic.commands;

import static seedu.gamebook.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.gamebook.logic.commands.CommandTestUtil.showGameEntryAtIndex;
import static seedu.gamebook.testutil.TypicalGameEntries.getTypicalGameBook;
import static seedu.gamebook.testutil.TypicalIndexes.INDEX_FIRST_GAMEENTRY;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.gamebook.model.Model;
import seedu.gamebook.model.ModelManager;
import seedu.gamebook.model.UserPrefs;

/**
 * Contains integration tests (interaction with the Model) and unit tests for ListCommand.
 */
public class ListCommandTest {

    private Model model;
    private Model expectedModel;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalGameBook(), new UserPrefs());
        expectedModel = new ModelManager(model.getGameBook(), new UserPrefs());
    }

    @Test
    public void execute_listIsNotFiltered_showsSameList() {
        assertCommandSuccess(new ListCommand(), model, ListCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_listIsFiltered_showsEverything() {
        showGameEntryAtIndex(model, INDEX_FIRST_GAMEENTRY);
        assertCommandSuccess(new ListCommand(), model, ListCommand.MESSAGE_SUCCESS, expectedModel);
    }
}
