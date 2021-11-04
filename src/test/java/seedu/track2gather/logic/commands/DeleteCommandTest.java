package seedu.track2gather.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.track2gather.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.track2gather.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.track2gather.logic.commands.CommandTestUtil.showPersonAtIndex;
import static seedu.track2gather.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.track2gather.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
import static seedu.track2gather.testutil.TypicalIndexes.INDEX_THIRD_PERSON;
import static seedu.track2gather.testutil.TypicalPersons.getTypicalTrack2Gather;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import seedu.track2gather.commons.core.Messages;
import seedu.track2gather.commons.core.index.Index;
import seedu.track2gather.model.Model;
import seedu.track2gather.model.ModelManager;
import seedu.track2gather.model.UserPrefs;
import seedu.track2gather.model.person.Person;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code DeleteCommand}.
 */
public class DeleteCommandTest {

    private Model model = new ModelManager(getTypicalTrack2Gather(), new UserPrefs());

    @Test
    public void execute_validIndexUnfilteredList_success() {
        Person personToDelete = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        ArrayList<Index> indicesFirstPerson = new ArrayList<>();
        indicesFirstPerson.add(INDEX_FIRST_PERSON);
        DeleteCommand deleteCommand = new DeleteCommand(indicesFirstPerson);

        String expectedMessage = DeleteCommand.MESSAGE_DELETE_PERSON_SUCCESS + personToDelete;

        ModelManager expectedModel = new ModelManager(model.getTrack2Gather(), new UserPrefs());
        expectedModel.deletePerson(personToDelete);

        assertCommandSuccess(deleteCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_validIndicesUnfilteredListInAscendingOrder_success() {
        Person firstPersonToDelete = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        Person secondPersonToDelete = model.getFilteredPersonList().get(INDEX_SECOND_PERSON.getZeroBased());
        ArrayList<Index> indicesMultiplePersons = new ArrayList<>();
        indicesMultiplePersons.add(INDEX_FIRST_PERSON);
        indicesMultiplePersons.add(INDEX_SECOND_PERSON);
        DeleteCommand deleteCommand = new DeleteCommand(indicesMultiplePersons);

        String expectedMessage = DeleteCommand.MESSAGE_DELETE_PERSON_SUCCESS + firstPersonToDelete + "\n"
                + secondPersonToDelete;

        ModelManager expectedModel = new ModelManager(model.getTrack2Gather(), new UserPrefs());
        expectedModel.deletePerson(firstPersonToDelete);
        expectedModel.deletePerson(secondPersonToDelete);

        assertCommandSuccess(deleteCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_validIndicesUnfilteredListInDescendingOrder_success() {
        Person firstPersonToDelete = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        Person secondPersonToDelete = model.getFilteredPersonList().get(INDEX_SECOND_PERSON.getZeroBased());
        ArrayList<Index> indicesMultiplePersons = new ArrayList<>();
        indicesMultiplePersons.add(INDEX_SECOND_PERSON);
        indicesMultiplePersons.add(INDEX_FIRST_PERSON);
        DeleteCommand deleteCommand = new DeleteCommand(indicesMultiplePersons);

        String expectedMessage = DeleteCommand.MESSAGE_DELETE_PERSON_SUCCESS + firstPersonToDelete + "\n"
                + secondPersonToDelete;

        ModelManager expectedModel = new ModelManager(model.getTrack2Gather(), new UserPrefs());
        expectedModel.deletePerson(firstPersonToDelete);
        expectedModel.deletePerson(secondPersonToDelete);

        assertCommandSuccess(deleteCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_validIndicesUnfilteredListInMixedOrder_success() {
        Person firstPersonToDelete = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        Person secondPersonToDelete = model.getFilteredPersonList().get(INDEX_SECOND_PERSON.getZeroBased());
        Person thirdPersonToDelete = model.getFilteredPersonList().get(INDEX_THIRD_PERSON.getZeroBased());
        ArrayList<Index> indicesMultiplePersons = new ArrayList<>();
        indicesMultiplePersons.add(INDEX_THIRD_PERSON);
        indicesMultiplePersons.add(INDEX_FIRST_PERSON);
        indicesMultiplePersons.add(INDEX_SECOND_PERSON);
        DeleteCommand deleteCommand = new DeleteCommand(indicesMultiplePersons);

        String expectedMessage = DeleteCommand.MESSAGE_DELETE_PERSON_SUCCESS + firstPersonToDelete + "\n"
                + secondPersonToDelete + "\n" + thirdPersonToDelete;

        ModelManager expectedModel = new ModelManager(model.getTrack2Gather(), new UserPrefs());
        expectedModel.deletePerson(firstPersonToDelete);
        expectedModel.deletePerson(secondPersonToDelete);
        expectedModel.deletePerson(thirdPersonToDelete);

        assertCommandSuccess(deleteCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredPersonList().size() + 1);
        ArrayList<Index> indicesOutOfBound = new ArrayList<>();
        indicesOutOfBound.add(outOfBoundIndex);
        DeleteCommand deleteCommand = new DeleteCommand(indicesOutOfBound);

        assertCommandFailure(deleteCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void execute_validIndexFilteredList_success() {
        showPersonAtIndex(model, INDEX_FIRST_PERSON);

        Person personToDelete = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        ArrayList<Index> indicesFirstPerson = new ArrayList<>();
        indicesFirstPerson.add(INDEX_FIRST_PERSON);
        DeleteCommand deleteCommand = new DeleteCommand(indicesFirstPerson);

        String expectedMessage = DeleteCommand.MESSAGE_DELETE_PERSON_SUCCESS + personToDelete;

        Model expectedModel = new ModelManager(model.getTrack2Gather(), new UserPrefs());
        expectedModel.deletePerson(personToDelete);
        showNoPerson(expectedModel);

        assertCommandSuccess(deleteCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexFilteredList_throwsCommandException() {
        showPersonAtIndex(model, INDEX_FIRST_PERSON);

        Index outOfBoundIndex = INDEX_SECOND_PERSON;
        // ensures that outOfBoundIndex is still in bounds of persons list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getTrack2Gather().getPersonList().size());
        ArrayList<Index> indicesOutOfBound = new ArrayList<>();
        indicesOutOfBound.add(outOfBoundIndex);
        DeleteCommand deleteCommand = new DeleteCommand(indicesOutOfBound);

        assertCommandFailure(deleteCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        ArrayList<Index> indicesFirstPerson = new ArrayList<>();
        indicesFirstPerson.add(INDEX_FIRST_PERSON);
        DeleteCommand deleteFirstCommand = new DeleteCommand(indicesFirstPerson);

        ArrayList<Index> indicesSecondPerson = new ArrayList<>();
        indicesSecondPerson.add(INDEX_SECOND_PERSON);
        DeleteCommand deleteSecondCommand = new DeleteCommand(indicesSecondPerson);

        // same object -> returns true
        assertTrue(deleteFirstCommand.equals(deleteFirstCommand));

        // same values -> returns true
        DeleteCommand deleteFirstCommandCopy = new DeleteCommand(indicesFirstPerson);
        assertTrue(deleteFirstCommand.equals(deleteFirstCommandCopy));

        // different types -> returns false
        assertFalse(deleteFirstCommand.equals(1));

        // null -> returns false
        assertFalse(deleteFirstCommand.equals(null));

        // different person -> returns false
        assertFalse(deleteFirstCommand.equals(deleteSecondCommand));
    }

    /**
     * Updates {@code model}'s filtered list to show no one.
     */
    private void showNoPerson(Model model) {
        model.updateFilteredPersonList(p -> false);

        assertTrue(model.getFilteredPersonList().isEmpty());
    }
}
