package seedu.address.model.person.predicate;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.PersonBuilder;

public class GenderContainsKeywordsPredicateTest {

    @Test
    public void equals() {
        List<String> firstPredicateKeywordList = Collections.singletonList("F");
        List<String> secondPredicateKeywordList = Arrays.asList("F", "M");

        GenderContainsKeywordsPredicate firstPredicate =
                new GenderContainsKeywordsPredicate(firstPredicateKeywordList);
        GenderContainsKeywordsPredicate secondPredicate =
                new GenderContainsKeywordsPredicate(secondPredicateKeywordList);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        GenderContainsKeywordsPredicate firstPredicateCopy =
                new GenderContainsKeywordsPredicate(firstPredicateKeywordList);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different person -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_genderContainsKeywords_returnsTrue() {
        // One keyword
        GenderContainsKeywordsPredicate predicate = new GenderContainsKeywordsPredicate(Collections.singletonList("F"));
        assertTrue(predicate.test(new PersonBuilder().withGender("F").build()));



        // Mixed-case keyword
        predicate = new GenderContainsKeywordsPredicate(Arrays.asList("f"));
        assertTrue(predicate.test(new PersonBuilder().withGender("F").build()));
    }

    @Test
    public void test_genderContainsMultipleKeyword_returnFalse() {
        // Only one matching keyword
        GenderContainsKeywordsPredicate predicate = new GenderContainsKeywordsPredicate(Arrays.asList("O", "F"));
        assertFalse(predicate.test(new PersonBuilder().withGender("O").build()));
    }

    @Test
    public void test_genderDoesNotContainKeywords_returnsFalse() {
        // Zero keywords
        GenderContainsKeywordsPredicate predicate = new GenderContainsKeywordsPredicate(Collections.emptyList());
        assertFalse(predicate.test(new PersonBuilder().withGender("").build()));

        // Non-matching keyword
        predicate = new GenderContainsKeywordsPredicate(Arrays.asList("F"));
        assertFalse(predicate.test(new PersonBuilder().withGender("O").build()));

        // Keywords match phone, email and nationality, but does not match gender
        predicate = new GenderContainsKeywordsPredicate(Arrays.asList("12345", "alice@email.com", "Malaysia", "O"));
        assertFalse(predicate.test(new PersonBuilder().withGender("F").withPhone("12345")
                .withEmail("alice@email.com").withNationality("Malaysian").build()));
    }
}
