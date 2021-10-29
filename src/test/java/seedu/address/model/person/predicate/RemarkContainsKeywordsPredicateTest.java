package seedu.address.model.person.predicate;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.PersonBuilder;

public class RemarkContainsKeywordsPredicateTest {

    @Test
    public void equals() {
        List<String> firstPredicateKeywordList = Collections.singletonList("She likes to code");
        List<String> secondPredicateKeywordList = Arrays.asList("She likes to code", "loves exercising");

        RemarkContainsKeywordsPredicate firstPredicate =
                new RemarkContainsKeywordsPredicate(firstPredicateKeywordList);
        RemarkContainsKeywordsPredicate secondPredicate =
                new RemarkContainsKeywordsPredicate(secondPredicateKeywordList);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        RemarkContainsKeywordsPredicate firstPredicateCopy =
                new RemarkContainsKeywordsPredicate(firstPredicateKeywordList);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different person -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_remarkContainsKeywords_returnsTrue() {
        // One keyword
        RemarkContainsKeywordsPredicate predicate =
                new RemarkContainsKeywordsPredicate(Collections.singletonList("likes"));
        assertTrue(predicate.test(new PersonBuilder().withRemark("She likes coding").build()));

        // Multiple keywords
        predicate = new RemarkContainsKeywordsPredicate(Arrays.asList("likes", "coding"));
        assertTrue(predicate.test(new PersonBuilder().withRemark("She likes coding").build()));

        // Mixed-case keywords
        predicate = new RemarkContainsKeywordsPredicate(Arrays.asList("lIkeS", "CoDinG"));
        assertTrue(predicate.test(new PersonBuilder().withRemark("She likes coding").build()));
    }

    @Test
    public void test_remarkContainsMultipleKeywords_returnFalse() {
        // Only one matching keyword
        RemarkContainsKeywordsPredicate predicate =
                new RemarkContainsKeywordsPredicate(Arrays.asList("dislikes", "coding"));
        assertFalse(predicate.test(new PersonBuilder().withRemark("She likes coding").build()));
    }

    @Test
    public void test_remarkDoesNotContainKeywords_returnsFalse() {
        // Zero keywords
        RemarkContainsKeywordsPredicate predicate = new RemarkContainsKeywordsPredicate(Collections.emptyList());
        assertFalse(predicate.test(new PersonBuilder().withRemark("She likes coding").build()));

        // Non-matching keyword
        predicate = new RemarkContainsKeywordsPredicate(Arrays.asList("dislikes"));
        assertFalse(predicate.test(new PersonBuilder().withRemark("She likes coding").build()));

        // Keywords match phone, email and nationality, but does not match remark
        predicate = new RemarkContainsKeywordsPredicate(
                Arrays.asList("12345", "alice@email.com", "Malaysian", "dislikes eating"));
        assertFalse(predicate.test(new PersonBuilder().withRemark("She likes coding").withPhone("12345")
                .withEmail("alice@email.com").withNationality("Malaysian").build()));
    }
}
