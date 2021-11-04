package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.commons.core.Messages.MESSAGE_SUPPLIERS_LISTED_OVERVIEW;
import static seedu.address.logic.commands.SupplierCommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalSuppliers.getTypicalRhrhSuppliers;

import java.util.Arrays;
import java.util.Collections;

import org.junit.jupiter.api.Test;

import javafx.collections.ObservableList;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.supplier.Supplier;
import seedu.address.model.person.supplier.SupplierClassContainsKeywordsPredicate;


public class FindSupplierCommandTest {
    private Model model = new ModelManager(getTypicalRhrhSuppliers(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalRhrhSuppliers(), new UserPrefs());

    @Test
    public void equals() {
        SupplierClassContainsKeywordsPredicate firstPredicate =
                new SupplierClassContainsKeywordsPredicate(Collections.singletonList("first"));
        SupplierClassContainsKeywordsPredicate secondPredicate =
                new SupplierClassContainsKeywordsPredicate(Collections.singletonList("second"));

        FindSupplierCommand findFirstCommand = new FindSupplierCommand(firstPredicate);
        FindSupplierCommand findSecondCommand = new FindSupplierCommand(secondPredicate);

        // same object -> returns true
        assertTrue(findFirstCommand.equals(findFirstCommand));

        // same values -> returns true
        FindSupplierCommand findFirstCommandCopy = new FindSupplierCommand(firstPredicate);
        assertTrue(findFirstCommand.equals(findFirstCommandCopy));

        // different types -> returns false
        assertFalse(findFirstCommand.equals(1));

        // null -> returns false
        assertFalse(findFirstCommand.equals(null));

        // different person -> returns false
        assertFalse(findFirstCommand.equals(findSecondCommand));
    }

    @Test
    public void execute_zeroKeywords_allSuppliersFound() {
        String expectedMessage = String.format(MESSAGE_SUPPLIERS_LISTED_OVERVIEW,
                model.getFilteredSupplierList().size());
        ObservableList<Supplier> suppliersList = model.getFilteredSupplierList();
        SupplierClassContainsKeywordsPredicate predicate = preparePredicate(" ");
        FindSupplierCommand command = new FindSupplierCommand(predicate);
        expectedModel.updateFilteredSupplierList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(suppliersList, model.getFilteredSupplierList());
    }

    @Test
    public void execute_partialDeliveryDetails_suppliersFound() {
        String expectedMessage = String.format(MESSAGE_SUPPLIERS_LISTED_OVERVIEW, 5);
        ObservableList<Supplier> suppliersList = model.getFilteredSupplierList();
        SupplierClassContainsKeywordsPredicate predicate = preparePredicate("oct");
        FindSupplierCommand command = new FindSupplierCommand(predicate);
        expectedModel.updateFilteredSupplierList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(suppliersList, model.getFilteredSupplierList());
    }

    @Test
    public void execute_multipleKeywords_multipleSupplierNamesNotFound() {
        String expectedMessage = String.format(MESSAGE_SUPPLIERS_LISTED_OVERVIEW, 0);
        SupplierClassContainsKeywordsPredicate predicate = preparePredicate("Kurz Elle Kunz");
        FindSupplierCommand command = new FindSupplierCommand(predicate);
        expectedModel.updateFilteredSupplierList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredSupplierList());
    }

    /**
     * Parses {@code userInput} into a {@code NameContainsKeywordsPredicate}.
     */
    private SupplierClassContainsKeywordsPredicate preparePredicate(String userInput) {
        return new SupplierClassContainsKeywordsPredicate(Arrays.asList(userInput.split("\\s+")));
    }
}
