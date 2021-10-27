package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalFolders.CCA;
import static seedu.address.testutil.TypicalFolders.CS2103;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;

public class ClearFolderCommandTest {

    @Test
    public void execute_emptyAddressBook_success() {
        Model model = new ModelManager();
        Model expectedModel = new ModelManager();

        assertCommandSuccess(new ClearFoldersCommand(), model, ClearFoldersCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_addressBookEmptyFolderList_success() {
        Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());

        assertCommandSuccess(new ClearFoldersCommand(), model, ClearFoldersCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_addressBookFoldersRemoved_success() {
        AddressBook addressBook = getTypicalAddressBook();
        addressBook.addFolder(CS2103);
        addressBook.addFolder(CCA);
        Model model = new ModelManager(addressBook, new UserPrefs());
        Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        expectedModel.setAddressBook(getTypicalAddressBook());

        assertCommandSuccess(new ClearFoldersCommand(), model, ClearFoldersCommand.MESSAGE_SUCCESS, expectedModel);
    }

}
