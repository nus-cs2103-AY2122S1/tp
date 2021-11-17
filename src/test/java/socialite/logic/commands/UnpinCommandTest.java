package socialite.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import socialite.commons.core.Messages;
import socialite.commons.core.index.Index;
import socialite.logic.commands.exceptions.CommandException;
import socialite.model.CommandHistory;
import socialite.model.Model;
import socialite.model.ModelManager;
import socialite.model.UserPrefs;
import socialite.model.person.Person;
import socialite.testutil.TypicalIndexes;
import socialite.testutil.TypicalPersons;

/**
 * Contains integration tests (interaction with the model) and unit tests for {@code UnpinCommand}.
 */
public class UnpinCommandTest {

    private Model model =
            new ModelManager(TypicalPersons.getTypicalContactList(), new UserPrefs(), new CommandHistory());

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredPersonList().size() + 1);
        UnpinCommand unpinCommand = new UnpinCommand(outOfBoundIndex);

        CommandTestUtil.assertCommandFailure(unpinCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void execute_invalidIndexFilteredLIst_throwsCommandException() {
        CommandTestUtil.showPersonAtIndex(model, TypicalIndexes.INDEX_FIRST_PERSON);

        Index outOfBoundIndex = TypicalIndexes.INDEX_SECOND_PERSON;
        // ensures that outOfBoundIndex is still in bounds of the contact list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getContactList().getPersonList().size());

        UnpinCommand unpinCommand = new UnpinCommand(outOfBoundIndex);

        CommandTestUtil.assertCommandFailure(unpinCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);

    }

    @Test
    public void execute_validIndexFilteredList_success() {
        // Filter and only show the third person in the list
        CommandTestUtil.showPersonAtIndex(model, TypicalIndexes.INDEX_THIRD_PERSON);

        // Pin the person and unpin it afterwards
        Person personToUnpin = model.getFilteredPersonList().get(TypicalIndexes.INDEX_FIRST_PERSON.getZeroBased());
        PinCommand pinCommand = new PinCommand(TypicalIndexes.INDEX_FIRST_PERSON);
        UnpinCommand unpinCommand = new UnpinCommand(TypicalIndexes.INDEX_FIRST_PERSON);
        try {
            pinCommand.execute(model);
        } catch (CommandException ce) {
            throw new AssertionError("Execution of command should not fail.", ce);
        }

        ModelManager expectedModel = new ModelManager(model.getContactList(), new UserPrefs(), new CommandHistory());
        expectedModel.pinPerson(personToUnpin);
        expectedModel.unpinPerson(personToUnpin);
        expectedModel.updateFilteredPersonList(Model.PREDICATE_SHOW_ALL_PERSONS);

        String expectedMessage = String.format(UnpinCommand.MESSAGE_UNPIN_PERSON_SUCCESS, personToUnpin);
        CommandTestUtil.assertCommandSuccess(unpinCommand, model, expectedMessage, expectedModel);
    }


    @Test
    public void execute_validIndexUnfilteredList_success() {
        // Pin the third person in the list and unpin it afterwards
        Person personToUnpin = model.getFilteredPersonList().get(TypicalIndexes.INDEX_THIRD_PERSON.getZeroBased());
        PinCommand pinCommand = new PinCommand(TypicalIndexes.INDEX_THIRD_PERSON);
        UnpinCommand unpinCommand = new UnpinCommand(TypicalIndexes.INDEX_FIRST_PERSON);
        try {
            pinCommand.execute(model);
        } catch (CommandException ce) {
            throw new AssertionError("Execution of command should not fail.", ce);
        }

        ModelManager expectedModel = new ModelManager(model.getContactList(), new UserPrefs(), new CommandHistory());
        expectedModel.pinPerson(personToUnpin);
        expectedModel.unpinPerson(personToUnpin);
        expectedModel.updateFilteredPersonList(Model.PREDICATE_SHOW_ALL_PERSONS);

        String expectedMessage = String.format(UnpinCommand.MESSAGE_UNPIN_PERSON_SUCCESS, personToUnpin);
        CommandTestUtil.assertCommandSuccess(unpinCommand, model, expectedMessage, expectedModel);
    }


    @Test
    public void execute_unpinUnpinnedPerson_throwsCommandException() {
        UnpinCommand unpinCommand = new UnpinCommand(TypicalIndexes.INDEX_FIRST_PERSON);
        CommandTestUtil.assertCommandFailure(unpinCommand, model, UnpinCommand.MESSAGE_PERSON_NOT_PINNED);
    }

    @Test
    public void equals() {
        UnpinCommand unpinFirstCommand = new UnpinCommand(TypicalIndexes.INDEX_FIRST_PERSON);
        UnpinCommand unpinSecondCommand = new UnpinCommand(TypicalIndexes.INDEX_SECOND_PERSON);

        // same object -> returns true
        assertEquals(unpinFirstCommand, unpinFirstCommand);

        // same values -> returns true
        UnpinCommand unpinFirstCommandCopy = new UnpinCommand(TypicalIndexes.INDEX_FIRST_PERSON);
        assertEquals(unpinFirstCommand, unpinFirstCommandCopy);

        // different types -> returns false
        assertNotEquals(1, unpinFirstCommand);

        // null -> returns false
        assertNotEquals(null, unpinFirstCommand);

        // different person -> returns false
        assertNotEquals(unpinSecondCommand, unpinFirstCommand);
    }
}
