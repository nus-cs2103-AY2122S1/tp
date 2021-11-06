package seedu.address.logic.commands.abcommand;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;

import java.nio.file.Path;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.CommandResult;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;

public class AbListCommandTest {
    @Test
    public void equals() {
        AbListCommand abListCommand1 = new AbListCommand();
        AbListCommand abListCommand2 = new AbListCommand();

        // same object -> returns true
        assertTrue(abListCommand1.equals(abListCommand1));

        // different types -> returns false
        assertFalse(abListCommand1.equals(1));

        // null -> returns false
        assertFalse(abListCommand1.equals(null));

        // different client -> returns false
        assertFalse(abListCommand1.equals(abListCommand2));
    }

    @Test
    public void execute_success() {
        Path file1 = Path.of("first.json");
        Path file2 = Path.of("another.json");
        Path file3 = Path.of("last.json");

        UserPrefs userPrefs = new UserPrefs();
        userPrefs.setAddressBookFilePath(file1);
        Model model = new ModelManager(new AddressBook(), userPrefs);
        Model expectedModel = new ModelManager(new AddressBook(), userPrefs);

        model.addAddressBookList(file1);
        model.addAddressBookList(file2);
        model.addAddressBookList(file3);

        expectedModel.addAddressBookList(file1);
        expectedModel.addAddressBookList(file2);
        expectedModel.addAddressBookList(file3);


        AbListCommand command = new AbListCommand();
        String result = String.format(AbListCommand.MESSAGE_SUCCESS, model.getAddressBookListString());
        CommandResult commandResult = new CommandResult(result);

        assertCommandSuccess(command, model, commandResult, expectedModel);
    }
}
