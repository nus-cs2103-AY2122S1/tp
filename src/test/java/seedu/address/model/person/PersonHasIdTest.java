package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.PersonBuilder;

public class PersonHasIdTest {

    @Test
    public void equals() {
        String input1 = "1";
        String input2 = "2";
        ClientId clientA = new ClientId(input1);
        ClientId clientB = new ClientId(input2);

        PersonHasId firstPredicate = new PersonHasId(clientA);
        PersonHasId secondPredicate = new PersonHasId(clientB);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        PersonHasId firstPredicateCopy = new PersonHasId(clientA);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different person -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_clientIdMatches_returnsTrue() {
        // Same client id
        PersonHasId predicate = new PersonHasId(new ClientId("1"));
        assertTrue(predicate.test(new PersonBuilder().withClientId("1").build()));
    }

    @Test
    public void test_clientIdMatches_returnsFalse() {
        // different client id
        PersonHasId predicate = new PersonHasId(new ClientId("0"));
        assertFalse(predicate.test(new PersonBuilder().withClientId("1").build()));
    }
}
