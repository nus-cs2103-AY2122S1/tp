package dash.logic.commands.personcommand;

import static dash.logic.commands.CommandTestUtil.assertCommandSuccess;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import dash.logic.commands.CommandTestUtil;
import dash.model.Model;
import dash.model.ModelManager;
import dash.model.UserPrefs;
import dash.model.task.TaskList;
import dash.testutil.TypicalIndexes;
import dash.testutil.TypicalPersons;

/**
 * Contains integration tests (interaction with the Model) and unit tests for ListCommand.
 */
public class ListPeopleCommandTest {

    private Model model;
    private Model expectedModel;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(TypicalPersons.getTypicalAddressBook(), new UserPrefs(), new TaskList());
        expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs(), new TaskList());
    }

    @Test
    public void execute_listIsNotFiltered_showsSameList() {
        assertCommandSuccess(new ListPeopleCommand(), model, ListPeopleCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_listIsFiltered_showsEverything() {
        CommandTestUtil.showPersonAtIndex(model, TypicalIndexes.INDEX_FIRST_PERSON);
        assertCommandSuccess(new ListPeopleCommand(), model, ListPeopleCommand.MESSAGE_SUCCESS, expectedModel);
    }
}
