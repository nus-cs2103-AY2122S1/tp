package seedu.address.logic.commands;

import static seedu.address.logic.commands.EmployeeCommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.ResetEmployeeSortCommand.SHOWING_RESET_MESSAGE;
import static seedu.address.testutil.TypicalEmployees.getTypicalAddressBookEmployees;

import org.junit.jupiter.api.Test;

import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.employee.EmployeeComparator;

public class ResetEmployeeSortCommandTest {

    private final Model model = new ModelManager(getTypicalAddressBookEmployees(), new UserPrefs());

    @Test
    public void execute_sortsByNameAscending_success() {
        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.getSortableEmployeeList().sort(EmployeeComparator.getDefaultComparator());

        assertCommandSuccess(new ResetEmployeeSortCommand(), model, SHOWING_RESET_MESSAGE, expectedModel);
    }
}
