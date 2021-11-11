package nustracker.model.student;

import static java.util.Objects.requireNonNull;

import java.util.function.Predicate;

import nustracker.model.event.EventName;


/**
 * Tests that a {@code Student}'s {@code EnrolledEvents} matches the keyword given.
 */
public class EnrolledEventsContainsKeywordsPredicate implements Predicate<Student> {
    private final EventName keyword;

    /**
     * Constructs an {@code EnrolledEventsContainsKeywordsPredicate}.
     *
     * @param keyword A valid event name.
     */
    public EnrolledEventsContainsKeywordsPredicate(EventName keyword) {
        requireNonNull(keyword);
        this.keyword = keyword;
    }

    @Override
    public boolean test(Student student) {
        return student.getEvents().isEnrolledInEvent(keyword);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof EnrolledEventsContainsKeywordsPredicate // instanceof handles nulls
                && keyword.equals(((EnrolledEventsContainsKeywordsPredicate) other).keyword)); // state check
    }

}
