package seedu.address.model.person;

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
        List<String> firstPredicateKeywordList = Collections.singletonList("Malaysia");
        List<String> secondPredicateKeywordList = Arrays.asList("Singapore", "Korea, North");

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
                new NationalityContainsKeywordsPredicate(Collections.singletonList("SG"));
        assertTrue(predicate.test(new PersonBuilder().withNationality("SG").build()));

        // Multiple keywords
        predicate = new NationalityContainsKeywordsPredicate(Arrays.asList("SG", "MY"));
        assertTrue(predicate.test(new PersonBuilder().withNationality("SG").build()));

        // Mixed-case keyword
        predicate = new NationalityContainsKeywordsPredicate(Arrays.asList("SG"));
        assertTrue(predicate.test(new PersonBuilder().withNationality("sg").build()));

        // Only one matching keyword
        predicate = new NationalityContainsKeywordsPredicate(Arrays.asList("SG", "MY"));
        assertTrue(predicate.test(new PersonBuilder().withNationality("SGMY").build()));
    }

    @Test
    public void test_nationalityDoesNotContainKeywords_returnsFalse() {
        // Zero keywords
        NationalityContainsKeywordsPredicate predicate =
                new NationalityContainsKeywordsPredicate(Collections.emptyList());
        assertFalse(predicate.test(new PersonBuilder().withNationality("SG").build()));

        // Non-matching keyword
        predicate = new NationalityContainsKeywordsPredicate(Arrays.asList("SG"));
        assertFalse(predicate.test(new PersonBuilder().withNationality("MY").build()));

        // Keywords match phone, email and nationality, but does not match name
        predicate = new NationalityContainsKeywordsPredicate(
                Arrays.asList("12345", "alice@email.com", "Main", "Street"));
        assertFalse(predicate.test(new PersonBuilder().withName("Amy Bee").withPhone("12345")
                .withEmail("alice@email.com").withNationality("North Korea").withTutorialGroup("19")
                .withTags("Meh").build()));
    }
}
