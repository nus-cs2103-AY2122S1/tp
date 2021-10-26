package seedu.address.model.person;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.ALICE_IS_FAVOURITE;

import org.junit.jupiter.api.Test;

public class IsFavouritePredicateTest {

    @Test
    public void testNotFavourite() {
        IsFavouritePredicate expected = new IsFavouritePredicate(false);
        assertEquals(expected, new IsFavouritePredicate(ALICE_IS_FAVOURITE));
    }

    @Test
    public void testFavourite() {
        IsFavouritePredicate expected = new IsFavouritePredicate(true);
        ALICE.setIsFavourite();
        assertTrue(expected.test(ALICE));
    }
}
