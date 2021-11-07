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
import seedu.track2gather.testutil.TestUtil;

public class FCallCommandTest {

    @Test
    public void execute_validIndexUnfilteredList_success() {
        Model model = new ModelManager(getTypicalTrack2Gather(), new UserPrefs());
        Model expectedModel = new ModelManager(model.getTrack2Gather(), new UserPrefs());

        FCallCommand command = new FCallCommand(INDEX_FIRST_PERSON);

        Person personToIncrement = TestUtil.getPerson(model, INDEX_FIRST_PERSON);
        Person newPerson = new Person(personToIncrement, personToIncrement.getCallStatus().incrementNumFailedCalls());
        expectedModel.setPerson(personToIncrement, newPerson);
        String expectedMessage = String.format(FCallCommand.MESSAGE_INCREMENT_PERSON_SUCCESS, newPerson.getName(),
                newPerson.getCaseNumber(), newPerson.getCallStatus().getNumFailedCalls());

        assertCommandSuccess(command, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Model model = new ModelManager(getTypicalTrack2Gather(), new UserPrefs());
        Model expectedModel = new ModelManager(model.getTrack2Gather(), new UserPrefs());

        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredPersonList().size() + 1);
        FCallCommand command = new FCallCommand(outOfBoundIndex);

        assertCommandFailure(command, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void execute_validIndexFilteredList_success() {
        Model model = new ModelManager(getTypicalTrack2Gather(), new UserPrefs());
        Model expectedModel = new ModelManager(model.getTrack2Gather(), new UserPrefs());
        showPersonAtIndex(model, INDEX_FIRST_PERSON);
        showPersonAtIndex(expectedModel, INDEX_FIRST_PERSON);

        FCallCommand command = new FCallCommand(INDEX_FIRST_PERSON);

        Person personToIncrement = TestUtil.getPerson(model, INDEX_FIRST_PERSON);
        Person newPerson = new Person(personToIncrement, personToIncrement.getCallStatus().incrementNumFailedCalls());
        expectedModel.setPerson(personToIncrement, newPerson);
        String expectedMessage = String.format(FCallCommand.MESSAGE_INCREMENT_PERSON_SUCCESS, newPerson.getName(),
                newPerson.getCaseNumber(), newPerson.getCallStatus().getNumFailedCalls());

        assertCommandSuccess(command, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexFilteredList_throwsCommandException() {
        Model model = new ModelManager(getTypicalTrack2Gather(), new UserPrefs());
        showPersonAtIndex(model, INDEX_FIRST_PERSON);

        Index outOfBoundIndex = INDEX_SECOND_PERSON;
        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getTrack2Gather().getPersonList().size());

        FCallCommand command = new FCallCommand(outOfBoundIndex);

        assertCommandFailure(command, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void execute_validIndexEnforcementMode_success() {
        Model model = new ModelManager(getTypicalTrack2Gather(), new UserPrefs());
        Model expectedModel = new ModelManager(model.getTrack2Gather(), new UserPrefs());
        model.updateFilteredPersonList(Model.PREDICATE_SHOW_NON_CALLED);
        expectedModel.updateFilteredPersonList(Model.PREDICATE_SHOW_NON_CALLED);

        FCallCommand command = new FCallCommand(INDEX_FIRST_PERSON);

        Person personToIncrement = TestUtil.getPerson(model, INDEX_FIRST_PERSON);
        Person newPerson = new Person(personToIncrement, personToIncrement.getCallStatus().incrementNumFailedCalls());
        expectedModel.setPerson(personToIncrement, newPerson);
        String expectedMessage = String.format(FCallCommand.MESSAGE_INCREMENT_PERSON_SUCCESS, newPerson.getName(),
                newPerson.getCaseNumber(), newPerson.getCallStatus().getNumFailedCalls());

        assertCommandSuccess(command, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_multipleFCallCommand_throwsCommandException() {
        Model model = new ModelManager(getTypicalTrack2Gather(), new UserPrefs());

        Person personToIncrement = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        Person newPerson = new Person(personToIncrement, personToIncrement.getCallStatus().incrementNumFailedCalls());
        model.setPerson(model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased()), newPerson);
        FCallCommand command = new FCallCommand(INDEX_FIRST_PERSON);

        String expectedMessage = FCallCommand.MESSAGE_INVALID_MULTIPLE_CALLS;
        assertCommandFailure(command, model, expectedMessage);
    }

    @Test
    public void equals() {
        FCallCommand firstCommand = new FCallCommand(INDEX_FIRST_PERSON);
        FCallCommand secondCommand = new FCallCommand(INDEX_SECOND_PERSON);

        // same object -> returns true
        assertTrue(firstCommand.equals(firstCommand));

        // same values -> returns true
        FCallCommand firstCommandCopy = new FCallCommand(INDEX_FIRST_PERSON);
        assertTrue(firstCommand.equals(firstCommandCopy));

        // different types -> returns false
        assertFalse(firstCommand.equals(1));

        // null -> returns false
        assertFalse(firstCommand.equals(null));

        // different person -> returns false
        assertFalse(firstCommand.equals(secondCommand));
    }
}
