package seedu.plannermd.model.appointment;

import static java.util.Objects.requireNonNull;
import static seedu.plannermd.logic.parser.CliSyntax.PREFIX_DOCTOR;

import java.util.List;
import java.util.function.Predicate;

import seedu.plannermd.commons.util.StringUtil;

/**
 * Tests that a {@code Appointment} has a doctor whose name matches any of the keywords given.
 */
public class AppointmentContainsDoctorPredicate implements Predicate<Appointment> {

    private final List<String> keywords;

    /**
     * Creates an AppointmentContainsDoctorPredicate based on a list of keywords.
     */
    public AppointmentContainsDoctorPredicate(List<String> keywords) {
        requireNonNull(keywords);
        this.keywords = keywords;
    }

    @Override
    public boolean test(Appointment appointment) {
        return keywords.stream().anyMatch(keyword ->
                StringUtil.containsWordIgnoreCase(appointment.getDoctor().getName().fullName, keyword));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AppointmentContainsDoctorPredicate // instanceof handles nulls
                && keywords.equals(((AppointmentContainsDoctorPredicate) other).keywords)); // state check
    }

    @Override
    public String toString() {
        return PREFIX_DOCTOR + String.join(" ", keywords) + " ";
    }
}
