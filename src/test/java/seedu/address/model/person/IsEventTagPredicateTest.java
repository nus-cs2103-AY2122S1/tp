package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.PersonBuilder;

public class IsEventTagPredicateTest {

    @Test
    public void test_validInput_success() {
        IsEventTagPredicate predicate = new IsEventTagPredicate();
        boolean test = predicate.test(new PersonBuilder().withName("Jai")
                .withTags("CS2103T", "event-Hackathon").build());
        assertTrue(test);
    }

    @Test
    public void test_invalidInput_falseOutput() {
        IsEventTagPredicate predicate = new IsEventTagPredicate();
        boolean test = predicate.test(new PersonBuilder().withName("Jai").withTags("CS2100", "DanceClub").build());
        assertFalse(test);
    }

    @Test
    public void equals_twoSameObjects_success() {
        IsEventTagPredicate predicate = new IsEventTagPredicate();
        assertTrue(predicate.equals(predicate));
    }

    @Test
    public void equals_twoDifferentObjects_falseOutput() {
        IsEventTagPredicate eventTagPredicate = new IsEventTagPredicate();
        IsFavoritePredicate favouritePredicate = new IsFavoritePredicate(false);
        assertFalse(eventTagPredicate.equals(favouritePredicate));
    }

    @Test
    public void equals_twoDifferentObjectsOfSameType_success() {
        IsEventTagPredicate predicate1 = new IsEventTagPredicate();
        IsEventTagPredicate predicate2 = new IsEventTagPredicate();
        assertTrue(predicate1.equals(predicate2));
    }
}
