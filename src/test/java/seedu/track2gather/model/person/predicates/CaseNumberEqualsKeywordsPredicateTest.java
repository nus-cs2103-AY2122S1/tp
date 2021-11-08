package seedu.track2gather.model.person.predicates;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.track2gather.testutil.PersonBuilder;

public class CaseNumberEqualsKeywordsPredicateTest {

    @Test
    public void equals() {
        List<String> firstPredicateKeywordList = Collections.singletonList("1");
        List<String> secondPredicateKeywordList = Arrays.asList("1", "2");

        CaseNumberEqualsKeywordsPredicate firstPredicate =
                new CaseNumberEqualsKeywordsPredicate(firstPredicateKeywordList);
        CaseNumberEqualsKeywordsPredicate secondPredicate =
                new CaseNumberEqualsKeywordsPredicate(secondPredicateKeywordList);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        CaseNumberEqualsKeywordsPredicate firstPredicateCopy =
                new CaseNumberEqualsKeywordsPredicate(firstPredicateKeywordList);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different person -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_caseNumberEqualsKeywords_returnsTrue() {
        // One keyword
        CaseNumberEqualsKeywordsPredicate predicate =
                new CaseNumberEqualsKeywordsPredicate(Collections.singletonList("111111"));
        assertTrue(predicate.test(new PersonBuilder().withCaseNumber("111111").build()));

        // Only one matching keyword
        predicate = new CaseNumberEqualsKeywordsPredicate(Arrays.asList("111111", "222222"));
        assertTrue(predicate.test(new PersonBuilder().withCaseNumber("111111").build()));
    }

    @Test
    public void test_caseNumberDoesNotEqualsKeywords_returnsFalse() {
        // Zero keywords
        CaseNumberEqualsKeywordsPredicate predicate =
                new CaseNumberEqualsKeywordsPredicate(Collections.emptyList());
        assertFalse(predicate.test(new PersonBuilder().withCaseNumber("111111").build()));

        // Non-matching keyword
        predicate = new CaseNumberEqualsKeywordsPredicate(Arrays.asList("666666"));
        assertFalse(predicate.test(new PersonBuilder().withCaseNumber("111111").build()));

        // Case number contains keyword but not equals to keyword
        predicate = new CaseNumberEqualsKeywordsPredicate(Arrays.asList("111"));
        assertFalse(predicate.test(new PersonBuilder().withCaseNumber("111111").build()));

        // Keywords match name, phone, email, address, shn period (start) and shn period (end), but does not match
        // case number
        predicate = new CaseNumberEqualsKeywordsPredicate(Arrays.asList("Alice", "12345", "alice@email.com", "Main",
                "Street", "2000-01-01", "01-02"));
        assertFalse(predicate.test(new PersonBuilder().withName("Alice").withPhone("12345").withEmail("alice@email.com")
                .withCaseNumber("111111").withHomeAddress("Main Street").withShnPeriod("2000-01-01 => 2000-01-02")
                .build()));
    }
}
