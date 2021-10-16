package seedu.fast.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.fast.logic.commands.CommandTestUtil.APPT_DES_AMY;
import static seedu.fast.logic.commands.CommandTestUtil.APPT_DES_BOB;
import static seedu.fast.logic.commands.CommandTestUtil.VALID_APPOINTMENT_BOB;
import static seedu.fast.logic.commands.CommandTestUtil.VALID_APPOINTMENT_TIME_BOB;
import static seedu.fast.logic.commands.CommandTestUtil.VALID_APPOINTMENT_VENUE_BOB;

import org.junit.jupiter.api.Test;

import seedu.fast.logic.commands.EditAppointmentCommand.EditAppointmentDescriptor;
import seedu.fast.testutil.EditAppointmentDescriptorBuilder;

public class EditAppointmentDescriptorTest {
    @Test
    public void equals() {
        // same values -> returns true
        EditAppointmentDescriptor descriptorWithSameValues = new EditAppointmentDescriptor(APPT_DES_AMY);
        assertTrue(APPT_DES_AMY.equals(descriptorWithSameValues));

        // same object -> returns true
        assertTrue(descriptorWithSameValues.equals(descriptorWithSameValues));

        // null -> returns false
        assertFalse(descriptorWithSameValues.equals(null));

        // different types -> returns false
        assertFalse(descriptorWithSameValues.equals("Matthew"));

        // different values -> returns false
        assertFalse(descriptorWithSameValues.equals(APPT_DES_BOB));

        // different date -> returns false
        EditAppointmentDescriptor editedAppointmentAmy = new EditAppointmentDescriptorBuilder(APPT_DES_AMY)
                .withDate(VALID_APPOINTMENT_BOB).build();
        assertFalse(APPT_DES_AMY.equals(editedAppointmentAmy));

        // different time -> returns false
        editedAppointmentAmy = new EditAppointmentDescriptorBuilder(APPT_DES_AMY)
                .withTime(VALID_APPOINTMENT_TIME_BOB).build();
        assertFalse(APPT_DES_AMY.equals(editedAppointmentAmy));

        // different venue -> returns false
        editedAppointmentAmy = new EditAppointmentDescriptorBuilder(APPT_DES_AMY)
                .withTime(VALID_APPOINTMENT_VENUE_BOB).build();
        assertFalse(APPT_DES_AMY.equals(editedAppointmentAmy));

        // no parameters -> returns false
        editedAppointmentAmy = new EditAppointmentDescriptorBuilder().build();
        assertFalse(APPT_DES_AMY.equals(editedAppointmentAmy));
    }
}
