package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.PersonBuilder;

public class FindABuddyPredicateTest {

    @Test
    public void equals() {
        FindABuddyPredicate firstPredicate = new FindABuddyPredicate();
        FindABuddyPredicate secondPredicate = new FindABuddyPredicate();

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // different object, same instance -> returns true
        assertTrue(firstPredicate.equals(secondPredicate));

        // different object -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));
    }

    @Test
    public void test_lessThanFive_returnsTrue() {
        FindABuddyPredicate predicate = new FindABuddyPredicate();
        for (int i = 0; i < 5; i++) {
            assertTrue(predicate.test(new PersonBuilder().withName("Alice").build()));
        }
    }

    @Test
    public void test_moreThanFive_returnsFalse() {
        FindABuddyPredicate predicate = new FindABuddyPredicate();
        for (int i = 0; i < 5; i++) {
            predicate.test(new PersonBuilder().withName("Alice").build());
        }
        for (int i = 0; i < 5; i++) {
            assertFalse(predicate.test(new PersonBuilder().withName("Alice").build()));
        }
    }

}
