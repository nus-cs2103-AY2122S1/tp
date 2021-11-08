package seedu.address.model.person;

import java.util.List;
import java.util.function.Predicate;

/**
 * Tests that a {@code Person}'s {@code EmploymentType} matches any of the keywords given.
 */
public class EmploymentTypeContainsKeywordsPredicate implements Predicate<Person> {

    private final List<String> keywords;

    /**
     * Constructs an {@code EmploymentTypeContainsKeywordsPredicate}.
     *
     * @param keywords {@code List} of keywords to be tested against an {@code EmploymentType}.
     */
    public EmploymentTypeContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Person person) {
        return keywords.stream()
                .anyMatch(keyword -> person.getEmploymentType().employmentType.toLowerCase()
                        .startsWith(keyword.toLowerCase()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof EmploymentTypeContainsKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((EmploymentTypeContainsKeywordsPredicate) other).keywords)); // state check
    }
}
