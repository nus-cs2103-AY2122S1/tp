package seedu.address.model.interview;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;
import seedu.address.model.person.Person;


/**
 * Tests that a {@code Person}'s {@code Interview} matches any of the keywords given.
 */
public class InterviewContainsKeywordsPredicate implements Predicate<Person> {

    public static final String MESSAGE_CONSTRAINTS =
            "Interview can only be searched through numbers and relevant characters such as '-' or ':', "
                    + "and it should not contain alphabets ";

    private final List<String> keywords;

    public InterviewContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Person person) {
        return keywords.stream()
                .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(
                        person.getInterview().orElse(Interview.EMPTY_INTERVIEW).displayTime(), keyword));
    }


    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof InterviewContainsKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((InterviewContainsKeywordsPredicate) other).keywords)); // state check
    }
}
