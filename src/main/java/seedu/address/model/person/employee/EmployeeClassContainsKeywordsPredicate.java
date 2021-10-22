package seedu.address.model.person.employee;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;

/**
 * Tests that a {@code employee}'s {@code Name} matches any of the keywords given.
 */
public class EmployeeClassContainsKeywordsPredicate implements Predicate<Employee> {
    private final List<String> keywords;

    public EmployeeClassContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Employee employee) {
        return keywords.stream()
                .anyMatch(keyword ->
                        StringUtil.containsSubstringIgnoreCase(
                                employee.getName().fullName, keyword)
                                || StringUtil.containsSubstringIgnoreCase(
                                employee.getEmail().value, keyword)
                                || StringUtil.containsSubstringIgnoreCase(
                                employee.getPhone().value, keyword)
                                || StringUtil.containsSubstringIgnoreCase(
                                employee.getAddress().value, keyword)
                                || StringUtil.containsSubstringIgnoreCase(
                                employee.getLeaves().currentLeaves, keyword)
                                || StringUtil.containsSubstringIgnoreCase(
                                employee.getJobTitle().jobTitle, keyword)
                                || StringUtil.containsSubstringIgnoreCase(
                                employee.getSalary().currentSalary, keyword)
                                || StringUtil.containsSubstringIgnoreCase(
                                employee.getShifts().toString(), keyword)
                                || StringUtil.containsSubstringIgnoreCase(
                                employee.getTags().toString(), keyword)
                );
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof EmployeeClassContainsKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((EmployeeClassContainsKeywordsPredicate) other).keywords)); // state check
    }

}
