package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.PersonBuilder;

public class ExpectedSalaryWithinRangePredicateTest {

    @Test
    public void equals() {
        List<String> firstPredicateKeywordList = Collections.singletonList("1000");
        List<String> secondPredicateKeywordList = Arrays.asList("1000", "2000");

        ExpectedSalaryWithinRangePredicate firstPredicate =
                new ExpectedSalaryWithinRangePredicate(firstPredicateKeywordList);
        ExpectedSalaryWithinRangePredicate secondPredicate =
                new ExpectedSalaryWithinRangePredicate(secondPredicateKeywordList);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        ExpectedSalaryWithinRangePredicate firstPredicateCopy =
                new ExpectedSalaryWithinRangePredicate(firstPredicateKeywordList);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different person -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_expectedSalaryWithinRange_returnsTrue() {
        // One salary
        ExpectedSalaryWithinRangePredicate predicate = new ExpectedSalaryWithinRangePredicate(Collections
                .singletonList("1000"));
        assertTrue(predicate.test(new PersonBuilder().withExpectedSalary("1000").build()));

        // One matching salary out of multiple salaries
        predicate = new ExpectedSalaryWithinRangePredicate(Arrays.asList("1000", "2000"));
        assertTrue(predicate.test(new PersonBuilder().withExpectedSalary("1000").build()));

        // Salary within range
        int salary = 1000;
        int salaryWithinRange = salary + ExpectedSalaryWithinRangePredicate.RANGE;
        predicate = new ExpectedSalaryWithinRangePredicate(Collections.singletonList(Integer.toString(salary)));
        assertTrue(predicate.test(new PersonBuilder()
                .withExpectedSalary(Integer.toString(salaryWithinRange)).build()));
    }

    @Test
    public void test_expectedSalaryOutOfRange_returnsFalse() {

        // Salary out of range
        int salary1 = 2000;
        int salaryOutOfRange1 = salary1 + ExpectedSalaryWithinRangePredicate.RANGE + 1;
        ExpectedSalaryWithinRangePredicate predicate =
                new ExpectedSalaryWithinRangePredicate(Collections.singletonList(Integer.toString(salary1)));
        assertFalse(predicate.test(new PersonBuilder()
                .withExpectedSalary(Integer.toString(salaryOutOfRange1)).build()));

        // Multiple salaries out of range
        int salary2 = 2000;
        int salary3 = 3000;
        int salaryOutOfRange2 = salary2 + salary3 + ExpectedSalaryWithinRangePredicate.RANGE + 1;
        predicate = new ExpectedSalaryWithinRangePredicate(
                Arrays.asList(Integer.toString(salary2), Integer.toString(salary3)));
        assertFalse(predicate.test(new PersonBuilder()
                .withExpectedSalary(Integer.toString(salaryOutOfRange2)).build()));

    }
}
