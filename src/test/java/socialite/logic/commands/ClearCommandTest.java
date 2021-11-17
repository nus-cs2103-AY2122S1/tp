package socialite.logic.commands;

import org.junit.jupiter.api.Test;

import socialite.model.CommandHistory;
import socialite.model.ContactList;
import socialite.model.Model;
import socialite.model.ModelManager;
import socialite.model.UserPrefs;
import socialite.testutil.TypicalPersons;

public class ClearCommandTest {

    @Test
    public void execute_emptyContactList_success() {
        Model model = new ModelManager();
        Model expectedModel = new ModelManager();

        CommandTestUtil.assertCommandSuccess(new ClearCommand(), model, ClearCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_nonEmptyContactList_success() {
        Model model =
                new ModelManager(
                        TypicalPersons.getTypicalContactList(), new UserPrefs(), new CommandHistory());
        Model expectedModel =
                new ModelManager(
                        TypicalPersons.getTypicalContactList(), new UserPrefs(), new CommandHistory());
        expectedModel.setContactList(new ContactList());

        CommandTestUtil.assertCommandSuccess(new ClearCommand(), model, ClearCommand.MESSAGE_SUCCESS, expectedModel);
    }

}
