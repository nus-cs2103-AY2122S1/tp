package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.PersonBuilder;

class IsInCategoryPredicateTest {

    @Test
    public void equals() {
        Set<CategoryCode> firstCategoryCodeSet = Collections.singleton(new CategoryCode("att"));
        Set<CategoryCode> secondCategoryCodeSet = new HashSet<>(Arrays.asList(new CategoryCode("fnb"), new CategoryCode("att")));

        IsInCategoryPredicate firstPredicate = new IsInCategoryPredicate(firstCategoryCodeSet);
        IsInCategoryPredicate secondPredicate = new IsInCategoryPredicate(secondCategoryCodeSet);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        IsInCategoryPredicate firstPredicateCopy = new IsInCategoryPredicate(firstCategoryCodeSet);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different category codes -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_isInCategory_returnsTrue() {
        //One category code
        IsInCategoryPredicate predicate = new IsInCategoryPredicate(Collections.singleton(new CategoryCode("fnb")));
        assertTrue(predicate.test(new PersonBuilder().withCategoryCode("fnb").build()));

        //Multiple category codes
        predicate = new IsInCategoryPredicate(
                new HashSet<>(Arrays.asList(
                        new CategoryCode("att"), new CategoryCode("tpt"), new CategoryCode("acc"))));
        assertTrue(predicate.test(new PersonBuilder().withCategoryCode("att").build()));
        assertTrue(predicate.test(new PersonBuilder().withCategoryCode("tpt").build()));
        assertTrue(predicate.test(new PersonBuilder().withCategoryCode("acc").build()));
    }

    @Test
    public void test_isInCategory_returnsFalse() {
        //One category code
        IsInCategoryPredicate predicate = new IsInCategoryPredicate(Collections.singleton(new CategoryCode("fnb")));
        assertFalse(predicate.test(new PersonBuilder().withCategoryCode("att").build()));

        //Multiple category codes
        predicate = new IsInCategoryPredicate(new HashSet<>(Arrays.asList(new CategoryCode("acc"), new CategoryCode("tpt"))));
        assertFalse(predicate.test(new PersonBuilder().withCategoryCode("fnb").build()));
        assertFalse(predicate.test(new PersonBuilder().withCategoryCode("att").build()));
    }

}
