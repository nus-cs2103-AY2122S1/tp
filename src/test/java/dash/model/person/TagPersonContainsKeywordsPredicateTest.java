package dash.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import dash.testutil.PersonBuilder;

public class TagPersonContainsKeywordsPredicateTest {

    @Test
    public void equals() {
        List<String> firstPredicateKeywordList = Collections.singletonList("colleagues");
        List<String> secondPredicateKeywordList = Arrays.asList("colleagues", "neighbours");

        TagPersonContainsKeywordsPredicate firstPredicate = new TagPersonContainsKeywordsPredicate(firstPredicateKeywordList);
        TagPersonContainsKeywordsPredicate secondPredicate = new TagPersonContainsKeywordsPredicate(secondPredicateKeywordList);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        TagPersonContainsKeywordsPredicate firstPredicateCopy = new TagPersonContainsKeywordsPredicate(firstPredicateKeywordList);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different person -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_tagsContainsKeywords_returnsTrue() {
        // One keyword
        TagPersonContainsKeywordsPredicate predicate = new TagPersonContainsKeywordsPredicate(Collections.singletonList("Colleagues"));
        assertTrue(predicate.test(new PersonBuilder().withTags("Colleagues").build()));

        // Multiple keywords
        predicate = new TagPersonContainsKeywordsPredicate(Arrays.asList("Colleagues", "Neighbours"));
        assertTrue(predicate.test(new PersonBuilder().withTags("Colleagues", "Neighbours").build()));

        // Mixed-case keywords
        predicate = new TagPersonContainsKeywordsPredicate(Arrays.asList("cOlLeAgUeS", "nEiGhBoUrS"));
        assertTrue(predicate.test(new PersonBuilder().withTags("Colleagues", "Neighbours").build()));
    }

    @Test
    public void test_tagsDoesNotContainKeywords_returnsFalse() {
        // Non-matching keyword
        TagPersonContainsKeywordsPredicate predicate = new TagPersonContainsKeywordsPredicate(Arrays.asList("Friend"));
        assertFalse(predicate.test(new PersonBuilder().withTags("Colleague").build()));

        // Only one matching keyword, fails because of AND search
        predicate = new TagPersonContainsKeywordsPredicate(Arrays.asList("Colleague", "Friend"));
        assertFalse(predicate.test(new PersonBuilder().withTags("Colleague", "Neighbour").build()));

        // Keywords match phone, email and address, but does not match name
        predicate = new TagPersonContainsKeywordsPredicate(Arrays.asList("12345", "alice@email.com", "Main", "Street"));
        assertFalse(predicate.test(new PersonBuilder().withTags("Alice").withPhone("12345")
                .withEmail("alice@email.com").withAddress("Main Street").build()));
    }
}
