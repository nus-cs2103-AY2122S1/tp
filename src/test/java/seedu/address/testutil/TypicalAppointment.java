package seedu.address.testutil;

import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.CARL;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.schedule.Appointment;
import seedu.address.model.schedule.Schedule;

public class TypicalAppointment {
    public static final Appointment ALICE_APPOINTMENT = new AppointmentBuilder()
            .withClient(ALICE)
            .withLocation("369 Tanjong Rhu")
            .withTimePeriod("2021-12-25 10:00", "2021-12-25 11:00")
            .withDescription("Talk about sales")
            .build();

    public static final Appointment ALICE_CARL_APPOINTMENT = new AppointmentBuilder()
            .withClient(ALICE)
            .addClient(CARL)
            .withLocation("369 Tanjong Rhu")
            .withTimePeriod("2021-12-25 10:00", "2021-12-25 11:00")
            .withDescription("Talk about sales")
            .build();

    public static final Appointment CARL_APPOINTMENT = new AppointmentBuilder()
            .withClient(CARL)
            .withLocation("vivocity")
            .withTimePeriod("2021-12-25 10:00", "2021-12-25 11:00")
            .withDescription("Halloween Sales")
            .build();

    public static List<Appointment> getTypicalAppointments() {
        return new ArrayList<>(Arrays.asList(ALICE_APPOINTMENT, CARL_APPOINTMENT));
    }

    public static Schedule getTypicalSchedule() {
        Schedule schedule = new Schedule();
        for (Appointment appointment : getTypicalAppointments()) {
            schedule.addAppointment(appointment);
        }
        return schedule;
    }

    public static Schedule getTypicalSchedule(Schedule schedule) {
        for (Appointment appointment : getTypicalAppointments()) {
            schedule.addAppointment(appointment);
        }
        return schedule;
    }
}
