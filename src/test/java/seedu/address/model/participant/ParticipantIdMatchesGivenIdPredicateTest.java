package seedu.address.model.participant;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.TypicalParticipants.ALEX;

import org.junit.jupiter.api.Test;


class ParticipantIdMatchesGivenIdPredicateTest {

    @Test
    public void test_idContainsGivenId_returnsTrue() {
        ParticipantIdMatchesGivenIdPredicate predicate =
                new ParticipantIdMatchesGivenIdPredicate("ALEyeo1");
        assertTrue(predicate.test(ALEX));
    }

    @Test
    public void test_idDoesNotContainGivenId_returnsFalse() {
        ParticipantIdMatchesGivenIdPredicate predicate =
                new ParticipantIdMatchesGivenIdPredicate("InvalidID");
        assertFalse(predicate.test(ALEX));
    }

    @Test
    public void equals() {
        ParticipantIdMatchesGivenIdPredicate firstPredicate =
                new ParticipantIdMatchesGivenIdPredicate("aleyeo1");
        ParticipantIdMatchesGivenIdPredicate secondPredicate =
                new ParticipantIdMatchesGivenIdPredicate("aleyeo2");

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        ParticipantIdMatchesGivenIdPredicate firstPredicateCopy =
                new ParticipantIdMatchesGivenIdPredicate("aleyeo1");
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different person -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }
}
