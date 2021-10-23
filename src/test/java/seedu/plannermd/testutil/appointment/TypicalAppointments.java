package seedu.plannermd.testutil.appointment;

import static seedu.plannermd.testutil.doctor.TypicalDoctors.DR_ELLE;
import static seedu.plannermd.testutil.doctor.TypicalDoctors.DR_FIONA;
import static seedu.plannermd.testutil.doctor.TypicalDoctors.DR_GEORGE;
import static seedu.plannermd.testutil.patient.TypicalPatients.ALICE;
import static seedu.plannermd.testutil.patient.TypicalPatients.BENSON;
import static seedu.plannermd.testutil.patient.TypicalPatients.CARL;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.plannermd.model.appointment.Appointment;

public class TypicalAppointments {
    public static final Appointment TWO_HOUR_APPOINTMENT = new AppointmentBuilder()
            .withPatient(ALICE).withDoctor(DR_GEORGE).withDate("6/6/2022")
            .withSession("18:00", 120)
            .withRemark("Therapy Session").build();
    public static final Appointment FIVE_MIN_APPOINTMENT = new AppointmentBuilder()
            .withPatient(BENSON).withDoctor(DR_FIONA).withDate("8/8/2022")
            .withSession("12:00", 5)
            .withRemark("Blocked Nose").build();
    public static final Appointment THIRTY_MIN_APPOINTMENT = new AppointmentBuilder()
            .withPatient(CARL).withDoctor(DR_ELLE).withDate("10/10/2022")
            .withSession("09:00", 30)
            .withRemark("High Fever and Cough").build();

    private TypicalAppointments() {
    } // prevents instantiation

    public static List<Appointment> getTypicalAppointments() {
        return new ArrayList<>(Arrays.asList(TWO_HOUR_APPOINTMENT, FIVE_MIN_APPOINTMENT, THIRTY_MIN_APPOINTMENT));
    }
}
