package seedu.address.model.person.predicates;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.PersonBuilder;

public class GenderContainsKeywordPredicateTest {

    @Test
    public void equals() {
        List<String> firstPredicateKeywordList = Collections.singletonList("first");
        List<String> secondPredicateKeywordList = Arrays.asList("first", "second");

        GenderContainsKeywordPredicate firstPredicate = new GenderContainsKeywordPredicate(
                firstPredicateKeywordList, false
        );
        GenderContainsKeywordPredicate secondPredicate = new GenderContainsKeywordPredicate(
                secondPredicateKeywordList, false
        );
        GenderContainsKeywordPredicate thirdPredicate = new GenderContainsKeywordPredicate(
                secondPredicateKeywordList, true
        );



        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        GenderContainsKeywordPredicate firstPredicateCopy = new GenderContainsKeywordPredicate(
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
        GenderContainsKeywordPredicate predicate = new GenderContainsKeywordPredicate(
                Collections.singletonList("female"), false
        );
        assertTrue(predicate.test(new PersonBuilder().withGender("f").build()));

        predicate = new GenderContainsKeywordPredicate(
                Collections.singletonList("f"), false
        );
        assertTrue(predicate.test(new PersonBuilder().withGender("f").build()));

        predicate = new GenderContainsKeywordPredicate(
                Collections.singletonList("male"), false
        );
        assertTrue(predicate.test(new PersonBuilder().withGender("m").build()));
        predicate = new GenderContainsKeywordPredicate(
                Collections.singletonList("m"), false
        );
        assertTrue(predicate.test(new PersonBuilder().withGender("m").build()));

        predicate = new GenderContainsKeywordPredicate(
                Collections.singletonList("N.A"), false
        );
        assertTrue(predicate.test(new PersonBuilder().build()));

        // Only one matching keyword
        predicate = new GenderContainsKeywordPredicate(Arrays.asList("Bob", "f"), false);
        assertTrue(predicate.test(new PersonBuilder().withGender("f").build()));

        // Mixed-case keywords
        predicate = new GenderContainsKeywordPredicate(Arrays.asList("aLIce", "F"), false);
        assertTrue(predicate.test(new PersonBuilder().withGender("f").build()));
    }

    @Test
    public void test_addressDoesNotContainKeywords_returnsFalse() {
        // Zero keywords
        GenderContainsKeywordPredicate predicate = new GenderContainsKeywordPredicate(
                Collections.emptyList(), false
        );
        assertFalse(predicate.test(new PersonBuilder().withGender("f").build()));

        // Non-matching keyword
        predicate = new GenderContainsKeywordPredicate(Arrays.asList("Carol"), false);
        assertFalse(predicate.test(new PersonBuilder().withGender("f").build()));

        // Keywords match name, phone, email, but does not match gender
        predicate = new GenderContainsKeywordPredicate(Arrays.asList(
                "Alice", "87654321", "alice@email.com"
        ), false);
        assertFalse(predicate.test(new PersonBuilder().withName("ALice").withPhone("87654321")
                .withEmail("alice@email.com").withGender("f").build()));
    }

    @Test
    public void test_addressContainsKeywordsIsFindAll() {
        // One keyword match -> true
        GenderContainsKeywordPredicate predicate = new GenderContainsKeywordPredicate(
                Collections.singletonList("f"), true
        );
        assertTrue(predicate.test(new PersonBuilder().withGender("f").build()));

        // One keyword no match -> false
        predicate = new GenderContainsKeywordPredicate(
                Collections.singletonList("m"), true
        );
        assertFalse(predicate.test(new PersonBuilder().withGender("f").build()));

        // Multiple keywords all match -> true
        predicate = new GenderContainsKeywordPredicate(Arrays.asList("f", "f"), true);
        assertTrue(predicate.test(new PersonBuilder().withGender("f").build()));

        // Only one matching keyword -> false
        predicate = new GenderContainsKeywordPredicate(Arrays.asList("f", "m"), true);
        assertFalse(predicate.test(new PersonBuilder().withGender("m").build()));

        // Mixed-case keywords all match -> true
        predicate = new GenderContainsKeywordPredicate(Arrays.asList("m", "MaLe", "M"), true);
        assertTrue(predicate.test(new PersonBuilder().withGender("m").build()));

        // Mixed-case keywords not all match -> false
        predicate = new GenderContainsKeywordPredicate(Arrays.asList("m", "MaLe", "f"), true);
        assertFalse(predicate.test(new PersonBuilder().withGender("m").build()));

        // Keywords match name, phone, email, but does not match address -> false
        predicate = new GenderContainsKeywordPredicate(Arrays.asList(
                "Alice", "87654321", "alice@email.com"
        ), true);
        assertFalse(predicate.test(new PersonBuilder().withName("ALice").withPhone("87654321")
                .withEmail("alice@email.com").withGender("f").build()));
    }
}

