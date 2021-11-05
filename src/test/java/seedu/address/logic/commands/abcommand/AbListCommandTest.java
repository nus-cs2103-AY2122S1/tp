package seedu.address.logic.commands.abcommand;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;

import java.nio.file.Path;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.address.logic.commands.CommandResult;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;

public class AbListCommandTest {
    @TempDir
    public Path testFolder;

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
        Path file1 = testFolder.resolve("first.json");
        Path file2 = testFolder.resolve("another.json");
        Path file3 = testFolder.resolve("last.json");

        UserPrefs userPrefs = new UserPrefs();
        userPrefs.setAddressBookDirectory(testFolder);
        Model model = new ModelManager(new AddressBook(), userPrefs);
        Model expectedModel = new ModelManager(new AddressBook(), userPrefs);

        model.addAddressBookList(file1);
        model.addAddressBookList(file2);
        model.addAddressBookList(file3);

        AbListCommand command = new AbListCommand();
        String result = String.format(AbListCommand.MESSAGE_SUCCESS, "\n-first" + "\n-another" + "\n-last");
        CommandResult commandResult = new CommandResult(result);

        assertCommandSuccess(command, model, commandResult, expectedModel);
    }
}
