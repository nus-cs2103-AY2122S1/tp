package seedu.plannermd.model.appointment;

import static java.util.Objects.requireNonNull;

import java.time.LocalDateTime;
import java.util.function.Predicate;

public class AppointmentIsBeforePredicate implements Predicate<Appointment> {

    private final LocalDateTime filerEndDateTime;

    public AppointmentIsBeforePredicate(LocalDateTime filerEndDateTime) {
        requireNonNull(filerEndDateTime);
        this.filerEndDateTime = filerEndDateTime;
    }

    @Override
    public boolean test(Appointment appointment) {
        LocalDateTime apptStartTime = appointment.getAppointmentStartTime();
        return apptStartTime.isBefore(filerEndDateTime);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AppointmentIsBeforePredicate // instanceof handles nulls
                && filerEndDateTime.equals(((AppointmentIsBeforePredicate) other).filerEndDateTime)); // state check
    }

}
