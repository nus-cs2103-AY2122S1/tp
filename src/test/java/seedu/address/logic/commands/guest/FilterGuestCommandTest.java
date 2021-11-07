package seedu.address.logic.commands.guest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static seedu.address.commons.core.Messages.MESSAGE_GUESTS_LISTED_OVERVIEW;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.guest.TypicalGuests.getTypicalGuestBook;

import java.util.Collections;
import java.util.Optional;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.guest.Archive;
import seedu.address.model.guest.GuestPredicate;
import seedu.address.model.vendor.VendorBook;

public class FilterGuestCommandTest {

    private Model model = new ModelManager(getTypicalGuestBook(), new VendorBook(), new UserPrefs(), new Archive());
    private Model expectedModel =
            new ModelManager(getTypicalGuestBook(), new VendorBook(), new UserPrefs(), new Archive());

    @Test
    public void equals() {
        GuestPredicate firstPredicate =
                new GuestPredicate(Optional.of("12312D"), Optional.empty(), Optional.empty(), Optional.empty(),
                        Optional.empty());
        GuestPredicate secondPredicate =
                new GuestPredicate(Optional.of("23188F"), Optional.empty(), Optional.empty(), Optional.empty(),
                        Optional.empty());

        FilterGuestCommand filterFirstCommand = new FilterGuestCommand(firstPredicate);
        FilterGuestCommand filterSecondCommand = new FilterGuestCommand(secondPredicate);

        // same object -> returns true
        assertTrue(filterFirstCommand.equals(filterFirstCommand));

        // same values -> returns true
        FilterGuestCommand findFirstCommandCopy = new FilterGuestCommand(firstPredicate);
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
        String expectedMessage = String.format(MESSAGE_GUESTS_LISTED_OVERVIEW, 0);
        GuestPredicate predicate = preparePredicate("  ");
        FilterGuestCommand command = new FilterGuestCommand(predicate);
        expectedModel.updateFilteredGuestList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredGuestList());
    }

    /**
     * Parses {@code userInput} into a {@code GuestPredicate}.
     */
    private GuestPredicate preparePredicate(String userInput) {
        return new GuestPredicate(Optional.of("31321312D"), Optional.empty(), Optional.empty(), Optional.empty(),
                Optional.empty());
    }

}
