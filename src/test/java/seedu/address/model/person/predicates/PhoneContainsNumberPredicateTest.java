package seedu.address.model.person.predicates;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.PersonBuilder;

public class PhoneContainsNumberPredicateTest {

    @Test
    public void equals() {
        List<String> firstPredicateKeywordList = Collections.singletonList("first");
        List<String> secondPredicateKeywordList = Arrays.asList("first", "second");

        PhoneContainsNumberPredicate firstPredicate = new PhoneContainsNumberPredicate(
                firstPredicateKeywordList, false
        );
        PhoneContainsNumberPredicate secondPredicate = new PhoneContainsNumberPredicate(
                secondPredicateKeywordList, false
        );
        PhoneContainsNumberPredicate thirdPredicate = new PhoneContainsNumberPredicate(
                secondPredicateKeywordList, true
        );



        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        PhoneContainsNumberPredicate firstPredicateCopy = new PhoneContainsNumberPredicate(
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
        PhoneContainsNumberPredicate predicate = new PhoneContainsNumberPredicate(
                Collections.singletonList("62353535"), false
        );
        assertTrue(predicate.test(new PersonBuilder().withPhone("62353535").build()));

        // Multiple keywords
        predicate = new PhoneContainsNumberPredicate(Arrays.asList("623535", "62353535"), false);
        assertTrue(predicate.test(new PersonBuilder().withPhone("62353535").build()));

        // Only one matching keyword
        predicate = new PhoneContainsNumberPredicate(Arrays.asList("62353535", "999"), false);
        assertTrue(predicate.test(new PersonBuilder().withPhone("62353535").build()));

        // Substring keyword
        predicate = new PhoneContainsNumberPredicate(Collections.singletonList("235"), false);
        assertTrue(predicate.test(new PersonBuilder().withPhone("62353535").build()));
    }

    @Test
    public void test_addressDoesNotContainKeywords_returnsFalse() {
        // Zero keywords
        PhoneContainsNumberPredicate predicate = new PhoneContainsNumberPredicate(
                Collections.emptyList(), false
        );
        assertFalse(predicate.test(new PersonBuilder().withPhone("62353535").build()));

        // Non-matching keyword
        predicate = new PhoneContainsNumberPredicate(Arrays.asList("Carol"), false);
        assertFalse(predicate.test(new PersonBuilder().withPhone("62353535").build()));

        // Keywords match name,email, but does not match phone
        predicate = new PhoneContainsNumberPredicate(Arrays.asList(
                "Alice", "alice@email.com"
        ), false);
        assertFalse(predicate.test(new PersonBuilder().withName("ALice").withPhone("62353535")
                .withEmail("alice@email.com").build()));
    }

    @Test
    public void test_addressContainsKeywordsIsFindAll() {
        // One keyword match -> true
        PhoneContainsNumberPredicate predicate = new PhoneContainsNumberPredicate(
                Collections.singletonList("62353535"), true
        );
        assertTrue(predicate.test(new PersonBuilder().withPhone("62353535").build()));

        // One keyword no match -> false
        predicate = new PhoneContainsNumberPredicate(
                Collections.singletonList("haha"), true
        );
        assertFalse(predicate.test(new PersonBuilder().withPhone("62353535").build()));

        // Multiple keywords all match -> true
        predicate = new PhoneContainsNumberPredicate(Arrays.asList("62353535", "3535"), true);
        assertTrue(predicate.test(new PersonBuilder().withPhone("62353535").build()));

        // Only one matching keyword -> false
        predicate = new PhoneContainsNumberPredicate(Arrays.asList("3535", "number"), true);
        assertFalse(predicate.test(new PersonBuilder().withPhone("62353535").build()));

        // Mixed-case keywords not all match -> false
        predicate = new PhoneContainsNumberPredicate(Arrays.asList("62353535", "bOB"), true);
        assertFalse(predicate.test(new PersonBuilder().withPhone("62353535").build()));

        // Keywords match name,email, but does not match phone -> false
        predicate = new PhoneContainsNumberPredicate(Arrays.asList(
                "Alice", "alice@email.com"
        ), true);
        assertFalse(predicate.test(new PersonBuilder().withName("ALice").withPhone("62353535")
                .withEmail("alice@email.com").build()));
    }
}

