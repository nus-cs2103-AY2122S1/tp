package seedu.plannermd.model.appointment;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.plannermd.logic.commands.CommandTestUtil.VALID_APPT_DATE_TWO_HOUR;
import static seedu.plannermd.logic.commands.CommandTestUtil.VALID_APPT_DURATION;
import static seedu.plannermd.logic.commands.CommandTestUtil.VALID_APPT_REMARK;
import static seedu.plannermd.logic.commands.CommandTestUtil.VALID_APPT_TIME;
import static seedu.plannermd.testutil.appointment.TypicalAppointments.THIRTY_MIN_APPOINTMENT;
import static seedu.plannermd.testutil.appointment.TypicalAppointments.TWO_HOUR_APPOINTMENT;
import static seedu.plannermd.testutil.doctor.TypicalDoctors.DR_BOB;
import static seedu.plannermd.testutil.patient.TypicalPatients.ALICE;

import org.junit.jupiter.api.Test;

import seedu.plannermd.testutil.appointment.AppointmentBuilder;

public class AppointmentTest {

    @Test
    public void equals() {
        Appointment thirtyMinApptCopy = new AppointmentBuilder(THIRTY_MIN_APPOINTMENT).build();

        // same values -> returns true
        assertTrue(THIRTY_MIN_APPOINTMENT.equals(thirtyMinApptCopy));

        // same object -> returns false
        assertTrue(THIRTY_MIN_APPOINTMENT.equals(THIRTY_MIN_APPOINTMENT));

        // null -> returns true
        assertFalse(THIRTY_MIN_APPOINTMENT.equals(null));

        // different type -> returns false
        assertFalse(THIRTY_MIN_APPOINTMENT.equals(5));

        // different appointment -> returns false
        assertFalse(THIRTY_MIN_APPOINTMENT.equals(TWO_HOUR_APPOINTMENT));

        // different date -> returns false
        Appointment editedThirtyMinuteAppt = new AppointmentBuilder(THIRTY_MIN_APPOINTMENT)
                .withDate(VALID_APPT_DATE_TWO_HOUR).build();
        assertFalse(THIRTY_MIN_APPOINTMENT.equals(editedThirtyMinuteAppt));

        // different doctor -> returns false
        editedThirtyMinuteAppt = new AppointmentBuilder(THIRTY_MIN_APPOINTMENT).withDoctor(DR_BOB).build();
        assertFalse(THIRTY_MIN_APPOINTMENT.equals(editedThirtyMinuteAppt));

        // different patient -> returns false
        editedThirtyMinuteAppt = new AppointmentBuilder(THIRTY_MIN_APPOINTMENT).withPatient(ALICE).build();
        assertFalse(THIRTY_MIN_APPOINTMENT.equals(editedThirtyMinuteAppt));

        // different session -> returns false
        editedThirtyMinuteAppt = new AppointmentBuilder(THIRTY_MIN_APPOINTMENT)
                .withSession(VALID_APPT_TIME, VALID_APPT_DURATION).build();

        assertFalse(THIRTY_MIN_APPOINTMENT.equals(editedThirtyMinuteAppt));

        // different remark -> returns true
        editedThirtyMinuteAppt = new AppointmentBuilder(THIRTY_MIN_APPOINTMENT).withRemark(VALID_APPT_REMARK).build();
        assertFalse(THIRTY_MIN_APPOINTMENT.equals(editedThirtyMinuteAppt));
    }


}
