package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import javafx.collections.ObservableList;
import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Person;


/**
 * Contains integration tests (interaction with the Model) and unit tests for EditCommand.
 */
public class UnfavoriteCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs(), null);

    @Test
    public void execute_validIndexFilteredList_success() {
        Person personToUnfavorite = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        UnfavoriteCommand unfavoriteCommand = new UnfavoriteCommand(INDEX_FIRST_PERSON);

        String expectedMessage = personToUnfavorite.getName().toString()
                + UnfavoriteCommand.MESSAGE_UNFAVORITE_PERSON_SUCCESS;

        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs(), null);
        expectedModel.unfavoritePerson(personToUnfavorite);
        if (!personToUnfavorite.isFavorite()) {
            personToUnfavorite.setIsFavorite();
        }
        assertCommandSuccess(unfavoriteCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_validIndexFilteredList_failure() {
        Person personAlreadyUnfavorited = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        UnfavoriteCommand unfavoriteCommand = new UnfavoriteCommand(INDEX_FIRST_PERSON);

        String expectedMessage = personAlreadyUnfavorited.getName().toString()
                + UnfavoriteCommand.MESSAGE_ALREADY_UNFAVORITE_PERSON;

        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs(), null);
        expectedModel.favoritePerson(personAlreadyUnfavorited);
        if (personAlreadyUnfavorited.isFavorite()) {
            personAlreadyUnfavorited.setIsNotFavorite();
        }
        assertCommandFailure(unfavoriteCommand, model, expectedMessage);
    }

    @Test
    public void execute_invalidIndexFilteredList_failure() {
        ObservableList<Person> list = model.getFilteredPersonList();
        Person personToUnfavorite = list.get(INDEX_FIRST_PERSON.getZeroBased());
        UnfavoriteCommand unfavoriteCommand = new UnfavoriteCommand(Index.fromZeroBased(list.size() + 1));

        String expectedMessage = Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX;

        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs(), null);
        expectedModel.unfavoritePerson(personToUnfavorite);
        if (!personToUnfavorite.isFavorite()) {
            personToUnfavorite.setIsFavorite();
        }
        assertCommandFailure(unfavoriteCommand, model, expectedMessage);
    }

    @Test
    public void equals() {
        UnfavoriteCommand unfavoriteFirstCommand = new UnfavoriteCommand(INDEX_FIRST_PERSON);
        UnfavoriteCommand unfavoriteSecondCommand = new UnfavoriteCommand(INDEX_SECOND_PERSON);

        // same object -> returns true
        assertTrue(unfavoriteFirstCommand.equals(unfavoriteFirstCommand));

        // same values -> returns true
        UnfavoriteCommand unfavoriteFirstCommandCopy = new UnfavoriteCommand(INDEX_FIRST_PERSON);
        assertTrue(unfavoriteFirstCommand.equals(unfavoriteFirstCommandCopy));

        // different types -> returns false
        assertFalse(unfavoriteFirstCommand.equals(1));

        // null -> returns false
        assertFalse(unfavoriteFirstCommand.equals(null));

        // different person -> returns false
        assertFalse(unfavoriteFirstCommand.equals(unfavoriteSecondCommand));
    }

}
