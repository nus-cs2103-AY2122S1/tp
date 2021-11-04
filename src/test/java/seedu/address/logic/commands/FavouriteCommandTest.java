package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_THIRD_PERSON;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.core.Messages;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.NameContainsKeywordsPredicate;
import seedu.address.testutil.TypicalPersons;

/**
 * Contains integration tests (interaction with the Model) and unit tests for EditCommand.
 */
public class FavouriteCommandTest {

    private final Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs(), null);
    private final Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs(), null);

    private Index targetIndex;

    @Test
    public void equals() {
        FavouriteCommand favouriteFirstCommand = new FavouriteCommand(INDEX_FIRST_PERSON);
        FavouriteCommand favouriteSecondCommand = new FavouriteCommand(INDEX_SECOND_PERSON);

        // same object -> returns true
        assertTrue(favouriteFirstCommand.equals(favouriteFirstCommand));

        // same values -> returns true
        FavouriteCommand favouriteFirstCommandCopy = new FavouriteCommand(INDEX_FIRST_PERSON);
        assertTrue(favouriteFirstCommand.equals(favouriteFirstCommandCopy));

        // different types -> returns false
        assertFalse(favouriteFirstCommand.equals(1));

        // null -> returns false
        assertFalse(favouriteFirstCommand.equals(null));

        // different person -> returns false
        assertFalse(favouriteFirstCommand.equals(favouriteSecondCommand));
    }
    @Test
    public void execute_validIndex_success() {
        String expectedMessage = TypicalPersons.ALICE.getName().fullName
                + FavouriteCommand.MESSAGE_FAVOURITE_PERSON_SUCCESS;
        FavouriteCommand command = new FavouriteCommand(INDEX_FIRST_PERSON);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndex_failure() {
        String expectedMessage = Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX;
        model.updateFilteredPersonList(new NameContainsKeywordsPredicate(List.of("alice")));
        FavouriteCommand command = new FavouriteCommand(INDEX_THIRD_PERSON);
        assertCommandFailure(command, model, expectedMessage);
    }

    @Test
    public void execute_alreadyFavourite_failure() {
        String expectedMessage = TypicalPersons.ALICE.getName().fullName
                + FavouriteCommand.MESSAGE_ALREADY_FAVOURITE_PERSON;
        FavouriteCommand command = new FavouriteCommand(INDEX_FIRST_PERSON);
        model.getFilteredPersonList().get(0).setIsFavourite();
        assertCommandFailure(command, model, expectedMessage);
    }
}
