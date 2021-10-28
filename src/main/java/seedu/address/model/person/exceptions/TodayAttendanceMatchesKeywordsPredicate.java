package seedu.address.model.person.exceptions;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.model.person.Person;
import seedu.address.model.person.TodayAttendance;



public class TodayAttendanceMatchesKeywordsPredicate implements Predicate<Person> {
    private final List<TodayAttendance> todayAttendance;

    public TodayAttendanceMatchesKeywordsPredicate(List<TodayAttendance> todayAttendance) {
        this.todayAttendance = todayAttendance;
    }

    @Override
    public boolean test(Person person) {
        return false;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof TodayAttendanceMatchesKeywordsPredicate // instanceof handles nulls
                && todayAttendance.equals(((TodayAttendanceMatchesKeywordsPredicate) other)
                .todayAttendance)); // state check
    }
}
