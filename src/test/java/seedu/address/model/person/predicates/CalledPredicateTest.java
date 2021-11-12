package seedu.address.model.person.predicates;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.PersonBuilder;

public class CalledPredicateTest {

    @Test
    public void equals() {
        List<String> firstPredicateKeywordList = Collections.singletonList("first");
        List<String> secondPredicateKeywordList = Arrays.asList("first", "second");

        CalledPredicate firstPredicate = new CalledPredicate(
                firstPredicateKeywordList, false
        );
        CalledPredicate secondPredicate = new CalledPredicate(
                secondPredicateKeywordList, false
        );
        CalledPredicate thirdPredicate = new CalledPredicate(
                secondPredicateKeywordList, true
        );

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        CalledPredicate firstPredicateCopy = new CalledPredicate(
                firstPredicateKeywordList, false
        );
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different person -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));

        // different isFindAll -> returns false
        assertFalse(secondPredicate.equals(thirdPredicate));
    }

    @Test
    public void test_addressContainsKeywords_returnsTrue() {
        // One keyword
        CalledPredicate predicate = new CalledPredicate(
                Collections.singletonList("false"), false
        );
        assertTrue(predicate.test(new PersonBuilder().withCalled("false").build()));

        predicate = new CalledPredicate(
                Collections.singletonList("f"), false
        );
        assertTrue(predicate.test(new PersonBuilder().withCalled("false").build()));

        predicate = new CalledPredicate(
                Collections.singletonList("true"), false
        );
        assertTrue(predicate.test(new PersonBuilder().withCalled("true").build()));

        predicate = new CalledPredicate(
                Collections.singletonList("t"), false
        );
        assertTrue(predicate.test(new PersonBuilder().withCalled("true").build()));

        // Multiple keywords
        predicate = new CalledPredicate(Arrays.asList("t", "f"), false);
        assertTrue(predicate.test(new PersonBuilder().withCalled("false").build()));

        // Mixed-case keywords
        predicate = new CalledPredicate(Arrays.asList("tRuE", "fAlSe"), false);
        assertTrue(predicate.test(new PersonBuilder().withCalled("false").build()));
    }

    @Test
    public void test_addressDoesNotContainKeywords_returnsFalse() {
        // Zero keywords
        CalledPredicate predicate = new CalledPredicate(
                Collections.emptyList(), false
        );
        assertFalse(predicate.test(new PersonBuilder().withCalled("true").build()));

        // Non-matching keyword
        predicate = new CalledPredicate(Arrays.asList("Carol"), false);
        assertFalse(predicate.test(new PersonBuilder().withCalled("false").build()));

        // Keywords match name, phone, email, but does not match isCalled
        predicate = new CalledPredicate(Arrays.asList(
                "Alice", "87654321", "alice@email.com"
        ), false);
        assertFalse(predicate.test(new PersonBuilder().withName("ALice").withPhone("87654321")
                .withEmail("alice@email.com").withCalled("true").build()));
    }

    @Test
    public void test_addressContainsKeywordsIsFindAll() {
        // One keyword match -> true
        CalledPredicate predicate = new CalledPredicate(
                Collections.singletonList("f"), true
        );
        assertTrue(predicate.test(new PersonBuilder().withCalled("false").build()));

        predicate = new CalledPredicate(
                Collections.singletonList("t"), true
        );
        assertTrue(predicate.test(new PersonBuilder().withCalled("true").build()));

        // One keyword no match -> false
        predicate = new CalledPredicate(
                Collections.singletonList("haha"), true
        );
        assertFalse(predicate.test(new PersonBuilder().withCalled("false").build()));

        // Only one matching keyword -> false
        predicate = new CalledPredicate(Arrays.asList("t", "f"), true);
        assertFalse(predicate.test(new PersonBuilder().withCalled("false").build()));

        // Keywords match name, phone, email, but does not match isCalled -> false
        predicate = new CalledPredicate(Arrays.asList(
                "Alice", "87654321", "alice@email.com"
        ), true);
        assertFalse(predicate.test(new PersonBuilder().withName("ALice").withPhone("87654321")
                .withEmail("alice@email.com").withCalled("false").build()));
    }
}
