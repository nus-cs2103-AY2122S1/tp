package seedu.address.model.interview;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;
import seedu.address.model.person.Person;


/**
 * Tests that a {@code Person}'s {@code Interview} matches any of the keywords given.
 */
public class InterviewContainsKeywordsPredicate implements Predicate<Person> {

    private final List<String> keywords;

    public InterviewContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Person person) {
        return keywords.stream()
                .anyMatch(keyword ->
                        StringUtil.containsWordIgnoreCase(person.getInterview().get().parseTime, keyword));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof InterviewContainsKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((InterviewContainsKeywordsPredicate) other).keywords)); // state check
    }
}
