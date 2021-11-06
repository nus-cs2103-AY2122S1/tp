package seedu.address.model.person;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
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
    public void test_validInputFalse_success() {
        IsFavouritePredicate expected = new IsFavouritePredicate(false);
        boolean test = expected.test(ALICE);
        assertTrue(test);
    }

    @Test
    public void test_validInputTrue_success() {
        IsFavouritePredicate expected = new IsFavouritePredicate(true);
        ALICE.setIsFavourite();
        assertTrue(expected.test(ALICE));
    }

    @Test
    public void equals_twoSameObjects_success() {
        IsFavouritePredicate predicate = new IsFavouritePredicate(false);
        assertTrue(predicate.equals(predicate));
    }

    @Test
    public void equals_twoDifferentObjects_falseOutput() {
        IsFavouritePredicate predicate = new IsFavouritePredicate(false);
        Email email = new Email("jay@gmail.com");
        assertFalse(predicate.equals(email));
    }

    @Test
    public void equals_twoDifferentObjectsWithSameBooleanValues_success() {
        IsFavouritePredicate predicate1 = new IsFavouritePredicate(false);
        IsFavouritePredicate predicate2 = new IsFavouritePredicate(false);
        assertTrue(predicate1.equals(predicate2));
    }

    @Test
    public void equals_twoDifferentObjectsWithDifferentBooleanValues_falseOutput() {
        IsFavouritePredicate predicate1 = new IsFavouritePredicate(false);
        IsFavouritePredicate predicate2 = new IsFavouritePredicate(true);
        assertFalse(predicate1.equals(predicate2));
    }
}
