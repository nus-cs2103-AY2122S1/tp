package seedu.address.model.person;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;

/**
 * Tests that a {@code Person}'s {@code ExpectedSalary} is within range of any of the keywords given.
 */
public class ExpectedSalaryWithinRangePredicate implements Predicate<Person> {

    public static final int RANGE = 500;

    private final List<String> keywords;

    public ExpectedSalaryWithinRangePredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Person person) {
        return keywords.stream()
                .anyMatch(keyword -> StringUtil.areUnsignedIntegersWithinRange(
                        person.getExpectedSalary().value, keyword, RANGE));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ExpectedSalaryWithinRangePredicate // instanceof handles nulls
                && keywords.equals(((ExpectedSalaryWithinRangePredicate) other).keywords)); // state check
    }

}
