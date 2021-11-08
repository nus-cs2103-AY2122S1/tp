package seedu.address.logic.commands;

import static seedu.address.logic.commands.CustomerCommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.ResetCustomerSortCommand.SHOWING_RESET_MESSAGE;
import static seedu.address.testutil.TypicalCustomers.getTypicalRhrhCustomers;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.Rhrh;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.customer.CustomerComparator;

public class ResetCustomerSortCommandTest {

    private final Model model = new ModelManager(getTypicalRhrhCustomers(), new UserPrefs());

    @Test
    public void execute_sortsByNameAscending_success() {
        Model expectedModel = new ModelManager(new Rhrh(model.getRhrh()), new UserPrefs());
        expectedModel.getSortableCustomerList().sort(CustomerComparator.getDefaultComparator());

        assertCommandSuccess(new ResetCustomerSortCommand(), model, SHOWING_RESET_MESSAGE, expectedModel);
    }
}
