package seedu.address.model.person;

import java.util.List;
import java.util.function.Predicate;

public class EmploymentTypeContainsKeywordsPredicate implements Predicate<Person> {

    private final List<String> keywords;

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
