package socialite.model.person;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import socialite.testutil.PersonBuilder;

public class ContainsKeywordsPredicateTest {

    @Test
    public void equals() {
        List<String> firstPredicateKeywordList = Collections.singletonList("first");
        List<String> secondPredicateKeywordList = Arrays.asList("first", "second");

        ContainsKeywordsPredicate firstPredicate = new ContainsKeywordsPredicate(firstPredicateKeywordList);
        ContainsKeywordsPredicate secondPredicate = new ContainsKeywordsPredicate(secondPredicateKeywordList);

        // same object -> returns true
        assertEquals(firstPredicate, firstPredicate);

        // same values -> returns true
        ContainsKeywordsPredicate firstPredicateCopy = new ContainsKeywordsPredicate(firstPredicateKeywordList);
        assertEquals(firstPredicate, firstPredicateCopy);

        // different types -> returns false
        assertNotEquals(1, firstPredicate);

        // null -> returns false
        assertNotEquals(null, firstPredicate);

        // different person -> returns false
        assertNotEquals(firstPredicate, secondPredicate);
    }

    @Test
    public void test_nameContainsKeywords_returnsTrue() {
        // One matching keyword
        ContainsKeywordsPredicate predicate = new ContainsKeywordsPredicate(Collections.singletonList("Alice"));
        assertTrue(predicate.test(new PersonBuilder().withName("Alice Bob").build()));

        // Multiple matching keywords
        predicate = new ContainsKeywordsPredicate(Arrays.asList("Alice", "Bob"));
        assertTrue(predicate.test(new PersonBuilder().withName("Alice Bob").build()));

        // One non-matching keyword, should return false
        predicate = new ContainsKeywordsPredicate(Arrays.asList("Bob", "Carol"));
        assertFalse(predicate.test(new PersonBuilder().withName("Alice Carol").build()));

        // Mixed-case keywords
        predicate = new ContainsKeywordsPredicate(Arrays.asList("aLIce", "bOB"));
        assertTrue(predicate.test(new PersonBuilder().withName("Alice Bob").build()));
    }

    @Test
    public void test_nameDoesNotContainKeywords_returnsFalse() {
        // Zero keywords
        ContainsKeywordsPredicate predicate = new ContainsKeywordsPredicate(Collections.emptyList());
        assertFalse(predicate.test(new PersonBuilder().withName("Alice").build()));

        // Non-matching keyword
        predicate = new ContainsKeywordsPredicate(Arrays.asList("Carol"));
        assertFalse(predicate.test(new PersonBuilder().withName("Alice Bob").build()));

        // Keywords match phone, email and address, but does not match name
        predicate = new ContainsKeywordsPredicate(Arrays.asList("12345", "alice@email.com", "Main", "Street"));
        assertFalse(predicate.test(new PersonBuilder().withName("Alice").withPhone("12345")
                .build()));
    }

    @Test
    public void test_personHasHandles() {
        ContainsKeywordsPredicate fbPredicate = new ContainsKeywordsPredicate(Arrays.asList("p/facebook"));
        assertTrue(fbPredicate.test(new PersonBuilder().withName("Alice").withFacebook("dummy.link").build()));
        assertFalse(fbPredicate.test(new PersonBuilder().withName("Bob").withFacebook(null).build()));
        ContainsKeywordsPredicate igPredicate = new ContainsKeywordsPredicate(Arrays.asList("p/instagram"));
        assertTrue(igPredicate.test(new PersonBuilder().withName("Alice").withInstagram("dummy_link").build()));
        assertFalse(igPredicate.test(new PersonBuilder().withName("Bob").withInstagram(null).build()));

        ContainsKeywordsPredicate telePredicate = new ContainsKeywordsPredicate(Arrays.asList("p/telegram"));
        assertTrue(telePredicate.test(new PersonBuilder().withName("Alice").withTelegram("dummy_link").build()));
        assertFalse(telePredicate.test(new PersonBuilder().withName("Bob").withTelegram(null).build()));

        ContainsKeywordsPredicate tiktokPredicate = new ContainsKeywordsPredicate(Arrays.asList("p/tiktok"));
        assertTrue(tiktokPredicate.test(new PersonBuilder().withName("Alice").withTikTok("dummy.link").build()));
        assertFalse(tiktokPredicate.test(new PersonBuilder().withName("Bob").withTikTok(null).build()));

        ContainsKeywordsPredicate twitterPredicate = new ContainsKeywordsPredicate(Arrays.asList("p/twitter"));
        assertTrue(twitterPredicate.test(new PersonBuilder().withName("Alice").withTwitter("dummylink").build()));
        assertFalse(twitterPredicate.test(new PersonBuilder().withName("Bob").withTwitter(null).build()));
    }

    @Test
    public void test_tagContainsKeywords() {
        ContainsKeywordsPredicate predicate = new ContainsKeywordsPredicate(Arrays.asList("t/colleagues"));
        assertTrue(predicate.test(new PersonBuilder().withName("Alice").withTags("colleagues", "friends").build()));
        assertFalse(predicate.test(new PersonBuilder().withName("Bob").withTags("friends").build()));

        // Empty predicate -> anything returns true
        ContainsKeywordsPredicate emptyPredicate = new ContainsKeywordsPredicate(Arrays.asList("t/"));
        assertTrue(emptyPredicate.test(
                new PersonBuilder().withName("Alice").withTags("colleagues", "friends").build()));
        assertTrue(emptyPredicate.test(new PersonBuilder().withName("Bob").withTags("friends").build()));

    }


}
