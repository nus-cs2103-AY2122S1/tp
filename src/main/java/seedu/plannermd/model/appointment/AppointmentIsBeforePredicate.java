package seedu.plannermd.model.appointment;

import static java.util.Objects.requireNonNull;
import static seedu.plannermd.logic.parser.CliSyntax.PREFIX_END;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.function.Predicate;

/**
 * Tests that a {@code Appointment} has a starting time that is before the endDate given.
 */
public class AppointmentIsBeforePredicate implements Predicate<Appointment> {

    private final LocalDateTime filerEndDateTime;

    /**
     * Creates an AppointmentIsAfterPredicate with a LocalDate object.
     */
    public AppointmentIsBeforePredicate(LocalDate filterEndDateTime) {
        requireNonNull(filterEndDateTime);
        // The filter end date should be the last moment of the day since the filter is inclusive
        // of the end date itself.
        this.filerEndDateTime = filterEndDateTime.atTime(23, 59, 59, 99);
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

    @Override
    public String toString() {
        return PREFIX_END + filerEndDateTime.format(AppointmentDate.DATE_FORMATTER) + " ";
    }
}
