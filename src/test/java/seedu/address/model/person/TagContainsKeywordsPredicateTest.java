package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.PersonBuilder;

public class TagContainsKeywordsPredicateTest {

    @Test
    public void equals() {
        List<String> firstPredicateKeywordList = Collections.singletonList("Yes");
        List<String> secondPredicateKeywordList = Arrays.asList("No", "Maybe");

        TagContainsKeywordsPredicate firstPredicate = new TagContainsKeywordsPredicate(firstPredicateKeywordList);
        TagContainsKeywordsPredicate secondPredicate = new TagContainsKeywordsPredicate(secondPredicateKeywordList);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        TagContainsKeywordsPredicate firstPredicateCopy = new TagContainsKeywordsPredicate(firstPredicateKeywordList);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different person -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_tagContainsKeywords_returnsTrue() {
        // One keyword
        TagContainsKeywordsPredicate predicate = new TagContainsKeywordsPredicate(Collections.singletonList("Yes"));
        assertTrue(predicate.test(new PersonBuilder().withTags("Yes").build()));

        // Multiple keywords
        predicate = new TagContainsKeywordsPredicate(Arrays.asList("Yes", "No"));
        assertTrue(predicate.test(new PersonBuilder().withTags("Yes").build()));

        // Mixed-case keyword
        predicate = new TagContainsKeywordsPredicate(Arrays.asList("YES"));
        assertTrue(predicate.test(new PersonBuilder().withTags("yes").build()));

        // Only one matching keyword
        predicate = new TagContainsKeywordsPredicate(Arrays.asList("Yes", "No"));
        assertTrue(predicate.test(new PersonBuilder().withTags("YesNo").build()));
    }

    @Test
    public void test_tagDoesNotContainKeywords_returnsFalse() {
        // Zero keywords
        TagContainsKeywordsPredicate predicate = new TagContainsKeywordsPredicate(Collections.emptyList());
        assertFalse(predicate.test(new PersonBuilder().withTags("Yes").build()));

        // Non-matching keyword
        predicate = new TagContainsKeywordsPredicate(Arrays.asList("Yes"));
        assertFalse(predicate.test(new PersonBuilder().withTags("No").build()));

        // Keywords match phone, email and nationality, but does not match name
        predicate = new TagContainsKeywordsPredicate(Arrays.asList("Yes", "No", "Maybe"));
        assertFalse(predicate.test(new PersonBuilder().withName("Amy Bee").withPhone("12345")
                .withEmail("alice@email.com").withNationality("North Korea").withTutorialGroup("19")
                .withTags("Meh").build()));
    }
}
