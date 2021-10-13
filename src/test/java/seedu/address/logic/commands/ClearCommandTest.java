package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalPersons.CS2103;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;

public class ClearCommandTest {

    @Test
    public void execute_emptyAddressBook_success() {
        Model model = new ModelManager();
        Model expectedModel = new ModelManager();

        assertCommandSuccess(new ClearCommand(), model, ClearCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_nonEmptyAddressBook_success() {
        Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        expectedModel.setAddressBook(new AddressBook());

        assertCommandSuccess(new ClearCommand(), model, ClearCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_addressBookWithFolders_success() {
        AddressBook addressBook = getTypicalAddressBook();
        addressBook.addFolder(CS2103);
        Model model = new ModelManager(addressBook, new UserPrefs());
        AddressBook expectedAddressBook = new AddressBook();
        expectedAddressBook.addFolder(CS2103);
        Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        expectedModel.setAddressBook(expectedAddressBook);

        assertCommandSuccess(new ClearCommand(),
                model,
                new CommandResult(ClearCommand.MESSAGE_SUCCESS),
                expectedModel);
    }

    @Test
    public void copyFolders_success() {
        AddressBook addressBook = getTypicalAddressBook();
        addressBook.addFolder(CS2103);
        Model model = new ModelManager(addressBook, new UserPrefs());
        Model actualModel = new ModelManager(AddressBook.withFolders(model), new UserPrefs());
        AddressBook addressBookWithFolder = new AddressBook();
        addressBookWithFolder.addFolder(CS2103);
        Model expectedModel = new ModelManager(addressBookWithFolder, new UserPrefs());
        assertEquals(actualModel, expectedModel);
    }

}
