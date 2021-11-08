package seedu.address.model.event;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.TypicalEvents.ALICE;
import static seedu.address.testutil.TypicalEvents.BOB;

import org.junit.jupiter.api.Test;

public class IsEventPredicateTest {

    @Test
    public void equals() {

        IsEventPredicate firstPredicate = new IsEventPredicate(ALICE);
        IsEventPredicate secondPredicate = new IsEventPredicate(BOB);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different event -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }
}
