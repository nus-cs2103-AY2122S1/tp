package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showPersonAtIndex;
import static seedu.address.testutil.TypicalIndexes.INDEX_EIGHTH_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Person;
import seedu.address.testutil.PersonBuilder;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code UnpinCommand}.
 */
public class UnpinCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_validIndexUnfilteredList_success() {
        Person personToUnpin = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        UnpinCommand pinCommand = new UnpinCommand(INDEX_FIRST_PERSON);
        String expectedMessage = String.format(UnpinCommand.MESSAGE_UNPINNED_PERSON_SUCCESS, personToUnpin);
        Person unpinnedPerson = createUnpinnnedPerson(personToUnpin);
        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setPerson(personToUnpin, unpinnedPerson);
        assertCommandSuccess(pinCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_validIndexUnfilteredListPersonNotPinned_throwsCommandException() {
        Person personToUnpin = model.getFilteredPersonList().get(INDEX_EIGHTH_PERSON.getZeroBased());
        UnpinCommand pinCommand = new UnpinCommand(INDEX_EIGHTH_PERSON);
        String expectedMessage = String.format(UnpinCommand.MESSAGE_PERSON_NOT_PINNED_FAILURE, personToUnpin);
        assertCommandFailure(pinCommand, model, expectedMessage);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredPersonList().size() + 1);
        UnpinCommand unpinCommand = new UnpinCommand(outOfBoundIndex);
        assertCommandFailure(unpinCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void execute_validIndexFilteredList_success() {
        showPersonAtIndex(model, INDEX_FIRST_PERSON);
        Person personToUnpin = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        UnpinCommand pinCommand = new UnpinCommand(INDEX_FIRST_PERSON);
        String expectedMessage = String.format(UnpinCommand.MESSAGE_UNPINNED_PERSON_SUCCESS, personToUnpin);
        Person unpinnedPerson = createUnpinnnedPerson(personToUnpin);
        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        showPersonAtIndex(expectedModel, INDEX_FIRST_PERSON);
        expectedModel.setPerson(personToUnpin, unpinnedPerson);
        assertCommandSuccess(pinCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_validIndexFilteredListPersonAlreadyPinned_throwsCommandException() {
        showPersonAtIndex(model, INDEX_EIGHTH_PERSON);

        Person personToUnpin = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        UnpinCommand pinCommand = new UnpinCommand(INDEX_FIRST_PERSON);

        String expectedMessage = String.format(UnpinCommand.MESSAGE_PERSON_NOT_PINNED_FAILURE, personToUnpin);
        assertCommandFailure(pinCommand, model, expectedMessage);
    }

    @Test
    public void execute_invalidIndexFilteredList_throwsCommandException() {
        showPersonAtIndex(model, INDEX_FIRST_PERSON);

        Index outOfBoundIndex = INDEX_SECOND_PERSON;

        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getAddressBook().getPersonList().size());

        UnpinCommand pinCommand = new UnpinCommand(outOfBoundIndex);

        assertCommandFailure(pinCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        UnpinCommand pinFirstCommand = new UnpinCommand(INDEX_FIRST_PERSON);
        UnpinCommand pinSecondCommand = new UnpinCommand(INDEX_SECOND_PERSON);

        // same object -> returns true
        assertTrue(pinFirstCommand.equals(pinFirstCommand));

        // same values -> returns true
        UnpinCommand pinFirstCommandCopy = new UnpinCommand(INDEX_FIRST_PERSON);
        assertTrue(pinFirstCommand.equals(pinFirstCommandCopy));

        // different types -> returns false
        assertFalse(pinFirstCommand.equals(1));

        // null -> returns false
        assertFalse(pinFirstCommand.equals(null));

        // different person -> returns false
        assertFalse(pinFirstCommand.equals(pinSecondCommand));
    }

    private Person createUnpinnnedPerson(Person personToUnpin) {
        Person pinnedPerson = new PersonBuilder(personToUnpin).withPin(false).build();
        return pinnedPerson;
    }
}
