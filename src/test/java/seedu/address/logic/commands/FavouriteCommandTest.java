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
public class FavouriteCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs(), null);

    @Test
    public void execute_validIndexFilteredList_success() {
        Person personToFavourite = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        FavouriteCommand favouriteCommand = new FavouriteCommand(INDEX_FIRST_PERSON);

        String expectedMessage = personToFavourite.getName().toString()
                + FavouriteCommand.MESSAGE_FAVOURITE_PERSON_SUCCESS;

        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs(), null);
        expectedModel.favouritePerson(personToFavourite);
        if (personToFavourite.isFavourite()) {
            personToFavourite.setIsNotFavourite();
        }
        assertCommandSuccess(favouriteCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_validIndexFilteredList_failure() {
        Person personAlreadyFavourited = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        FavouriteCommand favouriteCommand = new FavouriteCommand(INDEX_FIRST_PERSON);

        String expectedMessage = personAlreadyFavourited.getName().toString()
                + FavouriteCommand.MESSAGE_ALREADY_FAVOURITE_PERSON;

        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs(), null);
        expectedModel.favouritePerson(personAlreadyFavourited);
        if (!personAlreadyFavourited.isFavourite()) {
            personAlreadyFavourited.setIsFavourite();
        }
        assertCommandFailure(favouriteCommand, model, expectedMessage);
    }

    @Test
    public void execute_invalidIndexFilteredList_failure() {
        ObservableList<Person> list = model.getFilteredPersonList();
        Person personToFavourite = list.get(INDEX_FIRST_PERSON.getZeroBased());
        FavouriteCommand favouriteCommand = new FavouriteCommand(Index.fromZeroBased(list.size() + 1));

        String expectedMessage = Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX;

        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs(), null);
        expectedModel.favouritePerson(personToFavourite);
        if (personToFavourite.isFavourite()) {
            personToFavourite.setIsNotFavourite();
        }
        assertCommandFailure(favouriteCommand, model, expectedMessage);
    }

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
