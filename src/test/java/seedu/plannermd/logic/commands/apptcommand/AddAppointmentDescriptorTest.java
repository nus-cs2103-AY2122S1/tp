package seedu.plannermd.logic.commands.apptcommand;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.plannermd.logic.commands.CommandTestUtil.DESC_THIRTY_MIN_APPT;
import static seedu.plannermd.logic.commands.CommandTestUtil.DESC_TWO_HOUR_APPT;
import static seedu.plannermd.logic.commands.CommandTestUtil.REMARK_DESC_AMY;
import static seedu.plannermd.logic.commands.CommandTestUtil.VALID_APPT_DATE_TWO_HOUR;
import static seedu.plannermd.logic.commands.CommandTestUtil.VALID_APPT_DURATION_TWO_HOUR;
import static seedu.plannermd.logic.commands.CommandTestUtil.VALID_APPT_TIME_TWO_HOUR;

import org.junit.jupiter.api.Test;

import seedu.plannermd.testutil.appointment.AddAppointmentDescriptorBuilder;

public class AddAppointmentDescriptorTest {

    @Test
    public void equals() {
        // same values -> returns true
        AddAppointmentCommand.AddAppointmentDescriptor descriptorWithSameValues =
                new AddAppointmentCommand.AddAppointmentDescriptor(DESC_THIRTY_MIN_APPT);
        assertTrue(DESC_THIRTY_MIN_APPT.equals(descriptorWithSameValues));

        // same object -> returns true
        assertTrue(DESC_THIRTY_MIN_APPT.equals(DESC_THIRTY_MIN_APPT));

        // null -> returns false
        assertFalse(DESC_THIRTY_MIN_APPT.equals(null));

        // different types -> returns false
        assertFalse(DESC_THIRTY_MIN_APPT.equals(5));

        // different values -> returns false
        assertFalse(DESC_THIRTY_MIN_APPT.equals(DESC_TWO_HOUR_APPT));

        // different appointment date -> returns false
        AddAppointmentCommand.AddAppointmentDescriptor editedThirtyMinAppt =
                new AddAppointmentDescriptorBuilder(DESC_THIRTY_MIN_APPT)
                        .withAppointmentDate(VALID_APPT_DATE_TWO_HOUR).build();
        assertFalse(DESC_THIRTY_MIN_APPT.equals(editedThirtyMinAppt));

        // different session -> returns false
        editedThirtyMinAppt = new AddAppointmentDescriptorBuilder(DESC_THIRTY_MIN_APPT)
                .withSession(VALID_APPT_TIME_TWO_HOUR, VALID_APPT_DURATION_TWO_HOUR).build();
        assertFalse(DESC_THIRTY_MIN_APPT.equals(editedThirtyMinAppt));

        // different remark -> returns true
        editedThirtyMinAppt = new AddAppointmentDescriptorBuilder(DESC_THIRTY_MIN_APPT)
                .withRemark(REMARK_DESC_AMY).build();
        assertTrue(DESC_THIRTY_MIN_APPT.equals(editedThirtyMinAppt));
    }
}
