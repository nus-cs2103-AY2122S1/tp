package seedu.plannermd.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.plannermd.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.plannermd.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.plannermd.logic.commands.CommandTestUtil.showPatientAtIndex;
import static seedu.plannermd.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.plannermd.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
import static seedu.plannermd.testutil.TypicalPlannerMd.getTypicalPlannerMd;

import org.junit.jupiter.api.Test;

import seedu.plannermd.commons.core.Messages;
import seedu.plannermd.commons.core.index.Index;
import seedu.plannermd.logic.commands.remarkcommand.RemarkCommand;
import seedu.plannermd.logic.commands.remarkcommand.RemarkDoctorCommand;
import seedu.plannermd.logic.commands.remarkcommand.RemarkPatientCommand;
import seedu.plannermd.model.Model;
import seedu.plannermd.model.ModelManager;
import seedu.plannermd.model.PlannerMd;
import seedu.plannermd.model.UserPrefs;
import seedu.plannermd.model.patient.Patient;
import seedu.plannermd.model.person.Remark;
import seedu.plannermd.testutil.patient.PatientBuilder;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * RemarkPatientCommand.
 */
class RemarkPatientCommandTest {

    private static final String SAMPLE_REMARK = "Monthly insulin prescription";

    private Model model = new ModelManager(getTypicalPlannerMd(), new UserPrefs());

    @Test
    void execute_sampleRemarkUnfilteredList_success() {
        Patient firstPatient = model.getFilteredPatientList().get(INDEX_FIRST_PERSON.getZeroBased());
        Patient editedPatient = new PatientBuilder(firstPatient).withRemark(SAMPLE_REMARK).build();

        RemarkPatientCommand remarkCommand = new RemarkPatientCommand(INDEX_FIRST_PERSON, new Remark(SAMPLE_REMARK));

        String expectedMessage = String.format(RemarkPatientCommand.MESSAGE_ADD_PATIENT_REMARK_SUCCESS,
                editedPatient);

        Model expectedModel = new ModelManager(new PlannerMd(model.getPlannerMd()), new UserPrefs());
        expectedModel.setPatient(model.getFilteredPatientList().get(INDEX_FIRST_PERSON.getZeroBased()), editedPatient);

        assertCommandSuccess(remarkCommand, model, expectedMessage, expectedModel);
    }

    /**
     * Add Empty Remark/ Remove current remarks.
     */
    @Test
    public void execute_emptyRemarkUnfilteredList_success() {
        Index indexLastPatient = Index.fromOneBased(model.getFilteredPatientList().size());
        Patient lastPatient = model.getFilteredPatientList().get(indexLastPatient.getZeroBased());
        Patient editedPatient = new PatientBuilder(lastPatient).withRemark(Remark.getEmptyRemark().value).build();

        RemarkPatientCommand remarkCommand = new RemarkPatientCommand(indexLastPatient, new Remark(""));

        String expectedMessage = String.format(RemarkPatientCommand.MESSAGE_DELETE_PATIENT_REMARK_SUCCESS,
                editedPatient);

        Model expectedModel = new ModelManager(new PlannerMd(model.getPlannerMd()), new UserPrefs());
        expectedModel.setPatient(model.getFilteredPatientList().get(indexLastPatient.getZeroBased()), editedPatient);

        assertCommandSuccess(remarkCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_filteredList_success() {
        showPatientAtIndex(model, INDEX_FIRST_PERSON);

        Patient patientInFilteredList = model.getFilteredPatientList().get(INDEX_FIRST_PERSON.getZeroBased());
        Patient editedPatient = new PatientBuilder(patientInFilteredList).withRemark(SAMPLE_REMARK).build();

        RemarkPatientCommand remarkCommand = new RemarkPatientCommand(INDEX_FIRST_PERSON, new Remark(SAMPLE_REMARK));

        String expectedMessage = String.format(RemarkPatientCommand.MESSAGE_ADD_PATIENT_REMARK_SUCCESS,
                editedPatient);

        Model expectedModel = new ModelManager(new PlannerMd(model.getPlannerMd()), new UserPrefs());
        expectedModel.setPatient(model.getFilteredPatientList().get(0), editedPatient);

        assertCommandSuccess(remarkCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidPatientIndexUnfilteredList_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredPatientList().size() + 1);
        RemarkCommand remarkCommand = new RemarkPatientCommand(outOfBoundIndex, new Remark(SAMPLE_REMARK));

        assertCommandFailure(remarkCommand, model, Messages.MESSAGE_INVALID_PATIENT_DISPLAYED_INDEX);
    }

    /**
     * Remark Patient in filtered list where index is larger than size of filtered list,
     * but smaller than size of plannermd
     */
    @Test
    public void execute_invalidPatientIndexFilteredList_failure() {
        showPatientAtIndex(model, INDEX_FIRST_PERSON);
        Index outOfBoundIndex = INDEX_SECOND_PERSON;

        // ensures that outOfBoundIndex is still in bounds of plannermd list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getPlannerMd().getPatientList().size());

        RemarkPatientCommand remarkCommand = new RemarkPatientCommand(outOfBoundIndex,
                new Remark(SAMPLE_REMARK));

        assertCommandFailure(remarkCommand, model, Messages.MESSAGE_INVALID_PATIENT_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        final RemarkCommand standardCommand = new RemarkPatientCommand(INDEX_FIRST_PERSON, new Remark(SAMPLE_REMARK));

        // same values -> returns true
        RemarkCommand commandWithSameValues = new RemarkPatientCommand(INDEX_FIRST_PERSON, new Remark(SAMPLE_REMARK));
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearCommand()));

        // different index -> returns false
        assertFalse(standardCommand.equals(new RemarkPatientCommand(INDEX_SECOND_PERSON, new Remark(SAMPLE_REMARK))));

        // different descriptor -> returns false
        assertFalse(standardCommand.equals(new RemarkPatientCommand(INDEX_FIRST_PERSON,
                new Remark("Different Remark"))));

        // RemarkPatientCommand vs RemarkDoctorCommand
        assertFalse(standardCommand.equals(new RemarkDoctorCommand(INDEX_FIRST_PERSON, new Remark(SAMPLE_REMARK))));
    }
}
