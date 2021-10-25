package seedu.plannermd.testutil.appointment;

import static seedu.plannermd.testutil.doctor.TypicalDoctors.DR_ALICE;
import static seedu.plannermd.testutil.doctor.TypicalDoctors.DR_BENSON;
import static seedu.plannermd.testutil.doctor.TypicalDoctors.DR_CARL;
import static seedu.plannermd.testutil.doctor.TypicalDoctors.DR_ELLE;
import static seedu.plannermd.testutil.doctor.TypicalDoctors.DR_FIONA;
import static seedu.plannermd.testutil.doctor.TypicalDoctors.DR_GEORGE;
import static seedu.plannermd.testutil.patient.TypicalPatients.ALICE;
import static seedu.plannermd.testutil.patient.TypicalPatients.BENSON;
import static seedu.plannermd.testutil.patient.TypicalPatients.CARL;
import static seedu.plannermd.testutil.patient.TypicalPatients.ELLE;
import static seedu.plannermd.testutil.patient.TypicalPatients.FIONA;
import static seedu.plannermd.testutil.patient.TypicalPatients.GEORGE;

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
    public static final Appointment ANOTHER_TWO_HOUR_APPOINTMENT = new AppointmentBuilder()
            .withPatient(GEORGE).withDoctor(DR_ALICE).withDate("6/6/2023")
            .withSession("20:00", 120)
            .withRemark("Therapy Session").build();
    public static final Appointment ANOTHER_FIVE_MIN_APPOINTMENT = new AppointmentBuilder()
            .withPatient(FIONA).withDoctor(DR_BENSON).withDate("8/8/2023")
            .withSession("12:05", 5)
            .withRemark("Head Trauma").build();
    public static final Appointment ANOTHER_THIRTY_MIN_APPOINTMENT = new AppointmentBuilder()
            .withPatient(ELLE).withDoctor(DR_CARL).withDate("10/10/2023")
            .withSession("09:30", 30)
            .withRemark("Sore throat").build();

    private TypicalAppointments() {
    } // prevents instantiation

    public static List<Appointment> getTypicalAppointments() {
        return new ArrayList<>(Arrays.asList(TWO_HOUR_APPOINTMENT, FIVE_MIN_APPOINTMENT, THIRTY_MIN_APPOINTMENT,
                ANOTHER_TWO_HOUR_APPOINTMENT, ANOTHER_FIVE_MIN_APPOINTMENT, ANOTHER_THIRTY_MIN_APPOINTMENT));
    }
}
