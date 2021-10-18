package seedu.address.model.person.predicates;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.PersonBuilder;

public class CaseNumberContainsKeywordsPredicateTest {

    @Test
    public void equals() {
        List<String> firstPredicateKeywordList = Collections.singletonList("1");
        List<String> secondPredicateKeywordList = Arrays.asList("1", "2");

        CaseNumberContainsKeywordsPredicate firstPredicate =
                new CaseNumberContainsKeywordsPredicate(firstPredicateKeywordList);
        CaseNumberContainsKeywordsPredicate secondPredicate =
                new CaseNumberContainsKeywordsPredicate(secondPredicateKeywordList);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        CaseNumberContainsKeywordsPredicate firstPredicateCopy =
                new CaseNumberContainsKeywordsPredicate(firstPredicateKeywordList);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different person -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_caseNumberContainsKeywords_returnsTrue() {
        // One keyword
        CaseNumberContainsKeywordsPredicate predicate =
                new CaseNumberContainsKeywordsPredicate(Collections.singletonList("601"));
        assertTrue(predicate.test(new PersonBuilder().withCaseNumber("60110").build()));

        // Multiple keyword
        predicate = new CaseNumberContainsKeywordsPredicate(Arrays.asList("601", "110"));
        assertTrue(predicate.test(new PersonBuilder().withCaseNumber("60110").build()));

        // Only one matching keyword
        predicate = new CaseNumberContainsKeywordsPredicate(Arrays.asList("110", "111"));
        assertTrue(predicate.test(new PersonBuilder().withCaseNumber("601110").build()));
    }

    @Test
    public void test_caseNumberDoesNotContainKeywords_returnsFalse() {
        // Zero keywords
        CaseNumberContainsKeywordsPredicate predicate =
                new CaseNumberContainsKeywordsPredicate(Collections.emptyList());
        assertFalse(predicate.test(new PersonBuilder().withCaseNumber("601110").build()));

        // Non-matching keyword
        predicate = new CaseNumberContainsKeywordsPredicate(Arrays.asList("666"));
        assertFalse(predicate.test(new PersonBuilder().withCaseNumber("601110").build()));

        // Keywords match name, phone, email, address, shn period (start) and shn period (end), but does not match
        // case number
        predicate = new CaseNumberContainsKeywordsPredicate(Arrays.asList("Alice", "12345", "alice@email.com", "Main",
                "Street", "2000-01-01", "01-02"));
        assertFalse(predicate.test(new PersonBuilder().withName("Alice").withPhone("12345").withEmail("alice@email.com")
                .withCaseNumber("601110").withHomeAddress("Main Street").withShnPeriod("2000-01-01 => 2000-01-02")
                .build()));
    }
}
