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
 * Contains integration tests (interaction with the model) and unit tests for {@code PinCommand}.
 */
public class PinCommandTest {

    private Model model =
            new ModelManager(TypicalPersons.getTypicalContactList(), new UserPrefs(), new CommandHistory());

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredPersonList().size() + 1);
        PinCommand pinCommand = new PinCommand(outOfBoundIndex);

        CommandTestUtil.assertCommandFailure(pinCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void execute_invalidIndexFilteredLIst_throwsCommandException() {
        CommandTestUtil.showPersonAtIndex(model, TypicalIndexes.INDEX_FIRST_PERSON);

        Index outOfBoundIndex = TypicalIndexes.INDEX_SECOND_PERSON;
        // ensures that outOfBoundIndex is still in bounds of contact list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getContactList().getPersonList().size());

        PinCommand pinCommand = new PinCommand(outOfBoundIndex);

        CommandTestUtil.assertCommandFailure(pinCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);

    }

    @Test
    public void execute_validIndexFilteredList_success() {
        // Filter and only show the third person in the list
        CommandTestUtil.showPersonAtIndex(model, TypicalIndexes.INDEX_THIRD_PERSON);

        Person personToPin = model.getFilteredPersonList().get(TypicalIndexes.INDEX_FIRST_PERSON.getZeroBased());
        PinCommand pinCommand = new PinCommand(TypicalIndexes.INDEX_FIRST_PERSON);

        ModelManager expectedModel = new ModelManager(model.getContactList(), new UserPrefs(), new CommandHistory());
        expectedModel.pinPerson(personToPin);
        expectedModel.updateFilteredPersonList(Model.PREDICATE_SHOW_ALL_PERSONS);
        String expectedMessage = String.format(PinCommand.MESSAGE_PIN_PERSON_SUCCESS, personToPin);
        CommandTestUtil.assertCommandSuccess(pinCommand, model, expectedMessage, expectedModel);

        // Unpin the person after we're done
        model.unpinPerson(personToPin);
        model.updateFilteredPersonList(Model.PREDICATE_SHOW_ALL_PERSONS);
    }

    @Test
    public void execute_validIndexUnfilteredList_success() {
        // Pin the third person in the list
        Person personToPin = model.getFilteredPersonList().get(TypicalIndexes.INDEX_THIRD_PERSON.getZeroBased());
        PinCommand pinCommand = new PinCommand(TypicalIndexes.INDEX_THIRD_PERSON);

        ModelManager expectedModel = new ModelManager(model.getContactList(), new UserPrefs(), new CommandHistory());
        expectedModel.pinPerson(personToPin);
        expectedModel.updateFilteredPersonList(Model.PREDICATE_SHOW_ALL_PERSONS);
        String expectedMessage = String.format(PinCommand.MESSAGE_PIN_PERSON_SUCCESS, personToPin);
        CommandTestUtil.assertCommandSuccess(pinCommand, model, expectedMessage, expectedModel);

        // Unpin the person after we're done
        model.unpinPerson(personToPin);
        model.updateFilteredPersonList(Model.PREDICATE_SHOW_ALL_PERSONS);
    }

    @Test
    public void execute_pinPinnedPerson_throwsCommandException() {
        Person personToPin = model.getFilteredPersonList().get(TypicalIndexes.INDEX_FIRST_PERSON.getZeroBased());
        PinCommand pinCommand = new PinCommand(TypicalIndexes.INDEX_FIRST_PERSON);
        // Pin the first person
        try {
            pinCommand.execute(model);
        } catch (CommandException ce) {
            throw new AssertionError("Execution of command should not fail.", ce);
        }

        // Pin the first person again
        CommandTestUtil.assertCommandFailure(pinCommand, model, PinCommand.MESSAGE_PERSON_ALREADY_PINNED);

        // Unpin the first person after the test
        model.unpinPerson(personToPin);
        model.updateFilteredPersonList(Model.PREDICATE_SHOW_ALL_PERSONS);
    }


    @Test
    public void equals() {
        PinCommand pinFirstCommand = new PinCommand(TypicalIndexes.INDEX_FIRST_PERSON);
        PinCommand pinSecondCommand = new PinCommand(TypicalIndexes.INDEX_SECOND_PERSON);

        // same object -> returns true
        assertEquals(pinFirstCommand, pinFirstCommand);

        // same values -> returns true
        PinCommand pinFirstCommandCopy = new PinCommand(TypicalIndexes.INDEX_FIRST_PERSON);
        assertEquals(pinFirstCommand, pinFirstCommandCopy);

        // different types -> returns false
        assertNotEquals(1, pinFirstCommand);

        // null -> returns false
        assertNotEquals(null, pinFirstCommand);

        // different person -> returns false
        assertNotEquals(pinSecondCommand, pinFirstCommand);
    }
}
