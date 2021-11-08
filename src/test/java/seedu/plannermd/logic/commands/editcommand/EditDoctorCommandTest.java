package seedu.plannermd.logic.commands.editcommand;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.plannermd.logic.commands.CommandTestUtil.DESC_AMY;
import static seedu.plannermd.logic.commands.CommandTestUtil.DESC_DR_AMY;
import static seedu.plannermd.logic.commands.CommandTestUtil.DESC_DR_BOB;
import static seedu.plannermd.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.plannermd.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.plannermd.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.plannermd.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.plannermd.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.plannermd.logic.commands.CommandTestUtil.showDoctorAtIndex;
import static seedu.plannermd.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.plannermd.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
import static seedu.plannermd.testutil.TypicalPlannerMd.getTypicalPlannerMd;

import org.junit.jupiter.api.Test;

import seedu.plannermd.commons.core.Messages;
import seedu.plannermd.commons.core.index.Index;
import seedu.plannermd.logic.commands.ClearCommand;
import seedu.plannermd.model.Model;
import seedu.plannermd.model.ModelManager;
import seedu.plannermd.model.PlannerMd;
import seedu.plannermd.model.UserPrefs;
import seedu.plannermd.model.doctor.Doctor;
import seedu.plannermd.testutil.doctor.DoctorBuilder;
import seedu.plannermd.testutil.doctor.EditDoctorDescriptorBuilder;

/**
 * Contains integration tests (interaction with the Model) and unit tests for EditDoctorCommand.
 */
public class EditDoctorCommandTest {

    private final Model model = new ModelManager(getTypicalPlannerMd(), new UserPrefs());

    @Test
    public void execute_allFieldsSpecifiedUnfilteredList_success() {
        DoctorBuilder editedDoctorBuilder = new DoctorBuilder()
                .withRemark(model.getFilteredPatientList().get(0).getRemark().value);
        Doctor editedDoctor = editedDoctorBuilder.build();
        EditDoctorCommand.EditDoctorDescriptor descriptor = new EditDoctorDescriptorBuilder(editedDoctor).build();
        EditDoctorCommand editDoctorCommand = new EditDoctorCommand(INDEX_FIRST_PERSON, descriptor);

        String expectedMessage = String.format(EditDoctorCommand.MESSAGE_EDIT_DOCTOR_SUCCESS, editedDoctor);

        Model expectedModel = new ModelManager(new PlannerMd(model.getPlannerMd()), new UserPrefs());
        expectedModel.setDoctor(model.getFilteredDoctorList().get(0), editedDoctor);
        expectedModel.editAppointmentsWithPerson(model.getFilteredDoctorList().get(0), editedDoctor);

        assertCommandSuccess(editDoctorCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_someFieldsSpecifiedUnfilteredList_success() {
        Index indexLastPerson = Index.fromOneBased(model.getFilteredDoctorList().size());
        Doctor lastDoctor = model.getFilteredDoctorList().get(indexLastPerson.getZeroBased());

        DoctorBuilder doctorInList = new DoctorBuilder(lastDoctor);
        Doctor editedDoctor = doctorInList.withName(VALID_NAME_BOB).withPhone(VALID_PHONE_BOB)
                .withTags(VALID_TAG_HUSBAND).build();

        EditDoctorCommand.EditDoctorDescriptor descriptor = new EditDoctorDescriptorBuilder().withName(VALID_NAME_BOB)
                .withPhone(VALID_PHONE_BOB).withTags(VALID_TAG_HUSBAND).build();
        EditDoctorCommand editDoctorCommand = new EditDoctorCommand(indexLastPerson, descriptor);

        String expectedMessage = String.format(EditDoctorCommand.MESSAGE_EDIT_DOCTOR_SUCCESS, editedDoctor);

        Model expectedModel = new ModelManager(new PlannerMd(model.getPlannerMd()), new UserPrefs());
        expectedModel.setDoctor(lastDoctor, editedDoctor);
        expectedModel.editAppointmentsWithPerson(lastDoctor, editedDoctor);

        assertCommandSuccess(editDoctorCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_noFieldSpecifiedUnfilteredList_success() {
        EditDoctorCommand editDoctorCommand =
                new EditDoctorCommand(INDEX_FIRST_PERSON, new EditDoctorCommand.EditDoctorDescriptor());
        Doctor editedDoctor = model.getFilteredDoctorList().get(INDEX_FIRST_PERSON.getZeroBased());

        String expectedMessage = String.format(EditDoctorCommand.MESSAGE_EDIT_DOCTOR_SUCCESS, editedDoctor);

        Model expectedModel = new ModelManager(new PlannerMd(model.getPlannerMd()), new UserPrefs());

        assertCommandSuccess(editDoctorCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_filteredList_success() {
        showDoctorAtIndex(model, INDEX_FIRST_PERSON);

        Doctor doctorInFilteredList = model.getFilteredDoctorList().get(INDEX_FIRST_PERSON.getZeroBased());
        Doctor editedDoctor = new DoctorBuilder(doctorInFilteredList).withName(VALID_NAME_BOB).build();
        EditDoctorCommand editDoctorCommand = new EditDoctorCommand(INDEX_FIRST_PERSON,
                new EditDoctorDescriptorBuilder().withName(VALID_NAME_BOB).build());

        String expectedMessage = String.format(EditDoctorCommand.MESSAGE_EDIT_DOCTOR_SUCCESS, editedDoctor);

        Model expectedModel = new ModelManager(new PlannerMd(model.getPlannerMd()), new UserPrefs());
        showDoctorAtIndex(expectedModel, INDEX_FIRST_PERSON);
        expectedModel.setDoctor(model.getFilteredDoctorList().get(0), editedDoctor);
        expectedModel.editAppointmentsWithPerson(model.getFilteredDoctorList().get(0), editedDoctor);

        assertCommandSuccess(editDoctorCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_duplicateDoctorUnfilteredList_failure() {
        Doctor firstDoctor = model.getFilteredDoctorList().get(INDEX_FIRST_PERSON.getZeroBased());
        EditDoctorCommand.EditDoctorDescriptor descriptor = new EditDoctorDescriptorBuilder(firstDoctor).build();
        EditDoctorCommand editDoctorCommand = new EditDoctorCommand(INDEX_SECOND_PERSON, descriptor);

        assertCommandFailure(editDoctorCommand, model, EditDoctorCommand.MESSAGE_DUPLICATE_DOCTOR);
    }

    @Test
    public void execute_duplicateDoctorFilteredList_failure() {
        showDoctorAtIndex(model, INDEX_FIRST_PERSON);

        // edit doctor in filtered list into a duplicate in plannermd
        Doctor doctorInList = model.getPlannerMd().getDoctorList().get(INDEX_SECOND_PERSON.getZeroBased());
        EditDoctorCommand editDoctorCommand = new EditDoctorCommand(INDEX_FIRST_PERSON,
                new EditDoctorDescriptorBuilder(doctorInList).build());

        assertCommandFailure(editDoctorCommand, model, EditDoctorCommand.MESSAGE_DUPLICATE_DOCTOR);
    }

    @Test
    public void execute_invalidDoctorIndexUnfilteredList_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredDoctorList().size() + 1);
        EditDoctorCommand.EditDoctorDescriptor descriptor =
                new EditDoctorDescriptorBuilder().withName(VALID_NAME_BOB).build();
        EditDoctorCommand editDoctorCommand = new EditDoctorCommand(outOfBoundIndex, descriptor);

        assertCommandFailure(editDoctorCommand, model, Messages.MESSAGE_INVALID_DOCTOR_DISPLAYED_INDEX);
    }

    /**
     * Edit filtered list where index is larger than size of filtered list,
     * but smaller than size of plannermd.
     */
    @Test
    public void execute_invalidDoctorIndexFilteredList_failure() {
        showDoctorAtIndex(model, INDEX_FIRST_PERSON);
        Index outOfBoundIndex = INDEX_SECOND_PERSON;
        // ensures that outOfBoundIndex is still in bounds of plannermd list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getPlannerMd().getDoctorList().size());

        EditDoctorCommand editDoctorCommand = new EditDoctorCommand(outOfBoundIndex,
                new EditDoctorDescriptorBuilder().withName(VALID_NAME_BOB).build());

        assertCommandFailure(editDoctorCommand, model, Messages.MESSAGE_INVALID_DOCTOR_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        final EditDoctorCommand standardCommand = new EditDoctorCommand(INDEX_FIRST_PERSON, DESC_DR_AMY);

        // same values -> returns true
        EditDoctorCommand.EditDoctorDescriptor copyDescriptor = new EditDoctorCommand.EditDoctorDescriptor(DESC_DR_AMY);
        EditDoctorCommand commandWithSameValues = new EditDoctorCommand(INDEX_FIRST_PERSON, copyDescriptor);
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearCommand()));

        // different index -> returns false
        assertFalse(standardCommand.equals(new EditDoctorCommand(INDEX_SECOND_PERSON, DESC_DR_AMY)));

        // different descriptor -> returns false
        assertFalse(standardCommand.equals(new EditDoctorCommand(INDEX_FIRST_PERSON, DESC_DR_BOB)));

        // EditDoctorCommand vs EditPatientCommand -> returns false
        EditPatientCommand editPatientCommand = new EditPatientCommand(INDEX_FIRST_PERSON, DESC_AMY);
        assertFalse(standardCommand.equals(editPatientCommand));
    }
}
