package seedu.plannermd.logic.commands.apptcommand;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.plannermd.logic.commands.CommandTestUtil.DESC_AMY;
import static seedu.plannermd.logic.commands.CommandTestUtil.DESC_EDIT_THIRTY_MIN_APPT;
import static seedu.plannermd.logic.commands.CommandTestUtil.DESC_EDIT_TWO_HOUR_APPT;
import static seedu.plannermd.logic.commands.CommandTestUtil.VALID_APPT_DATE_THIRTY_MIN;
import static seedu.plannermd.logic.commands.CommandTestUtil.VALID_APPT_DURATION_THIRTY_MIN_STR;
import static seedu.plannermd.logic.commands.CommandTestUtil.VALID_APPT_DURATION_TWO_HOUR;
import static seedu.plannermd.logic.commands.CommandTestUtil.VALID_APPT_REMARK;
import static seedu.plannermd.logic.commands.CommandTestUtil.VALID_APPT_TIME_THIRTY_MIN;
import static seedu.plannermd.logic.commands.CommandTestUtil.VALID_APPT_TIME_TWO_HOUR;
import static seedu.plannermd.logic.commands.CommandTestUtil.VALID_DOCTOR_INDEX;
import static seedu.plannermd.logic.commands.CommandTestUtil.VALID_PATIENT_INDEX;
import static seedu.plannermd.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.plannermd.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.plannermd.testutil.TypicalIndexes.INDEX_FIRST_APPT;
import static seedu.plannermd.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.plannermd.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
import static seedu.plannermd.testutil.TypicalPlannerMd.getTypicalPlannerMd;
import static seedu.plannermd.testutil.doctor.TypicalDoctors.DR_GEORGE;
import static seedu.plannermd.testutil.patient.TypicalPatients.ALICE;

import org.junit.jupiter.api.Test;

import seedu.plannermd.commons.core.Messages;
import seedu.plannermd.commons.core.index.Index;
import seedu.plannermd.logic.commands.editcommand.EditPatientCommand;
import seedu.plannermd.model.Model;
import seedu.plannermd.model.ModelManager;
import seedu.plannermd.model.PlannerMd;
import seedu.plannermd.model.UserPrefs;
import seedu.plannermd.model.appointment.Appointment;
import seedu.plannermd.model.appointment.Session;
import seedu.plannermd.testutil.appointment.AppointmentBuilder;
import seedu.plannermd.testutil.appointment.EditAppointmentDescriptorBuilder;

/**
 * Contains integration tests (interaction with the Model) and unit tests for EditAppointmentCommand.
 */
public class EditAppointmentCommandTest {

    private static final Index ALICE_PATIENT_INDEX = Index.fromOneBased(1);
    private static final Index DR_GEORGE_DOCTOR_INDEX = Index.fromOneBased(7);
    private static final String EDITED_DATE = "1/1/2030";

    private final Model model = new ModelManager(getTypicalPlannerMd(), new UserPrefs());

    @Test
    public void execute_allFieldsSpecifiedUnfilteredList_success() {
        AppointmentBuilder editedAppointmentBuilder =
                // patient and doctor are same as those for first appt in TypicalAppointments
                new AppointmentBuilder().withPatient(ALICE).withDoctor(DR_GEORGE).withDate(EDITED_DATE)
                        .withSession(VALID_APPT_TIME_TWO_HOUR, VALID_APPT_DURATION_TWO_HOUR)
                        .withRemark(VALID_APPT_REMARK);
        Appointment editedAppointment = editedAppointmentBuilder.build();

        EditAppointmentCommand.EditAppointmentDescriptor descriptor =
                new EditAppointmentDescriptorBuilder(ALICE_PATIENT_INDEX, DR_GEORGE_DOCTOR_INDEX, editedAppointment)
                        .build();
        EditAppointmentCommand editAppointmentCommand = new EditAppointmentCommand(INDEX_FIRST_APPT, descriptor);

        String expectedMessage =
                String.format(EditAppointmentCommand.MESSAGE_EDIT_APPOINTMENT_SUCCESS, editedAppointment);

        Model expectedModel = new ModelManager(new PlannerMd(model.getPlannerMd()), new UserPrefs());
        expectedModel.setAppointment(model.getFilteredAppointmentList().get(0), editedAppointment);

        assertCommandSuccess(editAppointmentCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_someFieldsSpecifiedUnfilteredList_success() {
        AppointmentBuilder editedAppointmentBuilder =
                // patient and doctor are same as those for first appt in TypicalAppointments
                new AppointmentBuilder().withPatient(ALICE).withDoctor(DR_GEORGE).withDate(EDITED_DATE);
        Appointment editedAppointment = editedAppointmentBuilder.build();

        EditAppointmentCommand.EditAppointmentDescriptor descriptor =
                new EditAppointmentDescriptorBuilder(ALICE_PATIENT_INDEX, DR_GEORGE_DOCTOR_INDEX, editedAppointment)
                        .build();
        EditAppointmentCommand editAppointmentCommand = new EditAppointmentCommand(INDEX_FIRST_APPT, descriptor);

        String expectedMessage =
                String.format(EditAppointmentCommand.MESSAGE_EDIT_APPOINTMENT_SUCCESS, editedAppointment);

        Model expectedModel = new ModelManager(new PlannerMd(model.getPlannerMd()), new UserPrefs());
        expectedModel.setAppointment(model.getFilteredAppointmentList().get(0), editedAppointment);

        assertCommandSuccess(editAppointmentCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_noFieldSpecifiedUnfilteredList_success() {
        AppointmentBuilder editedAppointmentBuilder =
                // patient and doctor are same as those for first appt in TypicalAppointments
                new AppointmentBuilder().withPatient(ALICE).withDoctor(DR_GEORGE);
        Appointment editedAppointment = editedAppointmentBuilder.build();

        EditAppointmentCommand.EditAppointmentDescriptor descriptor =
                new EditAppointmentDescriptorBuilder(ALICE_PATIENT_INDEX, DR_GEORGE_DOCTOR_INDEX, editedAppointment)
                        .build();
        EditAppointmentCommand editAppointmentCommand = new EditAppointmentCommand(INDEX_FIRST_APPT, descriptor);

        String expectedMessage =
                String.format(EditAppointmentCommand.MESSAGE_EDIT_APPOINTMENT_SUCCESS, editedAppointment);

        Model expectedModel = new ModelManager(new PlannerMd(model.getPlannerMd()), new UserPrefs());
        expectedModel.setAppointment(model.getFilteredAppointmentList().get(0), editedAppointment);

        assertCommandSuccess(editAppointmentCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_duplicateAppointmentUnfilteredList_failure() {
        Appointment firstAppointment = model.getFilteredAppointmentList().get(INDEX_FIRST_APPT.getZeroBased());
        EditAppointmentCommand.EditAppointmentDescriptor descriptor =
                new EditAppointmentDescriptorBuilder(ALICE_PATIENT_INDEX, DR_GEORGE_DOCTOR_INDEX, firstAppointment)
                        .build();
        EditAppointmentCommand editAppointmentCommand = new EditAppointmentCommand(INDEX_SECOND_PERSON, descriptor);

        assertCommandFailure(editAppointmentCommand, model, EditAppointmentCommand.MESSAGE_CLASHING_APPOINTMENT);
    }

    @Test
    public void execute_invalidAppointmentIndexUnfilteredList_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredAppointmentList().size() + 1);
        EditAppointmentCommand.EditAppointmentDescriptor descriptor =
                new EditAppointmentDescriptorBuilder().withRemark(VALID_APPT_REMARK).build();
        EditAppointmentCommand editAppointmentCommand = new EditAppointmentCommand(outOfBoundIndex, descriptor);

        assertCommandFailure(editAppointmentCommand, model, Messages.MESSAGE_INVALID_APPOINTMENT_DISPLAYED_INDEX);
    }

    @Test
    public void execute_sessionSpansTwoDaysUnfilteredList_failure() {
        AppointmentBuilder editedAppointmentBuilder =
                // patient and doctor are same as those for first appt in TypicalAppointments
                new AppointmentBuilder().withPatient(ALICE).withDoctor(DR_GEORGE).withDate(EDITED_DATE)
                        .withSession("23:30", VALID_APPT_DURATION_TWO_HOUR)
                        .withRemark(VALID_APPT_REMARK);
        Appointment editedAppointment = editedAppointmentBuilder.build();

        EditAppointmentCommand.EditAppointmentDescriptor descriptor =
                new EditAppointmentDescriptorBuilder(ALICE_PATIENT_INDEX, DR_GEORGE_DOCTOR_INDEX, editedAppointment)
                        .build();
        EditAppointmentCommand editAppointmentCommand = new EditAppointmentCommand(INDEX_FIRST_APPT, descriptor);

        assertCommandFailure(editAppointmentCommand, model, Session.MESSAGE_END_WITHIN_SAME_DAY);
    }

    @Test
    public void equals() {
        final EditAppointmentCommand standardCommand =
                new EditAppointmentCommand(INDEX_FIRST_PERSON, DESC_EDIT_THIRTY_MIN_APPT);

        // same values -> returns true
        EditAppointmentCommand.EditAppointmentDescriptor copyDescriptor =
                new EditAppointmentDescriptorBuilder().withPatientIndex(VALID_PATIENT_INDEX)
                        .withDoctorIndex(VALID_DOCTOR_INDEX).withAppointmentDate(VALID_APPT_DATE_THIRTY_MIN)
                        .withStartTime(VALID_APPT_TIME_THIRTY_MIN).withDuration(VALID_APPT_DURATION_THIRTY_MIN_STR)
                        .withRemark(VALID_APPT_REMARK).build();
        EditAppointmentCommand commandWithSameValues = new EditAppointmentCommand(INDEX_FIRST_PERSON, copyDescriptor);
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new EditPatientCommand(INDEX_FIRST_PERSON, DESC_AMY)));

        // different index -> returns false
        assertFalse(standardCommand.equals(new EditAppointmentCommand(INDEX_SECOND_PERSON, DESC_EDIT_THIRTY_MIN_APPT)));

        // different descriptor -> returns false
        assertFalse(standardCommand.equals(new EditAppointmentCommand(INDEX_FIRST_PERSON, DESC_EDIT_TWO_HOUR_APPT)));
    }
}
