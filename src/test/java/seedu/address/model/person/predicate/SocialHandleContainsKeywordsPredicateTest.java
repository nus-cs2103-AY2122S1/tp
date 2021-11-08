package seedu.address.model.person.predicate;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.PersonBuilder;

public class SocialHandleContainsKeywordsPredicateTest {

    @Test
    public void equals() {
        List<String> firstPredicateKeywordList = Collections.singletonList("tg:first");
        List<String> secondPredicateKeywordList = Arrays.asList("tg:first", "tg:second");

        SocialHandleContainsKeywordsPredicate firstPredicate =
                new SocialHandleContainsKeywordsPredicate(firstPredicateKeywordList);
        SocialHandleContainsKeywordsPredicate secondPredicate =
                new SocialHandleContainsKeywordsPredicate(secondPredicateKeywordList);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        SocialHandleContainsKeywordsPredicate firstPredicateCopy =
                new SocialHandleContainsKeywordsPredicate(firstPredicateKeywordList);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different person -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_socialHandleContainsKeywords_returnsTrue() {
        // One keyword
        SocialHandleContainsKeywordsPredicate predicate =
                new SocialHandleContainsKeywordsPredicate(Collections.singletonList("tg:Alice"));
        assertTrue(predicate.test(new PersonBuilder().withSocialHandles("tg:Alice").build()));

        // Mixed-case keywords
        predicate = new SocialHandleContainsKeywordsPredicate(Arrays.asList("tg:aLIce"));
        assertTrue(predicate.test(new PersonBuilder().withSocialHandles("tg:Alice").build()));
    }

    @Test
    public void test_socialHandleContainsMultipleKeywords_returnFalse() {
        // Only one matching keyword
        SocialHandleContainsKeywordsPredicate predicate =
                new SocialHandleContainsKeywordsPredicate(Arrays.asList("Bob", "Carol"));
        assertFalse(predicate.test(new PersonBuilder().withSocialHandles("tg:Carol").build()));
    }

    @Test
    public void test_socialHandleDoesNotContainKeywords_returnsFalse() {
        // Zero keywords
        SocialHandleContainsKeywordsPredicate predicate =
                new SocialHandleContainsKeywordsPredicate(Collections.emptyList());
        assertFalse(predicate.test(new PersonBuilder().withSocialHandles("tg:Alice").build()));

        // Non-matching keyword
        predicate = new SocialHandleContainsKeywordsPredicate(Arrays.asList("tg:Carol"));
        assertFalse(predicate.test(new PersonBuilder().withSocialHandles("tg:Alice").build()));

        // Keywords match phone, email and nationality, but does not match socialHandle
        predicate = new SocialHandleContainsKeywordsPredicate(
                Arrays.asList("12345", "alice@email.com", "Malaysian", "tg:Bob"));
        assertFalse(predicate.test(new PersonBuilder().withSocialHandles("tg:Alice").withPhone("12345")
                .withEmail("alice@email.com").withNationality("Malaysian").build()));
    }
}
