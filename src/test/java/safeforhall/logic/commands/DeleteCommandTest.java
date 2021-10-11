package safeforhall.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static safeforhall.logic.commands.CommandTestUtil.assertCommandFailure;
import static safeforhall.logic.commands.CommandTestUtil.assertCommandSuccess;
import static safeforhall.logic.commands.CommandTestUtil.showPersonAtIndex;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import safeforhall.commons.core.Messages;
import safeforhall.commons.core.index.Index;
import safeforhall.model.Model;
import safeforhall.model.ModelManager;
import safeforhall.model.UserPrefs;
import safeforhall.model.person.Person;
import safeforhall.testutil.TypicalIndexes;
import safeforhall.testutil.TypicalPersons;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code DeleteCommand}.
 */
public class DeleteCommandTest {

    private Model model = new ModelManager(TypicalPersons.getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_validIndexUnfilteredList_success() {
        int firstPersonIndex = TypicalIndexes.INDEX_FIRST_PERSON.getZeroBased();
        int secondPersonIndex = TypicalIndexes.INDEX_SECOND_PERSON.getZeroBased();
        Person firstPersonToDelete = model.getFilteredPersonList().get(firstPersonIndex);
        Person secondPersonToDelete = model.getFilteredPersonList().get(secondPersonIndex);
        ArrayList<Index> indexArray = new ArrayList<>();
        indexArray.add(TypicalIndexes.INDEX_FIRST_PERSON);
        indexArray.add(TypicalIndexes.INDEX_SECOND_PERSON);
        DeleteCommand deleteCommand = new DeleteCommand(indexArray);

        String deletedResident = "1.\t" + firstPersonToDelete.getName() + "\n"
                + "2.\t" + secondPersonToDelete.getName() + "\n";
        String expectedMessage = String.format(DeleteCommand.MESSAGE_DELETE_PERSON_SUCCESS, deletedResident);

        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.deletePerson(firstPersonToDelete);
        expectedModel.deletePerson(secondPersonToDelete);

        assertCommandSuccess(deleteCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredPersonList().size() + 1);
        ArrayList<Index> indexArray = new ArrayList<>();
        indexArray.add(outOfBoundIndex);
        DeleteCommand deleteCommand = new DeleteCommand(indexArray);

        assertCommandFailure(deleteCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void execute_validIndexFilteredList_success() {
        showPersonAtIndex(model, TypicalIndexes.INDEX_FIRST_PERSON);

        Person personToDelete = model.getFilteredPersonList().get(TypicalIndexes.INDEX_FIRST_PERSON.getZeroBased());
        ArrayList<Index> indexArray = new ArrayList<>();
        indexArray.add(TypicalIndexes.INDEX_FIRST_PERSON);
        DeleteCommand deleteCommand = new DeleteCommand(indexArray);

        String deletedResident = "1.\t" + personToDelete.getName() + "\n";
        String expectedMessage = String.format(DeleteCommand.MESSAGE_DELETE_PERSON_SUCCESS, deletedResident);

        Model expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.deletePerson(personToDelete);
        showNoPerson(expectedModel);

        assertCommandSuccess(deleteCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexFilteredList_throwsCommandException() {
        showPersonAtIndex(model, TypicalIndexes.INDEX_FIRST_PERSON);

        Index outOfBoundIndex = TypicalIndexes.INDEX_SECOND_PERSON;
        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getAddressBook().getPersonList().size());

        ArrayList<Index> indexArray = new ArrayList<>();
        indexArray.add(outOfBoundIndex);
        DeleteCommand deleteCommand = new DeleteCommand(indexArray);

        assertCommandFailure(deleteCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        ArrayList<Index> firstIndexArray = new ArrayList<>();
        firstIndexArray.add(TypicalIndexes.INDEX_FIRST_PERSON);
        DeleteCommand deleteFirstCommand = new DeleteCommand(firstIndexArray);
        ArrayList<Index> secondIndexArray = new ArrayList<>();
        secondIndexArray.add(TypicalIndexes.INDEX_SECOND_PERSON);
        DeleteCommand deleteSecondCommand = new DeleteCommand(secondIndexArray);

        // same object -> returns true
        assertTrue(deleteFirstCommand.equals(deleteFirstCommand));

        // same values -> returns true
        DeleteCommand deleteFirstCommandCopy = new DeleteCommand(firstIndexArray);
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
