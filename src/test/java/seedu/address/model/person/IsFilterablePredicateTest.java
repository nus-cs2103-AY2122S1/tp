package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

import seedu.address.model.tag.Tag;
import seedu.address.testutil.PersonBuilder;

class IsFilterablePredicateTest {

    @Test
    public void equals() {
        Set<CategoryCode> firstCategoryCodeSet = Collections.singleton(new CategoryCode("att"));
        Set<CategoryCode> secondCategoryCodeSet = new HashSet<>(Arrays.asList(
                new CategoryCode("fnb"), new CategoryCode("att")));

        Rating firstRating = new Rating("2");
        Rating secondRating = new Rating("4");
        Set<Tag> firstTag = new HashSet<>(Arrays.asList(new Tag("outdoor")));
        Set<Tag> secondTag = Collections.emptySet();

        IsFilterablePredicate firstPredicate = new IsFilterablePredicate(firstCategoryCodeSet, firstRating, firstTag);
        IsFilterablePredicate secondPredicate = new IsFilterablePredicate(
                secondCategoryCodeSet, secondRating, secondTag);
        IsFilterablePredicate thirdPredicate = new IsFilterablePredicate(firstCategoryCodeSet, secondRating, firstTag);
        IsFilterablePredicate fourthPredicate = new IsFilterablePredicate(
                secondCategoryCodeSet, firstRating, secondTag);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        IsFilterablePredicate firstPredicateCopy = new IsFilterablePredicate(
                firstCategoryCodeSet, firstRating, firstTag);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different category codes -> returns false
        assertFalse(firstPredicate.equals(fourthPredicate));

        // different ratings -> returns false
        assertFalse(firstPredicate.equals(thirdPredicate));
    }

    @Test
    public void test_isInCategory_returnsTrue() {
        // One category code
        IsFilterablePredicate predicate = new IsFilterablePredicate(Collections.singleton(new CategoryCode("fnb")),
            new Rating("0"), Collections.emptySet());
        assertTrue(predicate.test(new PersonBuilder().withCategoryCode("fnb").withRating("0").build()));

        //Multiple category codes
        predicate = new IsFilterablePredicate(
                new HashSet<>(Arrays.asList(
                    new CategoryCode("att"), new CategoryCode("tpt"), new CategoryCode("acc"))),
                    new Rating("0"), Collections.emptySet());
        assertTrue(predicate.test(new PersonBuilder().withCategoryCode("att").withRating("0").build()));
        assertTrue(predicate.test(new PersonBuilder().withCategoryCode("tpt").withRating("0").build()));
        assertTrue(predicate.test(new PersonBuilder().withCategoryCode("acc").withRating("0").build()));
    }

    @Test
    public void test_isInCategory_returnsFalse() {
        //One category code
        IsFilterablePredicate predicate = new IsFilterablePredicate(Collections.singleton(new CategoryCode("fnb")),
            new Rating("0"), Collections.emptySet());
        assertFalse(predicate.test(new PersonBuilder().withCategoryCode("att").build()));

        //Multiple category codes
        predicate = new IsFilterablePredicate(new HashSet<>(Arrays.asList(
                new CategoryCode("acc"), new CategoryCode("tpt"))),
                new Rating("0"), Collections.emptySet());
        assertFalse(predicate.test(new PersonBuilder().withCategoryCode("fnb").withRating("0").build()));
        assertFalse(predicate.test(new PersonBuilder().withCategoryCode("att").withRating("0").build()));
    }

    @Test
    public void test_isRating_returnsTrue() {
        // No Rating initialises as 0
        IsFilterablePredicate predicate = new IsFilterablePredicate(Collections.singleton(new CategoryCode("fnb")),
            new Rating("0"), Collections.emptySet());
        assertTrue(predicate.test(new PersonBuilder().withCategoryCode("fnb").build()));

        // One category code
        predicate = new IsFilterablePredicate(Collections.singleton(new CategoryCode("fnb")),
            new Rating("5"), Collections.emptySet());
        assertTrue(predicate.test(new PersonBuilder().withCategoryCode("fnb").withRating("5").build()));

        //Multiple category codes
        predicate = new IsFilterablePredicate(
            new HashSet<>(Arrays.asList(
                new CategoryCode("att"), new CategoryCode("tpt"), new CategoryCode("acc"))),
            new Rating("5"), Collections.emptySet());
        assertTrue(predicate.test(new PersonBuilder().withCategoryCode("att").withRating("5").build()));
        assertTrue(predicate.test(new PersonBuilder().withCategoryCode("tpt").withRating("5").build()));
        assertTrue(predicate.test(new PersonBuilder().withCategoryCode("acc").withRating("5").build()));
    }

    @Test
    public void test_isRating_returnsFalse() {
        //One category code
        IsFilterablePredicate predicate = new IsFilterablePredicate(Collections.singleton(new CategoryCode("fnb")),
            new Rating("3"), Collections.emptySet());
        assertFalse(predicate.test(new PersonBuilder().withCategoryCode("fnb").withRating("2").build()));

        //Multiple category codes
        predicate = new IsFilterablePredicate(new HashSet<>(Arrays.asList(
            new CategoryCode("acc"), new CategoryCode("tpt"))),
            new Rating("3"), Collections.emptySet());
        assertFalse(predicate.test(new PersonBuilder().withCategoryCode("tpt").withRating("4").build()));
        assertFalse(predicate.test(new PersonBuilder().withCategoryCode("acc").withRating("5").build()));
    }
}
