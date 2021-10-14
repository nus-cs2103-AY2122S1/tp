package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.PersonBuilder;

public class IsFavouritePredicateTest {
    @Test
    public void test_returnsTrue() {
        // One keyword
        IsFavouritePredicate predicate = new IsFavouritePredicate();
        assertTrue(predicate.test(new PersonBuilder().withFavourite(true).build()));
    }

    @Test
    public void test_returnsFalse() {
        // Zero keywords
        IsFavouritePredicate predicate = new IsFavouritePredicate();
        assertFalse(predicate.test(new PersonBuilder().withFavourite(false).build()));
    }
}
