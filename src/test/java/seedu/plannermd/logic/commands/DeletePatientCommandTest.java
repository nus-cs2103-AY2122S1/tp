package seedu.plannermd.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.plannermd.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.plannermd.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.plannermd.logic.commands.CommandTestUtil.showPersonAtIndex;
import static seedu.plannermd.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.plannermd.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
import static seedu.plannermd.testutil.TypicalPersons.getTypicalPlannerMd;

import org.junit.jupiter.api.Test;

import seedu.plannermd.commons.core.Messages;
import seedu.plannermd.commons.core.index.Index;
import seedu.plannermd.logic.commands.deletecommand.DeletePatientCommand;
import seedu.plannermd.model.Model;
import seedu.plannermd.model.ModelManager;
import seedu.plannermd.model.UserPrefs;
import seedu.plannermd.model.person.Person;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code DeletePatientCommand}.
 */
public class DeletePatientCommandTest {

    private Model model = new ModelManager(getTypicalPlannerMd(), new UserPrefs());

    @Test
    public void execute_validIndexUnfilteredList_success() {
        Person personToDelete = model.getFilteredPatientList().get(INDEX_FIRST_PERSON.getZeroBased());
        DeletePatientCommand deletePatientCommand = new DeletePatientCommand(INDEX_FIRST_PERSON);

        String expectedMessage = String.format(DeletePatientCommand.MESSAGE_DELETE_PATIENT_SUCCESS, personToDelete);

        ModelManager expectedModel = new ModelManager(model.getPlannerMd(), new UserPrefs());
        expectedModel.deletePatient(personToDelete);

        assertCommandSuccess(deletePatientCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredPatientList().size() + 1);
        DeletePatientCommand deletePatientCommand = new DeletePatientCommand(outOfBoundIndex);

        assertCommandFailure(deletePatientCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void execute_validIndexFilteredList_success() {
        showPersonAtIndex(model, INDEX_FIRST_PERSON);

        Person personToDelete = model.getFilteredPatientList().get(INDEX_FIRST_PERSON.getZeroBased());
        DeletePatientCommand deletePatientCommand = new DeletePatientCommand(INDEX_FIRST_PERSON);

        String expectedMessage = String.format(DeletePatientCommand.MESSAGE_DELETE_PATIENT_SUCCESS, personToDelete);

        Model expectedModel = new ModelManager(model.getPlannerMd(), new UserPrefs());
        expectedModel.deletePatient(personToDelete);
        showNoPerson(expectedModel);

        assertCommandSuccess(deletePatientCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexFilteredList_throwsCommandException() {
        showPersonAtIndex(model, INDEX_FIRST_PERSON);

        Index outOfBoundIndex = INDEX_SECOND_PERSON;
        // ensures that outOfBoundIndex is still in bounds of plannermd list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getPlannerMd().getPatientList().size());

        DeletePatientCommand deletePatientCommand = new DeletePatientCommand(outOfBoundIndex);

        assertCommandFailure(deletePatientCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        DeletePatientCommand deleteFirstCommand = new DeletePatientCommand(INDEX_FIRST_PERSON);
        DeletePatientCommand deleteSecondCommand = new DeletePatientCommand(INDEX_SECOND_PERSON);

        // same object -> returns true
        assertTrue(deleteFirstCommand.equals(deleteFirstCommand));

        // same values -> returns true
        DeletePatientCommand deleteFirstCommandCopy = new DeletePatientCommand(INDEX_FIRST_PERSON);
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
        model.updateFilteredPatientList(p -> false);

        assertTrue(model.getFilteredPatientList().isEmpty());
    }
}
