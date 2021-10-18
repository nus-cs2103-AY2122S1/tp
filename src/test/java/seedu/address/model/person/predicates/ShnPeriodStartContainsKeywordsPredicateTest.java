package seedu.address.model.person.predicates;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.PersonBuilder;

public class ShnPeriodStartContainsKeywordsPredicateTest {
    @Test
    public void equals() {
        List<String> firstPredicateKeywordList = Collections.singletonList("2000-01-01");
        List<String> secondPredicateKeywordList = Arrays.asList("2000-01-01", "2000-01-02");

        ShnPeriodStartContainsKeywordsPredicate firstPredicate =
                new ShnPeriodStartContainsKeywordsPredicate(firstPredicateKeywordList);
        ShnPeriodStartContainsKeywordsPredicate secondPredicate =
                new ShnPeriodStartContainsKeywordsPredicate(secondPredicateKeywordList);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        ShnPeriodStartContainsKeywordsPredicate firstPredicateCopy =
                new ShnPeriodStartContainsKeywordsPredicate(firstPredicateKeywordList);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different person -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_shnPeriodStartContainsKeywords_returnsTrue() {
        // One keyword
        ShnPeriodStartContainsKeywordsPredicate predicate =
                new ShnPeriodStartContainsKeywordsPredicate(Collections.singletonList("01-01"));
        assertTrue(predicate.test(new PersonBuilder().withShnPeriod("2000-01-01 => 2000-01-02").build()));

        // Multiple keywords
        predicate = new ShnPeriodStartContainsKeywordsPredicate(Arrays.asList("2000", "01-01"));
        assertTrue(predicate.test(new PersonBuilder().withShnPeriod("2000-01-01 => 2000-01-02").build()));

        // Only one matching keyword
        predicate = new ShnPeriodStartContainsKeywordsPredicate(Arrays.asList("1999", "01-01"));
        assertTrue(predicate.test(new PersonBuilder().withShnPeriod("2000-01-01 => 2000-01-02").build()));
    }

    @Test
    public void test_shnPeriodStartDoesNotContainKeywords_returnsFalse() {
        // Zero keywords
        ShnPeriodStartContainsKeywordsPredicate predicate =
                new ShnPeriodStartContainsKeywordsPredicate(Collections.emptyList());
        assertFalse(predicate.test(new PersonBuilder().withShnPeriod("2000-01-01 => 2000-01-02").build()));

        // Non-matching keyword
        predicate = new ShnPeriodStartContainsKeywordsPredicate(Arrays.asList("1999"));
        assertFalse(predicate.test(new PersonBuilder().withShnPeriod("2000-01-01 => 2000-01-02").build()));

        // Keyword matches with shn period end
        predicate = new ShnPeriodStartContainsKeywordsPredicate(Arrays.asList("2000-01-02"));
        assertFalse(predicate.test(new PersonBuilder().withShnPeriod("2000-01-01 => 2000-01-02").build()));

        // Keywords match name, phone, email, case number,  address, shn period (end), but does not match
        // shn period (start)
        predicate = new ShnPeriodStartContainsKeywordsPredicate(Arrays.asList("Alice", "12345", "alice@email.com",
                "601110", "Main", "Street", "2000-01-02"));
        assertFalse(predicate.test(new PersonBuilder().withName("Alice").withPhone("12345").withEmail("alice@email.com")
                .withCaseNumber("601110").withHomeAddress("Main Street").withShnPeriod("2000-01-01 => 2000-01-02")
                .build()));
    }
}
