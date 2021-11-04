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

import seedu.address.commons.core.Messages;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.NameContainsKeywordsPredicate;
import seedu.address.testutil.TypicalPersons;

/**
 * Contains integration tests (interaction with the Model) and unit tests for EditCommand.
 */
public class UnfavouriteCommandTest {

    private final Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs(), null);
    private final Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs(), null);

    @Test
    public void equals() {
        UnfavouriteCommand unfavouriteFirstCommand = new UnfavouriteCommand(INDEX_FIRST_PERSON);
        UnfavouriteCommand unfavouriteSecondCommand = new UnfavouriteCommand(INDEX_SECOND_PERSON);

        // same object -> returns true
        assertTrue(unfavouriteFirstCommand.equals(unfavouriteFirstCommand));

        // same values -> returns true
        UnfavouriteCommand unfavouriteFirstCommandCopy = new UnfavouriteCommand(INDEX_FIRST_PERSON);
        assertTrue(unfavouriteFirstCommand.equals(unfavouriteFirstCommandCopy));

        // different types -> returns false
        assertFalse(unfavouriteFirstCommand.equals(1));

        // null -> returns false
        assertFalse(unfavouriteFirstCommand.equals(null));

        // different person -> returns false
        assertFalse(unfavouriteFirstCommand.equals(unfavouriteSecondCommand));
    }

    @Test
    public void execute_validIndex_success() {
        String expectedMessage = TypicalPersons.ALICE.getName().fullName
                + UnfavouriteCommand.MESSAGE_UNFAVOURITE_PERSON_SUCCESS;
        UnfavouriteCommand command = new UnfavouriteCommand(INDEX_FIRST_PERSON);
        model.getFilteredPersonList().get(0).setIsFavourite();
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndex_failure() {
        String expectedMessage = Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX;
        model.updateFilteredPersonList(new NameContainsKeywordsPredicate(List.of("alice")));
        UnfavouriteCommand command = new UnfavouriteCommand(INDEX_THIRD_PERSON);
        assertCommandFailure(command, model, expectedMessage);
    }

    @Test
    public void execute_alreadyFavourite_failure() {
        String expectedMessage = TypicalPersons.ALICE.getName().fullName
                + UnfavouriteCommand.MESSAGE_ALREADY_UNFAVOURITE_PERSON;
        UnfavouriteCommand command = new UnfavouriteCommand(INDEX_FIRST_PERSON);
        model.getFilteredPersonList().get(0).setIsNotFavourite();
        assertCommandFailure(command, model, expectedMessage);
    }
}
