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
public class UnfavouriteCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs(), null);

    @Test
    public void execute_validIndexFilteredList_success() {
        Person personToUnfavourite = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        UnfavouriteCommand unfavouriteCommand = new UnfavouriteCommand(INDEX_FIRST_PERSON);

        String expectedMessage = personToUnfavourite.getName().toString()
                + UnfavouriteCommand.MESSAGE_UNFAVOURITE_PERSON_SUCCESS;

        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs(), null);
        expectedModel.unfavouritePerson(personToUnfavourite);
        if (!personToUnfavourite.isFavourite()) {
            personToUnfavourite.setIsFavourite();
        }
        assertCommandSuccess(unfavouriteCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_validIndexFilteredList_failure() {
        Person personAlreadyUnfavourited = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        UnfavouriteCommand unfavouriteCommand = new UnfavouriteCommand(INDEX_FIRST_PERSON);

        String expectedMessage = personAlreadyUnfavourited.getName().toString()
                + UnfavouriteCommand.MESSAGE_ALREADY_UNFAVOURITE_PERSON;

        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs(), null);
        expectedModel.favouritePerson(personAlreadyUnfavourited);
        if (personAlreadyUnfavourited.isFavourite()) {
            personAlreadyUnfavourited.setIsNotFavourite();
        }
        assertCommandFailure(unfavouriteCommand, model, expectedMessage);
    }

    @Test
    public void execute_invalidIndexFilteredList_failure() {
        ObservableList<Person> list = model.getFilteredPersonList();
        Person personToUnfavourite = list.get(INDEX_FIRST_PERSON.getZeroBased());
        UnfavouriteCommand unfavouriteCommand = new UnfavouriteCommand(Index.fromZeroBased(list.size() + 1));

        String expectedMessage = Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX;

        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs(), null);
        expectedModel.unfavouritePerson(personToUnfavourite);
        if (!personToUnfavourite.isFavourite()) {
            personToUnfavourite.setIsFavourite();
        }
        assertCommandFailure(unfavouriteCommand, model, expectedMessage);
    }

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

}
