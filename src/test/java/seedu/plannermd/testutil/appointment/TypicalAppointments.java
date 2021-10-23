package seedu.plannermd.testutil.appointment;

import static seedu.plannermd.logic.commands.CommandTestUtil.VALID_APPT_DATE_THIRTY_MIN;
import static seedu.plannermd.logic.commands.CommandTestUtil.VALID_APPT_DURATION_THIRTY_MIN;
import static seedu.plannermd.logic.commands.CommandTestUtil.VALID_APPT_TIME_THIRTY_MIN;
import static seedu.plannermd.testutil.doctor.TypicalDoctors.DR_AMY;
import static seedu.plannermd.testutil.doctor.TypicalDoctors.DR_BENSON;
import static seedu.plannermd.testutil.doctor.TypicalDoctors.DR_CARL;
import static seedu.plannermd.testutil.patient.TypicalPatients.ALICE;
import static seedu.plannermd.testutil.patient.TypicalPatients.ELLE;
import static seedu.plannermd.testutil.patient.TypicalPatients.FIONA;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.plannermd.model.appointment.Appointment;

public class TypicalAppointments {
    public static final Appointment TWO_HOUR_APPOINTMENT = new AppointmentBuilder().withPatient(ELLE)
            .withDoctor(DR_AMY).withDate(VALID_APPT_DATE_THIRTY_MIN).withDate("12/8/2022").build();
    public static final Appointment FIVE_MIN_APPOINTMENT = new AppointmentBuilder().withPatient(FIONA)
            .withDoctor(DR_CARL).withDate(VALID_APPT_DATE_THIRTY_MIN).withDate("19/12/2021").build();
    public static final Appointment THIRTY_MIN_APPOINTMENT = new AppointmentBuilder().withPatient(ALICE)
            .withDoctor(DR_BENSON).withDate(VALID_APPT_DATE_THIRTY_MIN)
            .withSession(VALID_APPT_TIME_THIRTY_MIN, VALID_APPT_DURATION_THIRTY_MIN).build();

    private TypicalAppointments() {
    } // prevents instantiation
    public static List<Appointment> getTypicalAppointments() {
        return new ArrayList<>(Arrays.asList(TWO_HOUR_APPOINTMENT, FIVE_MIN_APPOINTMENT, THIRTY_MIN_APPOINTMENT));
    }
}
