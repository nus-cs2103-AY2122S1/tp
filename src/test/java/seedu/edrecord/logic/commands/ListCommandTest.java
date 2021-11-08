package seedu.edrecord.logic.commands;

import static seedu.edrecord.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.edrecord.logic.commands.CommandTestUtil.showPersonAtIndex;
import static seedu.edrecord.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.edrecord.testutil.TypicalModules.getTypicalModuleSystem;
import static seedu.edrecord.testutil.TypicalPersons.getTypicalEdRecord;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.edrecord.model.Model;
import seedu.edrecord.model.ModelManager;
import seedu.edrecord.model.UserPrefs;

/**
 * Contains integration tests (interaction with the Model) and unit tests for ListCommand.
 */
public class ListCommandTest {

    private Model model;
    private Model expectedModel;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalEdRecord(), getTypicalModuleSystem(), new UserPrefs());
        expectedModel = new ModelManager(model.getEdRecord(), model.getModuleSystem(), new UserPrefs());
    }

    @Test
    public void execute_listIsNotFiltered_showsSameList() {
        assertCommandSuccess(new ListCommand(), model, ListCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_listIsFiltered_showsEverything() {
        showPersonAtIndex(model, INDEX_FIRST_PERSON);
        assertCommandSuccess(new ListCommand(), model, ListCommand.MESSAGE_SUCCESS, expectedModel);
    }
}
