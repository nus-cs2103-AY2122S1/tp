package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.PersonBuilder;

public class TutorialGroupContainsKeywordsPredicateTest {

    @Test
    public void equals() {
        List<String> firstPredicateKeywordList = Collections.singletonList("912345567");
        List<String> secondPredicateKeywordList = Arrays.asList("999", "9876543210");

        TutorialGroupContainsKeywordsPredicate firstPredicate =
                new TutorialGroupContainsKeywordsPredicate(firstPredicateKeywordList);
        TutorialGroupContainsKeywordsPredicate secondPredicate =
                new TutorialGroupContainsKeywordsPredicate(secondPredicateKeywordList);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        TutorialGroupContainsKeywordsPredicate firstPredicateCopy =
                new TutorialGroupContainsKeywordsPredicate(firstPredicateKeywordList);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different person -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_tutorialGroupContainsKeywords_returnsTrue() {
        // One keyword
        TutorialGroupContainsKeywordsPredicate predicate =
                new TutorialGroupContainsKeywordsPredicate(Collections.singletonList("912345"));
        assertTrue(predicate.test(new PersonBuilder().withTutorialGroup("912345").build()));

        // Only one matching keyword
        predicate = new TutorialGroupContainsKeywordsPredicate(Arrays.asList("9123456", "908976"));
        assertTrue(predicate.test(new PersonBuilder().withTutorialGroup("908976").build()));

        // Non-matching keyword
        predicate = new TutorialGroupContainsKeywordsPredicate(Arrays.asList("908976", "9123456"));
        assertTrue(predicate.test(new PersonBuilder().withTutorialGroup("9123456908976").build()));
    }

    @Test
    public void test_tutorialGroupDoesNotContainKeywords_returnsFalse() {
        // Zero keywords
        TutorialGroupContainsKeywordsPredicate predicate =
                new TutorialGroupContainsKeywordsPredicate(Collections.emptyList());
        assertFalse(predicate.test(new PersonBuilder().withTutorialGroup("908976").build()));

        // Keywords does not match name
        predicate = new TutorialGroupContainsKeywordsPredicate(
                Arrays.asList("99999", "123452354", "12345235", "12345253"));
        assertFalse(predicate.test(new PersonBuilder().withName("Amy Bee").withPhone("12345")
                .withEmail("alice@email.com").withNationality("North Korea").withTutorialGroup("19")
                .withTags("Meh").build()));
    }
}
