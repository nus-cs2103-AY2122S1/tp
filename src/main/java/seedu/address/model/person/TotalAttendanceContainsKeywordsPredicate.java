package seedu.address.model.person;

import java.util.List;
import java.util.function.Predicate;

/**
 * Tests that a {@code Person}'s {@code TotalAttendance} matches any of the keywords given.
 */
public class TotalAttendanceContainsKeywordsPredicate implements Predicate<Person> {
    private final List<TotalAttendance> totalAttendance;

    public TotalAttendanceContainsKeywordsPredicate(List<TotalAttendance> totalAttendance) {
        this.totalAttendance = totalAttendance;
    }

    @Override
    public boolean test(Person person) {
        return totalAttendance.stream()
                .anyMatch(totalAttendance -> person.getTotalAttendance().equals(totalAttendance));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof TotalAttendanceContainsKeywordsPredicate // instanceof handles nulls
                && totalAttendance.equals(((TotalAttendanceContainsKeywordsPredicate) other)
                .totalAttendance)); // state check
    }
}
