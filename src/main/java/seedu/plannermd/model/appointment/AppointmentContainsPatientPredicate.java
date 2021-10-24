package seedu.plannermd.model.appointment;

import static java.util.Objects.requireNonNull;
import static seedu.plannermd.logic.parser.CliSyntax.PREFIX_PATIENT;

import java.util.List;
import java.util.function.Predicate;

import seedu.plannermd.commons.util.StringUtil;

/**
 * Tests that a {@code Appointment} has a patient whose name matches any of the keywords given.
 */
public class AppointmentContainsPatientPredicate implements Predicate<Appointment> {

    private final List<String> keywords;

    /**
     * Creates an AppointmentContainsPredicate based on a given list of keywords.
     */
    public AppointmentContainsPatientPredicate(List<String> keywords) {
        requireNonNull(keywords);
        this.keywords = keywords;
    }

    @Override
    public boolean test(Appointment appointment) {
        return keywords.stream().anyMatch(keyword ->
                StringUtil.containsWordIgnoreCase(appointment.getPatient().getName().fullName, keyword));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AppointmentContainsPatientPredicate // instanceof handles nulls
                && keywords.equals(((AppointmentContainsPatientPredicate) other).keywords)); // state check
    }

    @Override
    public String toString() {
        return PREFIX_PATIENT + String.join(" ", keywords) + " ";
    }
}
