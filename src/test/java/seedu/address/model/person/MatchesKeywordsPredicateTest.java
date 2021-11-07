package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.PersonBuilder;

public class MatchesKeywordsPredicateTest {

    @Test
    public void equals() {
        List<String> firstPredicateKeywordList = Collections.singletonList("first");
        List<String> secondPredicateKeywordList = Arrays.asList("first", "second");

        MatchesKeywordsPredicate firstPredicate = new MatchesKeywordsPredicate(firstPredicateKeywordList);
        MatchesKeywordsPredicate secondPredicate = new MatchesKeywordsPredicate(secondPredicateKeywordList);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        MatchesKeywordsPredicate firstPredicateCopy = new MatchesKeywordsPredicate(firstPredicateKeywordList);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different person -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_matchesKeywords_returnsTrue() {
        // One keyword
        MatchesKeywordsPredicate predicate = new MatchesKeywordsPredicate(Collections.singletonList("Alice"));
        assertTrue(predicate.test(new PersonBuilder().withName("Alice Bob").build()));

        // Multiple keywords
        predicate = new MatchesKeywordsPredicate(Arrays.asList("Alice", "Bob"));
        assertTrue(predicate.test(new PersonBuilder().withName("Alice Bob").build()));

        // Only one matching keyword
        predicate = new MatchesKeywordsPredicate(Arrays.asList("Bob", "Carol"));
        assertTrue(predicate.test(new PersonBuilder().withName("Alice Carol").build()));

        // Mixed-case keywords
        predicate = new MatchesKeywordsPredicate(Arrays.asList("aLIce", "bOB"));
        assertTrue(predicate.test(new PersonBuilder().withName("Alice Bob").build()));

        // Multiple keywords (name, phone, email, telegram)
        predicate = new MatchesKeywordsPredicate(Arrays.asList("Alice", "98765432", "carol@email.com", "@alice"));
        assertTrue(predicate.test(new PersonBuilder().withName("Alice").withPhone("98765432")
                .withEmail("alice@email.com").withTelegram("@alice").build()));
        assertTrue(predicate.test(new PersonBuilder().withName("Bob").withPhone("98765432")
                .withEmail("bob@email.com").withTelegram("@bob").build()));
        assertTrue(predicate.test(new PersonBuilder().withName("Carol").withPhone("98765432")
                .withEmail("carol@email.com").withTelegram("@carol").build()));
        assertTrue(predicate.test(new PersonBuilder().withName("David").withPhone("98765432")
                .withEmail("david@email.com").withTelegram("@david").build()));
    }

    @Test
    public void test_doesNotMatchKeywords_returnsFalse() {
        // Zero keywords
        MatchesKeywordsPredicate predicate = new MatchesKeywordsPredicate(Collections.emptyList());
        assertFalse(predicate.test(new PersonBuilder().withName("Alice").build()));

        // Non-matching keyword
        predicate = new MatchesKeywordsPredicate(Arrays.asList("Carol"));
        assertFalse(predicate.test(new PersonBuilder().withName("Alice Bob").build()));
    }
}
