package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalGroups.getTypicalAddressBookWithGroups;
import static seedu.address.testutil.TypicalStudentsWithoutGroups.getTypicalAddressBookWithoutGroups;

import org.junit.jupiter.api.Test;

import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;

public class ClearGroupsCommandTest {

    @Test
    public void execute_emptyAddressBook_success() {
        Model model = new ModelManager();
        Model expectedModel = new ModelManager();

        assertCommandSuccess(new ClearGroupsCommand(), model, ClearGroupsCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_nonEmptyAddressBook_success() {
        Model model = new ModelManager(getTypicalAddressBookWithGroups(), new UserPrefs());
        Model expectedModel = new ModelManager(getTypicalAddressBookWithoutGroups(), new UserPrefs());

        AddressBook expectedAddressBook = new AddressBook();
        expectedAddressBook.setStudents(expectedModel.getFilteredStudentList());
        expectedModel.setAddressBook(expectedAddressBook);

        assertCommandSuccess(new ClearGroupsCommand(), model, ClearGroupsCommand.MESSAGE_SUCCESS, expectedModel);
    }

}
