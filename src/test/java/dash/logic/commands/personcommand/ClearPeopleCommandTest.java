package dash.logic.commands.personcommand;

import static dash.logic.commands.CommandTestUtil.assertCommandSuccess;

import org.junit.jupiter.api.Test;

import dash.model.AddressBook;
import dash.model.Model;
import dash.model.ModelManager;
import dash.model.UserPrefs;
import dash.model.task.TaskList;
import dash.testutil.TypicalPersons;

public class ClearPeopleCommandTest {

    @Test
    public void execute_emptyAddressBook_success() {
        Model model = new ModelManager();
        Model expectedModel = new ModelManager();

        assertCommandSuccess(new ClearPeopleCommand(), model, ClearPeopleCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_nonEmptyAddressBook_success() {
        Model model = new ModelManager(TypicalPersons.getTypicalAddressBook(), new UserPrefs(), new TaskList());
        Model expectedModel = new ModelManager(TypicalPersons.getTypicalAddressBook(), new UserPrefs(), new TaskList());
        expectedModel.setAddressBook(new AddressBook());

        assertCommandSuccess(new ClearPeopleCommand(), model, ClearPeopleCommand.MESSAGE_SUCCESS, expectedModel);
    }

}
