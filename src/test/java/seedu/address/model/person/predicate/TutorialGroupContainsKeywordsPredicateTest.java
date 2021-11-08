package seedu.address.model.person.predicate;

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
                new TutorialGroupContainsKeywordsPredicate(Collections.singletonList("T19"));
        assertTrue(predicate.test(new PersonBuilder().withTutorialGroup("T19").build()));

        // Mixed-Case Keywords
        predicate = new TutorialGroupContainsKeywordsPredicate(Collections.singletonList("f19"));
        assertTrue(predicate.test(new PersonBuilder().withTutorialGroup("F19").build()));
    }

    @Test
    public void test_tutorialGroupContainsMultipleKeywords_returnsFalse() {
        // Only one matching keyword
        TutorialGroupContainsKeywordsPredicate predicate =
                new TutorialGroupContainsKeywordsPredicate(Arrays.asList("T19", "T32"));
        assertFalse(predicate.test(new PersonBuilder().withTutorialGroup("T19").build()));
    }

    @Test
    public void test_tutorialGroupDoesNotContainKeywords_returnsFalse() {
        // Zero keywords
        TutorialGroupContainsKeywordsPredicate predicate =
                new TutorialGroupContainsKeywordsPredicate(Collections.emptyList());
        assertFalse(predicate.test(new PersonBuilder().withTutorialGroup("T19").build()));

        // Keywords does not match tutorial group
        predicate = new TutorialGroupContainsKeywordsPredicate(
                Arrays.asList("T20", "T21", "T22", "T23"));
        assertFalse(predicate.test(new PersonBuilder().withName("Amy Bee").withPhone("12345")
                .withEmail("alice@email.com").withNationality("North Korean").withTutorialGroup("T19")
                .withTags("Meh").build()));
    }
}
