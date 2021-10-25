package seedu.plannermd.model.appointment;

import java.util.List;
import java.util.function.Predicate;

import seedu.plannermd.commons.util.StringUtil;

/**
 * Tests that a {@code Person}'s {@code Name} matches any of the keywords given.
 */
public class AppointmentFieldsContainKeywordsPredicate implements Predicate<Appointment> {
    private final List<String> keywords;

    public AppointmentFieldsContainKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Appointment appointment) {
        return keywords.stream()
                .anyMatch(keyword ->
                        StringUtil.containsWordIgnoreCase(appointment.getPatient().getName().fullName, keyword)
                );
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof seedu.plannermd.model.appointment.AppointmentFieldsContainKeywordsPredicate
                // instanceof handles nulls
                && keywords.equals((
                        (seedu.plannermd.model.appointment.AppointmentFieldsContainKeywordsPredicate) other)
                            .keywords)); // state check
    }

}
