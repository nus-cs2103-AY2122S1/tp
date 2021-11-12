package seedu.address.model.game;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.GameBuilder;

public class GameIdContainsKeywordPredicateTest {

    @Test
    public void equals() {
        String firstKeyword = "first";
        String secondKeyword = "second";

        GameIdContainsKeywordPredicate firstPredicate =
                new GameIdContainsKeywordPredicate(firstKeyword);
        GameIdContainsKeywordPredicate secondPredicate =
                new GameIdContainsKeywordPredicate(secondKeyword);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        GameIdContainsKeywordPredicate firstPredicateCopy =
                new GameIdContainsKeywordPredicate(firstKeyword);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different person -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_idContainsKeywords_returnsTrue() {
        // No keyword (list all)
        GameIdContainsKeywordPredicate predicate =
                new GameIdContainsKeywordPredicate("");
        assertTrue(predicate.test(new GameBuilder().withGameId(GameBuilder.DEFAULT_GAME_ID).build()));

        // Matching partial keyword
        predicate = new GameIdContainsKeywordPredicate("A");
        assertTrue(predicate.test(new GameBuilder().withGameId(GameBuilder.DEFAULT_GAME_ID).build()));

        // Mixed-case keywords
        predicate = new GameIdContainsKeywordPredicate("afT");
        assertTrue(predicate.test(new GameBuilder().withGameId(GameBuilder.DEFAULT_GAME_ID).build()));
    }

    @Test
    public void test_idDoesNotContainKeywords_returnsFalse() {
        // Non-matching keyword
        GameIdContainsKeywordPredicate predicate = new GameIdContainsKeywordPredicate("Carol");
        assertFalse(predicate.test(new GameBuilder().withGameId(GameBuilder.DEFAULT_GAME_ID).build()));
    }
}

