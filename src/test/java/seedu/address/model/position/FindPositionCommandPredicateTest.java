package seedu.address.model.position;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.PositionBuilder;


public class FindPositionCommandPredicateTest {

    @Test
    public void equals() {
        List<String> firstPredicateKeywordList = Collections.singletonList("first");
        List<String> secondPredicateKeywordList = Arrays.asList("first", "second");

        FindPositionCommandPredicate firstPredicate = new FindPositionCommandPredicate(firstPredicateKeywordList);
        FindPositionCommandPredicate secondPredicate = new FindPositionCommandPredicate(secondPredicateKeywordList);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        FindPositionCommandPredicate firstPredicateCopy = new FindPositionCommandPredicate(firstPredicateKeywordList);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different position -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_nameContainsKeywords_returnsTrue() {
        // One keyword
        FindPositionCommandPredicate predicate = new FindPositionCommandPredicate(Collections.singletonList("Alice"));
        assertTrue(predicate.test(new PositionBuilder().withTitle("Alice Bob").build()));

        // Multiple keywords
        predicate = new FindPositionCommandPredicate(Arrays.asList("Alice", "Bob"));
        assertTrue(predicate.test(new PositionBuilder().withTitle("Alice Bob").build()));

        // Only one matching keyword
        predicate = new FindPositionCommandPredicate(Arrays.asList("Bob", "Carol"));
        assertTrue(predicate.test(new PositionBuilder().withTitle("Alice Carol").build()));

        // Mixed-case keywords
        predicate = new FindPositionCommandPredicate(Arrays.asList("aLIce", "bOB"));
        assertTrue(predicate.test(new PositionBuilder().withTitle("Alice Bob").build()));
    }

    @Test
    public void test_nameDoesNotContainKeywords_returnsFalse() {
        // Zero keywords
        FindPositionCommandPredicate predicate = new FindPositionCommandPredicate();

        // Non-matching keyword
        predicate = new FindPositionCommandPredicate(Arrays.asList("Carol"));
        assertFalse(predicate.test(new PositionBuilder().withTitle("Alice Bob").build()));

        // Keywords match phone, email and address, but does not match name
        predicate = new FindPositionCommandPredicate(Arrays.asList("12345", "alice@email.com", "Main", "Street"));
        assertFalse(predicate.test(new PositionBuilder().withTitle("Alice").withStatus(Position.PositionStatus.OPEN)
                .build()));
    }

    @Test
    public void test_statusContainsKeywords_returnsTrue() {
        //find closed
        FindPositionCommandPredicate predicate = new FindPositionCommandPredicate(
                new ArrayList<>(), Collections.singletonList("closed"));
        assertTrue(predicate.test(new PositionBuilder()
                .withStatus(Position.PositionStatus.CLOSED).build()));

        //find open
        predicate = new FindPositionCommandPredicate(
                new ArrayList<>(), Collections.singletonList("open"));
        assertTrue(predicate.test(new PositionBuilder()
                .withStatus(Position.PositionStatus.OPEN).build()));
    }

    @Test
    public void test_statusContainsInvalidKeywords_returnsFalse() {
        FindPositionCommandPredicate predicate = new FindPositionCommandPredicate(
                new ArrayList<>(), Collections.singletonList("close"));
        assertFalse(predicate.test(new PositionBuilder()
                .withStatus(Position.PositionStatus.CLOSED).build()));

        predicate = new FindPositionCommandPredicate(
                new ArrayList<>(), Collections.singletonList("1"));
        assertFalse(predicate.test(new PositionBuilder()
                .withStatus(Position.PositionStatus.OPEN).build()));
    }
}
