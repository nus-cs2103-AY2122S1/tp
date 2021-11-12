package seedu.address.model.person.predicates;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.PersonBuilder;

public class AddressContainsKeywordsPredicateTest {

    @Test
    public void equals() {
        List<String> firstPredicateKeywordList = Collections.singletonList("first");
        List<String> secondPredicateKeywordList = Arrays.asList("first", "second");

        AddressContainsKeywordsPredicate firstPredicate = new AddressContainsKeywordsPredicate(
                firstPredicateKeywordList, false
        );
        AddressContainsKeywordsPredicate secondPredicate = new AddressContainsKeywordsPredicate(
                secondPredicateKeywordList, false
        );
        AddressContainsKeywordsPredicate thirdPredicate = new AddressContainsKeywordsPredicate(
                secondPredicateKeywordList, true
        );



        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        AddressContainsKeywordsPredicate firstPredicateCopy = new AddressContainsKeywordsPredicate(
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
        AddressContainsKeywordsPredicate predicate = new AddressContainsKeywordsPredicate(
                Collections.singletonList("Alice"), false
        );
        assertTrue(predicate.test(new PersonBuilder().withAddress("Alice house").build()));

        // Multiple keywords
        predicate = new AddressContainsKeywordsPredicate(Arrays.asList("Alice", "house"), false);
        assertTrue(predicate.test(new PersonBuilder().withAddress("Alice house").build()));

        // Only one matching keyword
        predicate = new AddressContainsKeywordsPredicate(Arrays.asList("Bob", "house"), false);
        assertTrue(predicate.test(new PersonBuilder().withAddress("Alice house").build()));

        // Mixed-case keywords
        predicate = new AddressContainsKeywordsPredicate(Arrays.asList("aLIce", "hOuSe"), false);
        assertTrue(predicate.test(new PersonBuilder().withAddress("Alice house").build()));

        // Substring keyword
        predicate = new AddressContainsKeywordsPredicate(Collections.singletonList("aLi"), false);
        assertTrue(predicate.test(new PersonBuilder().withAddress("Alice house").build()));
    }

    @Test
    public void test_addressDoesNotContainKeywords_returnsFalse() {
        // Zero keywords
        AddressContainsKeywordsPredicate predicate = new AddressContainsKeywordsPredicate(
                Collections.emptyList(), false
        );
        assertFalse(predicate.test(new PersonBuilder().withAddress("Alice house").build()));

        // Non-matching keyword
        predicate = new AddressContainsKeywordsPredicate(Arrays.asList("Carol"), false);
        assertFalse(predicate.test(new PersonBuilder().withAddress("Alice house").build()));

        // Keywords match name, phone, email, but does not match address
        predicate = new AddressContainsKeywordsPredicate(Arrays.asList(
                "Alice", "87654321", "alice@email.com"
        ), false);
        assertFalse(predicate.test(new PersonBuilder().withName("ALice").withPhone("87654321")
                .withEmail("alice@email.com").withAddress("her house").build()));
    }

    @Test
    public void test_addressContainsKeywordsIsFindAll() {
        // One keyword match -> true
        AddressContainsKeywordsPredicate predicate = new AddressContainsKeywordsPredicate(
                Collections.singletonList("Alice"), true
        );
        assertTrue(predicate.test(new PersonBuilder().withAddress("Alice house").build()));

        // One keyword no match -> false
        predicate = new AddressContainsKeywordsPredicate(
                Collections.singletonList("haha"), true
        );
        assertFalse(predicate.test(new PersonBuilder().withAddress("Alice house").build()));

        // Multiple keywords all match -> true
        predicate = new AddressContainsKeywordsPredicate(Arrays.asList("Alice", "Bob"), true);
        assertTrue(predicate.test(new PersonBuilder().withAddress("Alice and Bob house").build()));

        // Only one matching keyword -> false
        predicate = new AddressContainsKeywordsPredicate(Arrays.asList("Bob", "Carol"), true);
        assertFalse(predicate.test(new PersonBuilder().withAddress("Alice Carol house").build()));

        // Mixed-case keywords all match -> true
        predicate = new AddressContainsKeywordsPredicate(Arrays.asList("aLIce", "bOB"), true);
        assertTrue(predicate.test(new PersonBuilder().withAddress("Alice Bob house").build()));

        // Mixed-case keywords not all match -> false
        predicate = new AddressContainsKeywordsPredicate(Arrays.asList("aLIce", "bOB"), true);
        assertFalse(predicate.test(new PersonBuilder().withAddress("Alice carol house").build()));

        // Keywords match name, phone, email, but does not match address -> false
        predicate = new AddressContainsKeywordsPredicate(Arrays.asList(
                "Alice", "87654321", "alice@email.com"
        ), true);
        assertFalse(predicate.test(new PersonBuilder().withName("ALice").withPhone("87654321")
                .withEmail("alice@email.com").withAddress("Alice house").build()));
    }
}
