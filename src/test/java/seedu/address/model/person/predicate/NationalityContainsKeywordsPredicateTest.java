package seedu.address.model.person.predicate;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.PersonBuilder;

public class NationalityContainsKeywordsPredicateTest {

    @Test
    public void equals() {
        List<String> firstPredicateKeywordList = Collections.singletonList("Malaysian");
        List<String> secondPredicateKeywordList = Arrays.asList("Singaporean", "Korean, North");

        NationalityContainsKeywordsPredicate firstPredicate =
                new NationalityContainsKeywordsPredicate(firstPredicateKeywordList);
        NationalityContainsKeywordsPredicate secondPredicate =
                new NationalityContainsKeywordsPredicate(secondPredicateKeywordList);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        NationalityContainsKeywordsPredicate firstPredicateCopy =
                new NationalityContainsKeywordsPredicate(firstPredicateKeywordList);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different person -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_nationalityContainsKeywords_returnsTrue() {
        // One keyword
        NationalityContainsKeywordsPredicate predicate =
                new NationalityContainsKeywordsPredicate(Collections.singletonList("Singaporean"));
        assertTrue(predicate.test(new PersonBuilder().withNationality("Singaporean").build()));

        // Mixed-case keyword
        predicate = new NationalityContainsKeywordsPredicate(Arrays.asList("Singaporean"));
        assertTrue(predicate.test(new PersonBuilder().withNationality("singaporean").build()));
    }

    @Test
    public void test_nationalityContainsMultipleKeywords_returnFalse() {
        // Multiple keywords
        NationalityContainsKeywordsPredicate predicate =
                new NationalityContainsKeywordsPredicate(Arrays.asList("Singaporean", "Malaysian"));
        assertFalse(predicate.test(new PersonBuilder().withNationality("Singaporean").build()));
    }

    @Test
    public void test_nationalityDoesNotContainKeywords_returnsFalse() {
        // Zero keywords
        NationalityContainsKeywordsPredicate predicate =
                new NationalityContainsKeywordsPredicate(Collections.emptyList());
        assertFalse(predicate.test(new PersonBuilder().withNationality("Singaporean").build()));

        // Non-matching keyword
        predicate = new NationalityContainsKeywordsPredicate(Arrays.asList("Singaporean"));
        assertFalse(predicate.test(new PersonBuilder().withNationality("Malaysian").build()));

        // Keywords match phone, email and nationality, but does not match name
        predicate = new NationalityContainsKeywordsPredicate(
                Arrays.asList("12345", "alice@email.com", "Main", "Street"));
        assertFalse(predicate.test(new PersonBuilder().withName("Amy Bee").withPhone("12345")
                .withEmail("alice@email.com").withNationality("North Korean").withTutorialGroup("W19")
                .withTags("Meh").build()));
    }
}
