package seedu.address.logic.commands;

import static seedu.address.logic.commands.ResetSupplierSortCommand.SHOWING_RESET_MESSAGE;
import static seedu.address.logic.commands.SupplierCommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalSuppliers.getTypicalRhrhSuppliers;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.Rhrh;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.supplier.SupplierComparator;

public class ResetSupplierSortCommandTest {

    private final Model model = new ModelManager(getTypicalRhrhSuppliers(), new UserPrefs());

    @Test
    public void execute_sortsByNameAscending_success() {
        Model expectedModel = new ModelManager(new Rhrh(model.getRhrh()), new UserPrefs());
        expectedModel.getSortableSupplierList().sort(SupplierComparator.getDefaultComparator());

        assertCommandSuccess(new ResetSupplierSortCommand(), model, SHOWING_RESET_MESSAGE, expectedModel);
    }
}
