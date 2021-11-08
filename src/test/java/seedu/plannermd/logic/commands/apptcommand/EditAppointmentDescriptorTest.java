package seedu.plannermd.logic.commands.apptcommand;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.plannermd.logic.commands.CommandTestUtil.ANOTHER_VALID_APPT_REMARK;
import static seedu.plannermd.logic.commands.CommandTestUtil.ANOTHER_VALID_DOCTOR_INDEX;
import static seedu.plannermd.logic.commands.CommandTestUtil.ANOTHER_VALID_PATIENT_INDEX;
import static seedu.plannermd.logic.commands.CommandTestUtil.DESC_EDIT_THIRTY_MIN_APPT;
import static seedu.plannermd.logic.commands.CommandTestUtil.DESC_EDIT_TWO_HOUR_APPT;
import static seedu.plannermd.logic.commands.CommandTestUtil.VALID_APPT_DATE_TWO_HOUR;
import static seedu.plannermd.logic.commands.CommandTestUtil.VALID_APPT_DURATION_TWO_HOUR_STR;
import static seedu.plannermd.logic.commands.CommandTestUtil.VALID_APPT_TIME_TWO_HOUR;

import org.junit.jupiter.api.Test;

import seedu.plannermd.testutil.appointment.EditAppointmentDescriptorBuilder;

public class EditAppointmentDescriptorTest {

    @Test
    public void equals() {
        // same values -> returns true
        EditAppointmentCommand.EditAppointmentDescriptor descriptorWithSameValues =
                new EditAppointmentCommand.EditAppointmentDescriptor(DESC_EDIT_THIRTY_MIN_APPT);
        assertTrue(DESC_EDIT_THIRTY_MIN_APPT.equals(descriptorWithSameValues));

        // same object -> returns true
        assertTrue(DESC_EDIT_THIRTY_MIN_APPT.equals(DESC_EDIT_THIRTY_MIN_APPT));

        // null -> returns false
        assertFalse(DESC_EDIT_THIRTY_MIN_APPT.equals(null));

        // different types -> returns false
        assertFalse(DESC_EDIT_THIRTY_MIN_APPT.equals(5));

        // different values -> returns false
        assertFalse(DESC_EDIT_THIRTY_MIN_APPT.equals(DESC_EDIT_TWO_HOUR_APPT));

        // different patient index -> returns false
        EditAppointmentCommand.EditAppointmentDescriptor editedThirtyMinAppt =
                new EditAppointmentDescriptorBuilder(DESC_EDIT_THIRTY_MIN_APPT)
                        .withPatientIndex(ANOTHER_VALID_PATIENT_INDEX).build();
        assertFalse(DESC_EDIT_THIRTY_MIN_APPT.equals(editedThirtyMinAppt));

        // different doctor index -> returns false
        editedThirtyMinAppt = new EditAppointmentDescriptorBuilder(DESC_EDIT_THIRTY_MIN_APPT)
                .withDoctorIndex(ANOTHER_VALID_DOCTOR_INDEX).build();
        assertFalse(DESC_EDIT_THIRTY_MIN_APPT.equals(editedThirtyMinAppt));

        // different appointment date -> returns false
        editedThirtyMinAppt = new EditAppointmentDescriptorBuilder(DESC_EDIT_THIRTY_MIN_APPT)
                .withAppointmentDate(VALID_APPT_DATE_TWO_HOUR).build();
        assertFalse(DESC_EDIT_THIRTY_MIN_APPT.equals(editedThirtyMinAppt));

        // different start time -> returns false
        editedThirtyMinAppt = new EditAppointmentDescriptorBuilder(DESC_EDIT_THIRTY_MIN_APPT)
                .withStartTime(VALID_APPT_TIME_TWO_HOUR).build();
        assertFalse(DESC_EDIT_THIRTY_MIN_APPT.equals(editedThirtyMinAppt));

        // different duration -> returns false
        editedThirtyMinAppt = new EditAppointmentDescriptorBuilder(DESC_EDIT_THIRTY_MIN_APPT)
                .withDuration(VALID_APPT_DURATION_TWO_HOUR_STR).build();
        assertFalse(DESC_EDIT_THIRTY_MIN_APPT.equals(editedThirtyMinAppt));

        // different remark -> returns false
        editedThirtyMinAppt = new EditAppointmentDescriptorBuilder(DESC_EDIT_THIRTY_MIN_APPT)
                .withRemark(ANOTHER_VALID_APPT_REMARK).build();
        assertFalse(DESC_EDIT_THIRTY_MIN_APPT.equals(editedThirtyMinAppt));
    }
}
