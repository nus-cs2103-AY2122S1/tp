package seedu.plannermd.testutil.appointment;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.plannermd.model.appointment.Appointment;

public class TypicalAppointments {
    private static final Appointment TWO_HOUR_APPOINTMENT = new AppointmentBuilder().build();
    private static final Appointment FIVE_MIN_APPOINTMENT = new AppointmentBuilder().build();
    private static final Appointment THIRTY_MIN_APPOINTMENT = new AppointmentBuilder().build();

    private TypicalAppointments() {
    } // prevents instantiation
    public static List<Appointment> getTypicalAppointments() {
        return new ArrayList<>(Arrays.asList(TWO_HOUR_APPOINTMENT, FIVE_MIN_APPOINTMENT, THIRTY_MIN_APPOINTMENT));
    }
}
