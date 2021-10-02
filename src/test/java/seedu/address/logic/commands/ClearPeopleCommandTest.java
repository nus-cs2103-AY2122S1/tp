package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.personcommand.ClearPeopleCommand;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.task.TaskList;

public class ClearPeopleCommandTest {

    @Test
    public void execute_emptyAddressBook_success() {
        Model model = new ModelManager();
        Model expectedModel = new ModelManager();

        assertCommandSuccess(new ClearPeopleCommand(), model, ClearPeopleCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_nonEmptyAddressBook_success() {
        Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs(), new TaskList());
        Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs(), new TaskList());
        expectedModel.setAddressBook(new AddressBook());

        assertCommandSuccess(new ClearPeopleCommand(), model, ClearPeopleCommand.MESSAGE_SUCCESS, expectedModel);
    }

}
