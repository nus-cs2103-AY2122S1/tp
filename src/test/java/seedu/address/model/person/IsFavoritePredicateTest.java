package seedu.address.model.person;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.ALICE_IS_FAVORITE;

import org.junit.jupiter.api.Test;

public class IsFavoritePredicateTest {

    @Test
    public void testNotFavorite() {
        IsFavoritePredicate expected = new IsFavoritePredicate(false);
        assertEquals(expected, new IsFavoritePredicate(ALICE_IS_FAVORITE));
    }

    @Test
    public void test_validInputTrue_success() {
        IsFavoritePredicate expected = new IsFavoritePredicate(true);
        ALICE.setIsFavorite();
        assertTrue(expected.test(ALICE));
    }

    @Test
    public void equals_twoSameObjects_success() {
        IsFavoritePredicate predicate = new IsFavoritePredicate(false);
        assertTrue(predicate.equals(predicate));
    }

    @Test
    public void equals_twoDifferentObjects_falseOutput() {
        IsFavoritePredicate predicate = new IsFavoritePredicate(false);
        Email email = new Email("jay@gmail.com");
        assertFalse(predicate.equals(email));
    }

    @Test
    public void equals_twoDifferentObjectsWithSameBooleanValues_success() {
        IsFavoritePredicate predicate1 = new IsFavoritePredicate(false);
        IsFavoritePredicate predicate2 = new IsFavoritePredicate(false);
        assertTrue(predicate1.equals(predicate2));
    }

    @Test
    public void equals_twoDifferentObjectsWithDifferentBooleanValues_falseOutput() {
        IsFavoritePredicate predicate1 = new IsFavoritePredicate(false);
        IsFavoritePredicate predicate2 = new IsFavoritePredicate(true);
        assertFalse(predicate1.equals(predicate2));
    }
}
