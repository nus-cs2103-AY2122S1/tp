package seedu.track2gather.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.track2gather.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.track2gather.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.track2gather.logic.commands.CommandTestUtil.showPersonAtIndex;
import static seedu.track2gather.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.track2gather.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
import static seedu.track2gather.testutil.TypicalPersons.getTypicalTrack2Gather;

import org.junit.jupiter.api.Test;

import seedu.track2gather.commons.core.Messages;
import seedu.track2gather.commons.core.index.Index;
import seedu.track2gather.model.Model;
import seedu.track2gather.model.ModelManager;
import seedu.track2gather.model.UserPrefs;
import seedu.track2gather.model.person.Person;

public class SCallCommandTest {

    private Model model = new ModelManager(getTypicalTrack2Gather(), new UserPrefs());

    @Test
    public void execute_validIndexUnfilteredList_success() {
        Person personToIncrement = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        SCallCommand command = new SCallCommand(INDEX_FIRST_PERSON);

        ModelManager expectedModel = new ModelManager(model.getTrack2Gather(), new UserPrefs());
        Person newPerson = new Person(personToIncrement, personToIncrement.getCallStatus().call());
        expectedModel.setPerson(personToIncrement, newPerson);
        String expectedMessage = String.format(SCallCommand.MESSAGE_CALL_PERSON_SUCCESS, newPerson.getName(),
                newPerson.getCaseNumber(), newPerson.getCallStatus().getNumFailedCalls());

        assertCommandSuccess(command, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredPersonList().size() + 1);
        SCallCommand command = new SCallCommand(outOfBoundIndex);

        assertCommandFailure(command, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void execute_validIndexFilteredList_success() {
        showPersonAtIndex(model, INDEX_FIRST_PERSON);

        Person personToIncrement = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        SCallCommand command = new SCallCommand(INDEX_FIRST_PERSON);

        Model expectedModel = new ModelManager(model.getTrack2Gather(), new UserPrefs());
        showPersonAtIndex(expectedModel, INDEX_FIRST_PERSON);

        Person newPerson = new Person(personToIncrement, personToIncrement.getCallStatus().call());
        expectedModel.setPerson(personToIncrement, newPerson);
        String expectedMessage = String.format(SCallCommand.MESSAGE_CALL_PERSON_SUCCESS, newPerson.getName(),
                newPerson.getCaseNumber(), newPerson.getCallStatus().getNumFailedCalls());

        assertCommandSuccess(command, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexFilteredList_throwsCommandException() {
        showPersonAtIndex(model, INDEX_FIRST_PERSON);

        Index outOfBoundIndex = INDEX_SECOND_PERSON;
        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getTrack2Gather().getPersonList().size());

        SCallCommand command = new SCallCommand(outOfBoundIndex);

        assertCommandFailure(command, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        SCallCommand firstCommand = new SCallCommand(INDEX_FIRST_PERSON);
        SCallCommand secondCommand = new SCallCommand(INDEX_SECOND_PERSON);

        // same object -> returns true
        assertTrue(firstCommand.equals(firstCommand));

        // same values -> returns true
        SCallCommand firstCommandCopy = new SCallCommand(INDEX_FIRST_PERSON);
        assertTrue(firstCommand.equals(firstCommandCopy));

        // different types -> returns false
        assertFalse(firstCommand.equals(1));

        // null -> returns false
        assertFalse(firstCommand.equals(null));

        // different person -> returns false
        assertFalse(firstCommand.equals(secondCommand));
    }
}
