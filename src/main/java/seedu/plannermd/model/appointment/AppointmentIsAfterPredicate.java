package seedu.plannermd.model.appointment;

import static java.util.Objects.requireNonNull;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.function.Predicate;

public class AppointmentIsAfterPredicate implements Predicate<Appointment> {

    private final LocalDateTime filterStartDateTime;

    public AppointmentIsAfterPredicate(LocalDate filterStartDateTime) {
        requireNonNull(filterStartDateTime);
        this.filterStartDateTime = filterStartDateTime.atStartOfDay();
    }

    public AppointmentIsAfterPredicate(LocalDateTime filterStartDateTime) {
        requireNonNull(filterStartDateTime);
        this.filterStartDateTime = filterStartDateTime;
    }

    @Override
    public boolean test(Appointment appointment) {
        LocalDateTime apptStartTime = appointment.getAppointmentStartTime();
        return !apptStartTime.isBefore(filterStartDateTime);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AppointmentIsAfterPredicate // instanceof handles nulls
                && filterStartDateTime.equals(((AppointmentIsAfterPredicate) other).filterStartDateTime)); // state check
    }

}
