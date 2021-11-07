package seedu.address.logic.commands.vendor;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static seedu.address.commons.core.Messages.MESSAGE_VENDORS_LISTED_OVERVIEW;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.vendor.TypicalVendors.getTypicalVendorBook;

import java.util.Collections;
import java.util.Optional;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.guest.Archive;
import seedu.address.model.guest.GuestBook;
import seedu.address.model.vendor.VendorPredicate;

public class FilterVendorCommandTest {

    private Model model = new ModelManager(new GuestBook(), getTypicalVendorBook(), new UserPrefs(), new Archive());
    private Model expectedModel =
            new ModelManager(new GuestBook(), getTypicalVendorBook(), new UserPrefs(), new Archive());

    @Test
    public void equals() {
        VendorPredicate firstPredicate =
                new VendorPredicate(Optional.of("001"), Optional.empty(), Optional.empty(), Optional.empty(),
                        Optional.empty(), Optional.empty(), Optional.empty(), Optional.empty(),
                        Optional.empty());
        VendorPredicate secondPredicate =
                new VendorPredicate(Optional.of("002"), Optional.empty(), Optional.empty(), Optional.empty(),
                        Optional.empty(), Optional.empty(), Optional.empty(), Optional.empty(),
                        Optional.empty());

        FilterVendorCommand filterFirstCommand = new FilterVendorCommand(firstPredicate);
        FilterVendorCommand filterSecondCommand = new FilterVendorCommand(secondPredicate);

        // same object -> returns true
        assertTrue(filterFirstCommand.equals(filterFirstCommand));

        // same values -> returns true
        FilterVendorCommand findFirstCommandCopy = new FilterVendorCommand(firstPredicate);
        assertTrue(filterFirstCommand.equals(findFirstCommandCopy));

        // different types -> returns false
        assertFalse(filterFirstCommand.equals(1));

        // null -> returns false
        assertFalse(filterFirstCommand.equals(null));

        // different person -> returns false
        assertFalse(filterFirstCommand.equals(filterSecondCommand));
    }

    @Test
    public void execute_noKeywords_noGuestFiltered() {
        String expectedMessage = String.format(MESSAGE_VENDORS_LISTED_OVERVIEW, 0);
        VendorPredicate predicate = preparePredicate("  ");
        FilterVendorCommand command = new FilterVendorCommand(predicate);
        expectedModel.updateFilteredVendorList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredVendorList());
    }

    /**
     * Parses {@code userInput} into a {@code VendorPredicate}.
     */
    private VendorPredicate preparePredicate(String userInput) {
        return new VendorPredicate(Optional.of("123141"), Optional.empty(), Optional.empty(), Optional.empty(),
                Optional.empty(), Optional.empty(), Optional.empty(), Optional.empty(),
                Optional.empty());
    }

}

