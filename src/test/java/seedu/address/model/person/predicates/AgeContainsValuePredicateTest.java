package seedu.address.model.person.predicates;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.PersonBuilder;

public class AgeContainsValuePredicateTest {

    @Test
    public void equals() {
        List<String> firstPredicateKeywordList = Collections.singletonList("first");
        List<String> secondPredicateKeywordList = Arrays.asList("first", "second");

        AgeContainsValuePredicate firstPredicate = new AgeContainsValuePredicate(
                firstPredicateKeywordList, false
        );
        AgeContainsValuePredicate secondPredicate = new AgeContainsValuePredicate(
                secondPredicateKeywordList, false
        );
        AgeContainsValuePredicate thirdPredicate = new AgeContainsValuePredicate(
                secondPredicateKeywordList, true
        );



        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        AgeContainsValuePredicate firstPredicateCopy = new AgeContainsValuePredicate(
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
        AgeContainsValuePredicate predicate = new AgeContainsValuePredicate(
                Collections.singletonList("11"), false
        );
        assertTrue(predicate.test(new PersonBuilder().withAge("11").build()));

        // Multiple keywords
        predicate = new AgeContainsValuePredicate(Arrays.asList("11", "22"), false);
        assertTrue(predicate.test(new PersonBuilder().withAge("11").build()));
    }

    @Test
    public void test_addressDoesNotContainKeywords_returnsFalse() {
        // Zero keywords
        AgeContainsValuePredicate predicate = new AgeContainsValuePredicate(
                Collections.emptyList(), false
        );
        assertFalse(predicate.test(new PersonBuilder().withAge("11").build()));

        // Non-matching keyword
        predicate = new AgeContainsValuePredicate(Arrays.asList("12"), false);
        assertFalse(predicate.test(new PersonBuilder().withAge("11").build()));

        // Keywords match name, phone, email, but does not match age
        predicate = new AgeContainsValuePredicate(Arrays.asList(
                "Alice", "87654321", "alice@email.com"
        ), false);
        assertFalse(predicate.test(new PersonBuilder().withName("ALice").withPhone("87654321")
                .withEmail("alice@email.com").withAge("11").build()));
    }

    @Test
    public void test_addressContainsKeywordsIsFindAll() {
        // One keyword match -> true
        AgeContainsValuePredicate predicate = new AgeContainsValuePredicate(
                Collections.singletonList("11"), true
        );
        assertTrue(predicate.test(new PersonBuilder().withAge("11").build()));

        // One keyword no match -> false
        predicate = new AgeContainsValuePredicate(
                Collections.singletonList("12"), true
        );
        assertFalse(predicate.test(new PersonBuilder().withAge("11").build()));

        // Only one matching keyword -> false
        predicate = new AgeContainsValuePredicate(Arrays.asList("22", "11"), true);
        assertFalse(predicate.test(new PersonBuilder().withAge("22").build()));

        // Keywords match name, phone, email, but does not match age -> false
        predicate = new AgeContainsValuePredicate(Arrays.asList(
                "Alice", "87654321", "alice@email.com"
        ), true);
        assertFalse(predicate.test(new PersonBuilder().withName("ALice").withPhone("87654321")
                .withEmail("alice@email.com").withAge("11").build()));
    }
}
