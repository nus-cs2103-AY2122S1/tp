package seedu.address.model.person;

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
        List<String> firstPredicateKeywordList = Collections.singletonList("@first");
        List<String> secondPredicateKeywordList = Arrays.asList("@first", "@second");

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
                new SocialHandleContainsKeywordsPredicate(Collections.singletonList("@Alice"));
        assertTrue(predicate.test(new PersonBuilder().withSocialHandle("@Alice").build()));

        // Only one matching keyword
        predicate = new SocialHandleContainsKeywordsPredicate(Arrays.asList("@Bob", "@Carol"));
        assertTrue(predicate.test(new PersonBuilder().withSocialHandle("@Carol").build()));

        // Mixed-case keywords
        predicate = new SocialHandleContainsKeywordsPredicate(Arrays.asList("@aLIce", "@bOB"));
        assertTrue(predicate.test(new PersonBuilder().withSocialHandle("@Alice").build()));
    }

    @Test
    public void test_socialHandleDoesNotContainKeywords_returnsFalse() {
        // Zero keywords
        SocialHandleContainsKeywordsPredicate predicate =
                new SocialHandleContainsKeywordsPredicate(Collections.emptyList());
        assertFalse(predicate.test(new PersonBuilder().withSocialHandle("@Alice").build()));

        // Non-matching keyword
        predicate = new SocialHandleContainsKeywordsPredicate(Arrays.asList("@Carol"));
        assertFalse(predicate.test(new PersonBuilder().withSocialHandle("@Alice").build()));

        // Keywords match phone, email and nationality, but does not match socialHandle
        predicate = new SocialHandleContainsKeywordsPredicate(
                Arrays.asList("12345", "alice@email.com", "Malaysia", "@Bob"));
        assertFalse(predicate.test(new PersonBuilder().withSocialHandle("@Alice").withPhone("12345")
                .withEmail("alice@email.com").withNationality("Malaysia").build()));
    }
}
