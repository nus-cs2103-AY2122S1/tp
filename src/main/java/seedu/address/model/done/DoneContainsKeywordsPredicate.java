package seedu.address.model.done;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.model.person.Person;

public class DoneContainsKeywordsPredicate implements Predicate<Person> {

    private final List<String> keywords;

    /**
     * Constructor for DoneContainsKeywordsPredicate.
     *
     * @param keywords List of words to match against the Done of every applicant.
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
