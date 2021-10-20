package seedu.address.model.interview;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.model.person.Person;


/**
 * Tests that a {@code Person}'s {@code Interview} matches any of the keywords given.
 */
public class InterviewContainsKeywordsPredicate implements Predicate<Person> {

    public static final String MESSAGE_CONSTRAINTS =
            "Interview can only be searched through numbers and relevant characters such as '-' or ':', "
                    + "and it should not contain alphabets ";
    /**
     * Keyword does not contain alphabets.
     */
    public static final String VALIDATION_REGEX = "\\P{IsAlphabetic}+";
    private final List<String> keywords;

    public InterviewContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Person person) {
        return keywords.stream()
                .anyMatch(keyword ->
                        person.getInterview().get().parseTime.contains(keyword));
    }

    /**
     * Returns true if a given keyword is a valid search term.
     */
    public static boolean isValidInterviewKeyword(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof InterviewContainsKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((InterviewContainsKeywordsPredicate) other).keywords)); // state check
    }
}
