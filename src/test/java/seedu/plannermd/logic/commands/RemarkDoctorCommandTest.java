package seedu.plannermd.logic.commands;

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
import seedu.plannermd.logic.commands.remarkcommand.RemarkCommand;
import seedu.plannermd.logic.commands.remarkcommand.RemarkDoctorCommand;
import seedu.plannermd.logic.commands.remarkcommand.RemarkPatientCommand;
import seedu.plannermd.model.Model;
import seedu.plannermd.model.ModelManager;
import seedu.plannermd.model.PlannerMd;
import seedu.plannermd.model.UserPrefs;
import seedu.plannermd.model.doctor.Doctor;
import seedu.plannermd.model.person.Remark;
import seedu.plannermd.testutil.doctor.DoctorBuilder;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * RemarkDoctorCommand.
 */
public class RemarkDoctorCommandTest {

    private static final String SAMPLE_REMARK = "Handsome doctor";

    private Model model = new ModelManager(getTypicalPlannerMd(), new UserPrefs());

    @Test
    void execute_sampleRemarkUnfilteredList_success() {
        Doctor firstDoctor = model.getFilteredDoctorList().get(INDEX_FIRST_PERSON.getZeroBased());
        Doctor editedDoctor = new DoctorBuilder(firstDoctor).withRemark(SAMPLE_REMARK).build();

        RemarkDoctorCommand remarkCommand = new RemarkDoctorCommand(INDEX_FIRST_PERSON, new Remark(SAMPLE_REMARK));

        String expectedMessage = String.format(RemarkDoctorCommand.MESSAGE_ADD_DOCTOR_REMARK_SUCCESS,
                editedDoctor);

        Model expectedModel = new ModelManager(new PlannerMd(model.getPlannerMd()), new UserPrefs());
        expectedModel.setDoctor(model.getFilteredDoctorList().get(INDEX_FIRST_PERSON.getZeroBased()), editedDoctor);

        assertCommandSuccess(remarkCommand, model, expectedMessage, expectedModel);
    }

    /**
     * Add Empty Remark/ Remove current remarks.
     */
    @Test
    public void execute_emptyRemarkUnfilteredList_success() {
        Index indexLastDoctor = Index.fromOneBased(model.getFilteredDoctorList().size());
        Doctor lastDoctor = model.getFilteredDoctorList().get(indexLastDoctor.getZeroBased());
        Doctor editedDoctor = new DoctorBuilder(lastDoctor).withRemark(Remark.getEmptyRemark().value).build();

        RemarkDoctorCommand remarkCommand = new RemarkDoctorCommand(indexLastDoctor, new Remark(""));

        String expectedMessage = String.format(RemarkDoctorCommand.MESSAGE_DELETE_DOCTOR_REMARK_SUCCESS,
                editedDoctor);

        Model expectedModel = new ModelManager(new PlannerMd(model.getPlannerMd()), new UserPrefs());
        expectedModel.setDoctor(model.getFilteredDoctorList().get(indexLastDoctor.getZeroBased()), editedDoctor);

        assertCommandSuccess(remarkCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_filteredList_success() {
        showDoctorAtIndex(model, INDEX_FIRST_PERSON);

        Doctor patientInFilteredList = model.getFilteredDoctorList().get(INDEX_FIRST_PERSON.getZeroBased());
        Doctor editedDoctor = new DoctorBuilder(patientInFilteredList).withRemark(SAMPLE_REMARK).build();

        RemarkDoctorCommand remarkCommand = new RemarkDoctorCommand(INDEX_FIRST_PERSON, new Remark(SAMPLE_REMARK));

        String expectedMessage = String.format(RemarkDoctorCommand.MESSAGE_ADD_DOCTOR_REMARK_SUCCESS,
                editedDoctor);

        Model expectedModel = new ModelManager(new PlannerMd(model.getPlannerMd()), new UserPrefs());
        expectedModel.setDoctor(model.getFilteredDoctorList().get(0), editedDoctor);

        assertCommandSuccess(remarkCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidDoctorIndexUnfilteredList_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredDoctorList().size() + 1);
        RemarkCommand remarkCommand = new RemarkDoctorCommand(outOfBoundIndex, new Remark(SAMPLE_REMARK));

        assertCommandFailure(remarkCommand, model, Messages.MESSAGE_INVALID_PATIENT_DISPLAYED_INDEX);
    }

    /**
     * Remark Doctor in filtered list where index is larger than size of filtered list,
     * but smaller than size of plannermd
     */
    @Test
    public void execute_invalidDoctorIndexFilteredList_failure() {
        showDoctorAtIndex(model, INDEX_FIRST_PERSON);
        Index outOfBoundIndex = INDEX_SECOND_PERSON;

        // ensures that outOfBoundIndex is still in bounds of plannermd list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getPlannerMd().getDoctorList().size());

        RemarkDoctorCommand remarkCommand = new RemarkDoctorCommand(outOfBoundIndex,
                new Remark(SAMPLE_REMARK));

        assertCommandFailure(remarkCommand, model, Messages.MESSAGE_INVALID_PATIENT_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        final RemarkCommand standardCommand = new RemarkDoctorCommand(INDEX_FIRST_PERSON, new Remark(SAMPLE_REMARK));

        // same values -> returns true
        RemarkCommand commandWithSameValues = new RemarkDoctorCommand(INDEX_FIRST_PERSON, new Remark(SAMPLE_REMARK));
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearCommand()));

        // different index -> returns false
        assertFalse(standardCommand.equals(new RemarkDoctorCommand(INDEX_SECOND_PERSON, new Remark(SAMPLE_REMARK))));

        // different descriptor -> returns false
        assertFalse(standardCommand.equals(new RemarkDoctorCommand(INDEX_FIRST_PERSON,
                new Remark("Different Remark"))));

        // RemarkDoctorCommand vs RemarkPatientCommand
        assertFalse(standardCommand.equals(new RemarkPatientCommand(INDEX_FIRST_PERSON, new Remark(SAMPLE_REMARK))));
    }
}
