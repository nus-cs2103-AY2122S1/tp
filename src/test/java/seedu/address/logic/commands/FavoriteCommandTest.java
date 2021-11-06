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
public class FavoriteCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs(), null);

    @Test
    public void execute_validIndexFilteredList_success() {
        Person personToFavorite = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        FavoriteCommand favoriteCommand = new FavoriteCommand(INDEX_FIRST_PERSON);

        String expectedMessage = personToFavorite.getName().toString()
                + FavoriteCommand.MESSAGE_FAVORITE_PERSON_SUCCESS;

        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs(), null);
        expectedModel.favoritePerson(personToFavorite);
        if (personToFavorite.isFavorite()) {
            personToFavorite.setIsNotFavorite();
        }
        assertCommandSuccess(favoriteCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_validIndexFilteredList_failure() {
        Person personAlreadyFavorited = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        FavoriteCommand favoriteCommand = new FavoriteCommand(INDEX_FIRST_PERSON);

        String expectedMessage = personAlreadyFavorited.getName().toString()
                + FavoriteCommand.MESSAGE_ALREADY_FAVORITE_PERSON;

        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs(), null);
        expectedModel.favoritePerson(personAlreadyFavorited);
        if (!personAlreadyFavorited.isFavorite()) {
            personAlreadyFavorited.setIsFavorite();
        }
        assertCommandFailure(favoriteCommand, model, expectedMessage);
    }

    @Test
    public void execute_invalidIndexFilteredList_failure() {
        ObservableList<Person> list = model.getFilteredPersonList();
        Person personToFavorite = list.get(INDEX_FIRST_PERSON.getZeroBased());
        FavoriteCommand favoriteCommand = new FavoriteCommand(Index.fromZeroBased(list.size() + 1));

        String expectedMessage = Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX;

        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs(), null);
        expectedModel.favoritePerson(personToFavorite);
        if (personToFavorite.isFavorite()) {
            personToFavorite.setIsNotFavorite();
        }
        assertCommandFailure(favoriteCommand, model, expectedMessage);
    }

    @Test
    public void equals() {
        FavoriteCommand favoriteFirstCommand = new FavoriteCommand(INDEX_FIRST_PERSON);
        FavoriteCommand favoriteSecondCommand = new FavoriteCommand(INDEX_SECOND_PERSON);

        // same object -> returns true
        assertTrue(favoriteFirstCommand.equals(favoriteFirstCommand));

        // same values -> returns true
        FavoriteCommand favoriteFirstCommandCopy = new FavoriteCommand(INDEX_FIRST_PERSON);
        assertTrue(favoriteFirstCommand.equals(favoriteFirstCommandCopy));

        // different types -> returns false
        assertFalse(favoriteFirstCommand.equals(1));

        // null -> returns false
        assertFalse(favoriteFirstCommand.equals(null));

        // different person -> returns false
        assertFalse(favoriteFirstCommand.equals(favoriteSecondCommand));
    }
}
