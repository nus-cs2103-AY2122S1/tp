package seedu.plannermd.logic.commands.deletecommand;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.plannermd.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.plannermd.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.plannermd.logic.commands.CommandTestUtil.showDoctorAtIndex;
import static seedu.plannermd.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.plannermd.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
import static seedu.plannermd.testutil.TypicalPlannerMd.getTypicalPlannerMd;

import org.junit.jupiter.api.Test;

import seedu.plannermd.commons.core.Messages;
import seedu.plannermd.commons.core.index.Index;
import seedu.plannermd.model.Model;
import seedu.plannermd.model.ModelManager;
import seedu.plannermd.model.UserPrefs;
import seedu.plannermd.model.doctor.Doctor;

/**
 * Contains integration tests (interaction with the Model) and unit tests
 * for {@code DeleteDoctorCommand}.
 */
public class DeleteDoctorCommandTest {

    private Model model = new ModelManager(getTypicalPlannerMd(), new UserPrefs());

    @Test
    public void execute_validIndexUnfilteredList_success() {
        Doctor doctorToDelete = model.getFilteredDoctorList().get(INDEX_FIRST_PERSON.getZeroBased());
        DeleteDoctorCommand deleteDoctorCommand = new DeleteDoctorCommand(INDEX_FIRST_PERSON);

        String expectedMessage = String.format(DeleteDoctorCommand.MESSAGE_DELETE_DOCTOR_SUCCESS, doctorToDelete);

        ModelManager expectedModel = new ModelManager(model.getPlannerMd(), new UserPrefs());
        expectedModel.deleteAppointmentsWithPerson(doctorToDelete);
        expectedModel.deleteDoctor(doctorToDelete);

        assertCommandSuccess(deleteDoctorCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredDoctorList().size() + 1);
        DeleteDoctorCommand deleteDoctorCommand = new DeleteDoctorCommand(outOfBoundIndex);

        assertCommandFailure(deleteDoctorCommand, model, Messages.MESSAGE_INVALID_DOCTOR_DISPLAYED_INDEX);
    }

    @Test
    public void execute_validIndexFilteredList_success() {
        showDoctorAtIndex(model, INDEX_FIRST_PERSON);

        Doctor doctorToDelete = model.getFilteredDoctorList().get(INDEX_FIRST_PERSON.getZeroBased());
        DeleteDoctorCommand deleteDoctorCommand = new DeleteDoctorCommand(INDEX_FIRST_PERSON);

        String expectedMessage = String.format(DeleteDoctorCommand.MESSAGE_DELETE_DOCTOR_SUCCESS, doctorToDelete);

        Model expectedModel = new ModelManager(model.getPlannerMd(), new UserPrefs());
        expectedModel.deleteAppointmentsWithPerson(doctorToDelete);
        expectedModel.deleteDoctor(doctorToDelete);
        showNoDoctor(expectedModel);

        assertCommandSuccess(deleteDoctorCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexFilteredList_throwsCommandException() {
        showDoctorAtIndex(model, INDEX_FIRST_PERSON);

        Index outOfBoundIndex = INDEX_SECOND_PERSON;
        // ensures that outOfBoundIndex is still in bounds of plannermd list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getPlannerMd().getDoctorList().size());

        DeleteDoctorCommand deleteDoctorCommand = new DeleteDoctorCommand(outOfBoundIndex);

        assertCommandFailure(deleteDoctorCommand, model, Messages.MESSAGE_INVALID_DOCTOR_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        DeleteDoctorCommand deleteFirstCommand = new DeleteDoctorCommand(INDEX_FIRST_PERSON);
        DeleteDoctorCommand deleteSecondCommand = new DeleteDoctorCommand(INDEX_SECOND_PERSON);

        // same object -> returns true
        assertTrue(deleteFirstCommand.equals(deleteFirstCommand));

        // same values -> returns true
        DeleteDoctorCommand deleteFirstCommandCopy = new DeleteDoctorCommand(INDEX_FIRST_PERSON);
        assertTrue(deleteFirstCommand.equals(deleteFirstCommandCopy));

        // different types -> returns false
        assertFalse(deleteFirstCommand.equals(1));

        // null -> returns false
        assertFalse(deleteFirstCommand.equals(null));

        // different person -> returns false
        assertFalse(deleteFirstCommand.equals(deleteSecondCommand));

        // DeleteDoctorCommand vs DeletePatientCommand -> returns false
        DeletePatientCommand deleteFirstPatientCommand = new DeletePatientCommand(INDEX_FIRST_PERSON);
        assertFalse(deleteFirstCommand.equals(deleteFirstPatientCommand));
    }

    /**
     * Updates {@code model}'s filtered list to show no one.
     */
    private void showNoDoctor(Model model) {
        model.updateFilteredDoctorList(p -> false);

        assertTrue(model.getFilteredDoctorList().isEmpty());
    }

}
