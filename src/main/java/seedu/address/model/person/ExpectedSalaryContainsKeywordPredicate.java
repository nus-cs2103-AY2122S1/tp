package seedu.address.model.person;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;

/**
 * Tests that a {@code Person}'s {@code ExpectedSalary} matches any of the keywords given.
 */
public class ExpectedSalaryContainsKeywordPredicate implements Predicate<Person> {

    private final List<String> keywords;

    public ExpectedSalaryContainsKeywordPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Person person) {
        return keywords.stream()
                .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(person.getExpectedSalary().value, keyword));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ExpectedSalaryContainsKeywordPredicate // instanceof handles nulls
                && keywords.equals(((ExpectedSalaryContainsKeywordPredicate) other).keywords)); // state check
    }
}
