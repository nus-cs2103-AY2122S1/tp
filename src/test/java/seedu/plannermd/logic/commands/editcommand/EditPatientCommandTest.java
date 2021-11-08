package seedu.plannermd.logic.commands.editcommand;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.plannermd.logic.commands.CommandTestUtil.DESC_AMY;
import static seedu.plannermd.logic.commands.CommandTestUtil.DESC_BOB;
import static seedu.plannermd.logic.commands.CommandTestUtil.DESC_DR_AMY;
import static seedu.plannermd.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.plannermd.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.plannermd.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.plannermd.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.plannermd.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.plannermd.logic.commands.CommandTestUtil.showPatientAtIndex;
import static seedu.plannermd.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.plannermd.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
import static seedu.plannermd.testutil.TypicalPlannerMd.getTypicalPlannerMd;

import org.junit.jupiter.api.Test;

import seedu.plannermd.commons.core.Messages;
import seedu.plannermd.commons.core.index.Index;
import seedu.plannermd.logic.commands.ClearCommand;
import seedu.plannermd.logic.commands.editcommand.EditPatientCommand.EditPatientDescriptor;
import seedu.plannermd.model.Model;
import seedu.plannermd.model.ModelManager;
import seedu.plannermd.model.PlannerMd;
import seedu.plannermd.model.UserPrefs;
import seedu.plannermd.model.patient.Patient;
import seedu.plannermd.model.person.Person;
import seedu.plannermd.testutil.patient.EditPatientDescriptorBuilder;
import seedu.plannermd.testutil.patient.PatientBuilder;

/**
 * Contains integration tests (interaction with the Model) and unit tests for EditPatientCommand.
 */
public class EditPatientCommandTest {

    private Model model = new ModelManager(getTypicalPlannerMd(), new UserPrefs());

    @Test
    public void execute_allFieldsSpecifiedUnfilteredList_success() {
        PatientBuilder editedPatientBuilder = new PatientBuilder()
                .withRemark(model.getFilteredPatientList().get(0).getRemark().value);
        Patient editedPatient = editedPatientBuilder.build();
        EditPatientDescriptor descriptor = new EditPatientDescriptorBuilder(editedPatient).build();
        EditPatientCommand editPatientCommand = new EditPatientCommand(INDEX_FIRST_PERSON, descriptor);

        String expectedMessage = String.format(EditPatientCommand.MESSAGE_EDIT_PATIENT_SUCCESS, editedPatient);

        Model expectedModel = new ModelManager(new PlannerMd(model.getPlannerMd()), new UserPrefs());
        expectedModel.setPatient(model.getFilteredPatientList().get(0), editedPatient);
        expectedModel.editAppointmentsWithPerson(model.getFilteredPatientList().get(0), editedPatient);

        assertCommandSuccess(editPatientCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_someFieldsSpecifiedUnfilteredList_success() {
        Index indexLastPerson = Index.fromOneBased(model.getFilteredPatientList().size());
        Patient lastPatient = model.getFilteredPatientList().get(indexLastPerson.getZeroBased());

        PatientBuilder patientInList = new PatientBuilder(lastPatient);
        Patient editedPatient = patientInList.withName(VALID_NAME_BOB).withPhone(VALID_PHONE_BOB)
                .withTags(VALID_TAG_HUSBAND).build();

        EditPatientCommand.EditPatientDescriptor descriptor = new EditPatientDescriptorBuilder()
                .withName(VALID_NAME_BOB).withPhone(VALID_PHONE_BOB).withTags(VALID_TAG_HUSBAND).build();
        EditPatientCommand editPatientCommand = new EditPatientCommand(indexLastPerson, descriptor);

        String expectedMessage = String.format(EditPatientCommand.MESSAGE_EDIT_PATIENT_SUCCESS, editedPatient);

        Model expectedModel = new ModelManager(new PlannerMd(model.getPlannerMd()), new UserPrefs());
        expectedModel.setPatient(lastPatient, editedPatient);
        expectedModel.editAppointmentsWithPerson(lastPatient, editedPatient);

        assertCommandSuccess(editPatientCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_noFieldSpecifiedUnfilteredList_success() {
        EditPatientCommand editPatientCommand = new EditPatientCommand(INDEX_FIRST_PERSON, new EditPatientDescriptor());
        Person editedPerson = model.getFilteredPatientList().get(INDEX_FIRST_PERSON.getZeroBased());

        String expectedMessage = String.format(EditPatientCommand.MESSAGE_EDIT_PATIENT_SUCCESS, editedPerson);

        Model expectedModel = new ModelManager(new PlannerMd(model.getPlannerMd()), new UserPrefs());

        assertCommandSuccess(editPatientCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_filteredList_success() {
        showPatientAtIndex(model, INDEX_FIRST_PERSON);

        Patient patientInFilteredList = model.getFilteredPatientList().get(INDEX_FIRST_PERSON.getZeroBased());
        Patient editedPatient = new PatientBuilder(patientInFilteredList).withName(VALID_NAME_BOB).build();
        EditPatientCommand editPatientCommand = new EditPatientCommand(INDEX_FIRST_PERSON,
                new EditPatientDescriptorBuilder().withName(VALID_NAME_BOB).build());

        String expectedMessage = String.format(EditPatientCommand.MESSAGE_EDIT_PATIENT_SUCCESS, editedPatient);

        Model expectedModel = new ModelManager(new PlannerMd(model.getPlannerMd()), new UserPrefs());
        showPatientAtIndex(expectedModel, INDEX_FIRST_PERSON);
        expectedModel.setPatient(model.getFilteredPatientList().get(0), editedPatient);
        expectedModel.editAppointmentsWithPerson(model.getFilteredPatientList().get(0), editedPatient);

        assertCommandSuccess(editPatientCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_duplicatePatientUnfilteredList_failure() {
        Patient firstPatient = model.getFilteredPatientList().get(INDEX_FIRST_PERSON.getZeroBased());
        EditPatientDescriptor descriptor = new EditPatientDescriptorBuilder(firstPatient).build();
        EditPatientCommand editPatientCommand = new EditPatientCommand(INDEX_SECOND_PERSON, descriptor);

        assertCommandFailure(editPatientCommand, model, EditPatientCommand.MESSAGE_DUPLICATE_PATIENT);
    }

    @Test
    public void execute_duplicatePatientFilteredList_failure() {
        showPatientAtIndex(model, INDEX_FIRST_PERSON);

        // edit person in filtered list into a duplicate in plannermd
        Patient patientInList = model.getPlannerMd().getPatientList().get(INDEX_SECOND_PERSON.getZeroBased());
        EditPatientCommand editPatientCommand = new EditPatientCommand(INDEX_FIRST_PERSON,
                new EditPatientDescriptorBuilder(patientInList).build());

        assertCommandFailure(editPatientCommand, model, EditPatientCommand.MESSAGE_DUPLICATE_PATIENT);
    }

    @Test
    public void execute_invalidPatientIndexUnfilteredList_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredPatientList().size() + 1);
        EditPatientDescriptor descriptor = new EditPatientDescriptorBuilder().withName(VALID_NAME_BOB).build();
        EditPatientCommand editPatientCommand = new EditPatientCommand(outOfBoundIndex, descriptor);

        assertCommandFailure(editPatientCommand, model, Messages.MESSAGE_INVALID_PATIENT_DISPLAYED_INDEX);
    }

    /**
     * Edit filtered list where index is larger than size of filtered list,
     * but smaller than size of plannermd
     */
    @Test
    public void execute_invalidPersonIndexFilteredList_failure() {
        showPatientAtIndex(model, INDEX_FIRST_PERSON);
        Index outOfBoundIndex = INDEX_SECOND_PERSON;
        // ensures that outOfBoundIndex is still in bounds of plannermd list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getPlannerMd().getPatientList().size());

        EditPatientCommand editPatientCommand = new EditPatientCommand(outOfBoundIndex,
                new EditPatientDescriptorBuilder().withName(VALID_NAME_BOB).build());

        assertCommandFailure(editPatientCommand, model, Messages.MESSAGE_INVALID_PATIENT_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        final EditPatientCommand standardCommand = new EditPatientCommand(INDEX_FIRST_PERSON, DESC_AMY);

        // same values -> returns true
        EditPatientDescriptor copyDescriptor = new EditPatientDescriptor(DESC_AMY);
        EditPatientCommand commandWithSameValues = new EditPatientCommand(INDEX_FIRST_PERSON, copyDescriptor);
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearCommand()));

        // different index -> returns false
        assertFalse(standardCommand.equals(new EditPatientCommand(INDEX_SECOND_PERSON, DESC_AMY)));

        // different descriptor -> returns false
        assertFalse(standardCommand.equals(new EditPatientCommand(INDEX_FIRST_PERSON, DESC_BOB)));

        // EditDoctorCommand vs EditPatientCommand -> returns false
        EditDoctorCommand editDoctorCommand = new EditDoctorCommand(INDEX_FIRST_PERSON, DESC_DR_AMY);
        assertFalse(standardCommand.equals(editDoctorCommand));
    }
}
