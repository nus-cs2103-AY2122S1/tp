package seedu.address.model.done;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.model.person.Person;

/**
 * Tests that a {@code Person}'s {@code Done} any of the keywords given.
 */
public class DoneContainsKeywordsPredicate implements Predicate<Person> {

    private final List<String> keywords;

    /**
     * Constructs a {@code DoneContainsKeywordsPredicate}.
     *
     * @param keywords {@code List} of keywords to match against a {@code Done}.
     */
    public DoneContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Person person) {
        if (keywords.isEmpty()) {
            return false;
        } else {
            return keywords.stream()
                    .allMatch(keyword -> person.getDone().getDoneStatus().equalsIgnoreCase(keyword));
        }
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DoneContainsKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((DoneContainsKeywordsPredicate) other).keywords)); // state check
    }
}
