package seedu.address.model.contact;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

import seedu.address.model.tag.Tag;
import seedu.address.testutil.ContactBuilder;

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
        assertFalse(firstPredicate.equals(secondPredicate));

        // different ratings -> returns false
        assertFalse(firstPredicate.equals(thirdPredicate));
    }

    // Due to high number of all available combinations of inputs, only some test cases were selected to increase
    // the efficiency of testing. A combination of the 'at least once' and 'random' strategies were employed.

    @Test
    public void test_isInCategory_returnsTrue() {
        // Tests done with 1 rating and multiple tags

        // one category code
        IsFilterablePredicate predicate = new IsFilterablePredicate(
            Collections.singleton(new CategoryCode("att")),
            new Rating("2"), new HashSet<>(Arrays.asList(
            new Tag("fun"), new Tag("horrible"), new Tag("weekends"))));

        assertTrue(predicate.test(new ContactBuilder().withCategoryCode("att").withRating("2")
            .withTags("fun", "horrible", "weekends").build()));

        // multiple category codes
        predicate = new IsFilterablePredicate(
            new HashSet<>(Arrays.asList(new CategoryCode("att"), new CategoryCode("tpt"), new CategoryCode("oth"))),
            new Rating("2"), new HashSet<>(Arrays.asList(
            new Tag("fun"), new Tag("horrible"), new Tag("weekends"))));

        assertTrue(predicate.test(new ContactBuilder().withCategoryCode("att").withRating("2")
            .withTags("fun", "horrible", "weekends").build()));
        assertTrue(predicate.test(new ContactBuilder().withCategoryCode("tpt").withRating("2")
            .withTags("fun", "horrible", "weekends").build()));
        assertTrue(predicate.test(new ContactBuilder().withCategoryCode("oth").withRating("2")
            .withTags("fun", "horrible", "weekends").build()));
    }

    @Test
    public void test_isInCategory_returnsFalse() {
        // Tests done with 1 rating and no tags

        // One category code
        IsFilterablePredicate predicate = new IsFilterablePredicate(Collections.singleton(new CategoryCode("fnb")),
            new Rating("1"), Collections.emptySet());
        assertFalse(predicate.test(new ContactBuilder().withCategoryCode("att").withRating("1").build()));

        // Multiple category codes
        predicate = new IsFilterablePredicate(new HashSet<>(Arrays.asList(
            new CategoryCode("acc"), new CategoryCode("tpt"))),
            new Rating("1"), Collections.emptySet());
        assertFalse(predicate.test(new ContactBuilder().withCategoryCode("fnb").withRating("1").build()));
        assertFalse(predicate.test(new ContactBuilder().withCategoryCode("att").withRating("1").build()));
    }

    @Test
    public void test_isRating_returnsTrue() {
        // Tests done with multiple categories and no tags

        // No rating
        IsFilterablePredicate predicate = new IsFilterablePredicate(
            new HashSet<>(Arrays.asList(
                new CategoryCode("att"), new CategoryCode("tpt"), new CategoryCode("acc"))),
            new Rating(), Collections.emptySet());
        assertTrue(predicate.test(new ContactBuilder().withCategoryCode("att").withRating().withTags().build()));
        assertTrue(predicate.test(new ContactBuilder().withCategoryCode("tpt").withRating().withTags().build()));
        assertTrue(predicate.test(new ContactBuilder().withCategoryCode("acc").withRating().withTags().build()));

        // One rating
        predicate = new IsFilterablePredicate(
            new HashSet<>(Arrays.asList(
                new CategoryCode("att"), new CategoryCode("tpt"), new CategoryCode("acc"))),
            new Rating("3"), Collections.emptySet());
        assertTrue(predicate.test(new ContactBuilder().withCategoryCode("att").withRating("3").withTags().build()));
        assertTrue(predicate.test(new ContactBuilder().withCategoryCode("tpt").withRating("3").withTags().build()));
        assertTrue(predicate.test(new ContactBuilder().withCategoryCode("acc").withRating("3").withTags().build()));
    }

    @Test
    public void test_isRating_returnsFalse() {
        // Tests done with 1 category and multiple tags

        // No rating
        IsFilterablePredicate predicate = new IsFilterablePredicate(
            Collections.singleton(new CategoryCode("oth")), new Rating(), new HashSet<>(Arrays.asList(
            new Tag("fun"), new Tag("horrible"), new Tag("affordable"))));
        assertFalse(predicate.test(new ContactBuilder().withCategoryCode("oth").withRating("4")
            .withTags("fun", "horrible", "affordable").build()));
        assertFalse(predicate.test(new ContactBuilder().withCategoryCode("oth").withRating("1")
            .withTags("fun", "horrible", "affordable").build()));
        assertFalse(predicate.test(new ContactBuilder().withCategoryCode("oth").withRating("5")
            .withTags("fun", "horrible", "affordable").build()));

        // One rating
        predicate = new IsFilterablePredicate(
            Collections.singleton(new CategoryCode("oth")), new Rating("4"), new HashSet<>(Arrays.asList(
            new Tag("fun"), new Tag("horrible"), new Tag("affordable"))));
        assertFalse(predicate.test(new ContactBuilder().withCategoryCode("oth").withRating().withTags().build()));
        assertFalse(predicate.test(new ContactBuilder().withCategoryCode("oth").withRating("3").withTags().build()));
    }

    // TODO [LETHICIA]
    @Test
    public void test_isInTags_returnsTrue() {
        // Tests done with 1 rating and 1 category

        // no tags

        // one tag

        // multiple tags

    }

    @Test
    public void test_isInTags_returnsFalse() {
        // Tests done with no rating and 1 category

        // no tags

        // one tag

        // multiple tags

    }

}
