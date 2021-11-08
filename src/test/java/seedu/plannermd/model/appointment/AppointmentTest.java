package seedu.plannermd.model.appointment;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.plannermd.logic.commands.CommandTestUtil.VALID_APPT_DATE_TWO_HOUR;
import static seedu.plannermd.logic.commands.CommandTestUtil.VALID_APPT_DURATION;
import static seedu.plannermd.logic.commands.CommandTestUtil.VALID_APPT_REMARK;
import static seedu.plannermd.logic.commands.CommandTestUtil.VALID_APPT_TIME;
import static seedu.plannermd.testutil.appointment.TypicalAppointments.THIRTY_MIN_APPOINTMENT;
import static seedu.plannermd.testutil.appointment.TypicalAppointments.TWO_HOUR_APPOINTMENT;
import static seedu.plannermd.testutil.doctor.TypicalDoctors.DR_BOB;
import static seedu.plannermd.testutil.doctor.TypicalDoctors.DR_FIONA;
import static seedu.plannermd.testutil.doctor.TypicalDoctors.DR_GEORGE;
import static seedu.plannermd.testutil.patient.TypicalPatients.ALICE;
import static seedu.plannermd.testutil.patient.TypicalPatients.BENSON;
import static seedu.plannermd.testutil.patient.TypicalPatients.BOB;

import org.junit.jupiter.api.Test;

import seedu.plannermd.testutil.appointment.AppointmentBuilder;

public class AppointmentTest {

    private static final Appointment APPT_ALICE_GEORGE = new AppointmentBuilder()
            .withPatient(ALICE).withDoctor(DR_GEORGE).withDate("6/6/2022")
            .withSession("18:00", 120)
            .withRemark("Therapy Session").build();

    @Test
    public void isClash() {
        // same patient, same sessions -> true
        Appointment appt1 = new AppointmentBuilder(APPT_ALICE_GEORGE).withDoctor(DR_FIONA).build();
        assertTrue(APPT_ALICE_GEORGE.isClash(appt1));

        // same patient, clashing sessions -> true
        Appointment appt2 = new AppointmentBuilder(APPT_ALICE_GEORGE).withDoctor(DR_FIONA)
                .withSession("17:30", 60).build();
        assertTrue(APPT_ALICE_GEORGE.isClash(appt2));

        // same doctor, same sessions -> true
        Appointment appt3 = new AppointmentBuilder(APPT_ALICE_GEORGE).withPatient(BENSON).build();
        assertTrue(APPT_ALICE_GEORGE.isClash(appt3));

        // same doctor, clashing sessions -> true
        Appointment appt4 = new AppointmentBuilder(APPT_ALICE_GEORGE).withPatient(BENSON)
                .withSession("17:30", 60).build();
        assertTrue(APPT_ALICE_GEORGE.isClash(appt4));

        // different patient and doctor -> false
        Appointment appt5 = new AppointmentBuilder(APPT_ALICE_GEORGE).withPatient(BENSON).withDoctor(DR_FIONA).build();
        assertFalse(APPT_ALICE_GEORGE.isClash(appt5));

        // different date -> false
        Appointment appt6 = new AppointmentBuilder(APPT_ALICE_GEORGE).withDate("7/7/2022").build();
        assertFalse(APPT_ALICE_GEORGE.isClash(appt6));

        // different session -> false
        Appointment appt7 = new AppointmentBuilder(APPT_ALICE_GEORGE).withSession("09:00", 10).build();
        assertFalse(APPT_ALICE_GEORGE.isClash(appt7));
    }

    @Test
    public void isSameAppointment() {
        // same patient, doctor, date, session -> true
        Appointment copyWithDifferentRemark = new AppointmentBuilder(APPT_ALICE_GEORGE).withRemark("").build();
        assertTrue(APPT_ALICE_GEORGE.isSameAppointment(copyWithDifferentRemark));

        // different patient -> false
        Appointment differentPatient = new AppointmentBuilder(APPT_ALICE_GEORGE).withPatient(BENSON).build();
        assertFalse(APPT_ALICE_GEORGE.isSameAppointment(differentPatient));

        // different doctor -> false
        Appointment differentDoctor = new AppointmentBuilder(APPT_ALICE_GEORGE).withDoctor(DR_FIONA).build();
        assertFalse(APPT_ALICE_GEORGE.isSameAppointment(differentDoctor));

        // different date -> false
        Appointment differentDate = new AppointmentBuilder(APPT_ALICE_GEORGE).withDate("7/7/2022").build();
        assertFalse(APPT_ALICE_GEORGE.isSameAppointment(differentDate));

        // different session -> false
        Appointment differentSession = new AppointmentBuilder(APPT_ALICE_GEORGE).withSession("09:00", 10).build();
        assertFalse(APPT_ALICE_GEORGE.isSameAppointment(differentSession));
    }

    @Test
    public void compareTo() {
        // same date, earlier start time -> < 0
        Appointment lateStartTime = new AppointmentBuilder(APPT_ALICE_GEORGE).withSession("20:00", 10).build();
        assertTrue(APPT_ALICE_GEORGE.compareTo(lateStartTime) < 0);

        // same date, same start time -> == 0
        Appointment sameStartTime = new AppointmentBuilder(APPT_ALICE_GEORGE).build();
        assertEquals(0, APPT_ALICE_GEORGE.compareTo(sameStartTime));

        // same date, later start time -> > 0
        Appointment earlyStartTime = new AppointmentBuilder(APPT_ALICE_GEORGE).withSession("09:00", 10).build();
        assertTrue(APPT_ALICE_GEORGE.compareTo(earlyStartTime) > 0);

        // earlier date -> < 0
        Appointment lateDate = new AppointmentBuilder(APPT_ALICE_GEORGE).withDate("7/7/2022").build();
        assertTrue(APPT_ALICE_GEORGE.compareTo(lateDate) < 0);

        // later date -> > 0
        Appointment earlyDate = new AppointmentBuilder(APPT_ALICE_GEORGE).withDate("1/1/2022").build();
        assertTrue(APPT_ALICE_GEORGE.compareTo(earlyDate) > 0);
    }

    @Test
    public void equals() {
        Appointment thirtyMinApptCopy = new AppointmentBuilder(THIRTY_MIN_APPOINTMENT).build();

        // same values -> returns true
        assertTrue(THIRTY_MIN_APPOINTMENT.equals(thirtyMinApptCopy));

        // same object -> returns true
        assertTrue(THIRTY_MIN_APPOINTMENT.equals(THIRTY_MIN_APPOINTMENT));

        // null -> returns false
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
        editedThirtyMinuteAppt = new AppointmentBuilder(THIRTY_MIN_APPOINTMENT).withPatient(BOB).build();
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
