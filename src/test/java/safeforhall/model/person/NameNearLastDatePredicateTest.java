package safeforhall.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import safeforhall.testutil.PersonBuilder;

public class NameNearLastDatePredicateTest {
    @Test
    public void test_nameWithinRangeFound_returnsTrue() {
        NameNearLastDatePredicate predicate = new NameNearLastDatePredicate("f", new LastDate("10-10-2021"));
        assertTrue(predicate.test(new PersonBuilder().withFet("03-10-2021").build()));

        predicate = new NameNearLastDatePredicate("c", new LastDate("10-10-2021"));
        assertTrue(predicate.test(new PersonBuilder().withCollection("03-10-2021").build()));

        predicate = new NameNearLastDatePredicate("f", new LastDate("10-10-2021"), new LastDate("13-10-2021"));
        assertTrue(predicate.test(new PersonBuilder().withFet("03-10-2021").build()));

        predicate = new NameNearLastDatePredicate("c", new LastDate("10-10-2021"), new LastDate("13-10-2021"));
        assertTrue(predicate.test(new PersonBuilder().withCollection("04-10-2021").build()));

        predicate = new NameNearLastDatePredicate("f", new LastDate("10-10-2021"), new LastDate("13-10-2021"));
        assertTrue(predicate.test(new PersonBuilder().withFet("05-10-2021").build()));

        predicate = new NameNearLastDatePredicate("c", new LastDate("10-10-2021"), new LastDate("13-10-2021"));
        assertTrue(predicate.test(new PersonBuilder().withCollection("06-10-2021").build()));
    }

    @Test
    public void test_nameWithinRangeNotFound_returnsFalse() {
        NameNearLastDatePredicate predicate = new NameNearLastDatePredicate("f", new LastDate("11-10-2021"));
        assertFalse(predicate.test(new PersonBuilder().withFet("10-09-2021").build()));

        predicate = new NameNearLastDatePredicate("c", new LastDate("11-10-2021"));
        assertFalse(predicate.test(new PersonBuilder().withCollection("10-09-2021").build()));

        predicate = new NameNearLastDatePredicate("f", new LastDate("10-10-2021"), new LastDate("13-10-2021"));
        assertFalse(predicate.test(new PersonBuilder().withFet("09-09-2021").build()));

        predicate = new NameNearLastDatePredicate("c", new LastDate("10-10-2021"), new LastDate("13-10-2021"));
        assertFalse(predicate.test(new PersonBuilder().withCollection("09-09-2021").build()));

        predicate = new NameNearLastDatePredicate("f", new LastDate("10-10-2021"), new LastDate("13-10-2021"));
        assertFalse(predicate.test(new PersonBuilder().withFet("14-09-2021").build()));

        predicate = new NameNearLastDatePredicate("c", new LastDate("10-10-2021"), new LastDate("13-10-2021"));
        assertFalse(predicate.test(new PersonBuilder().withCollection("14-09-2021").build()));
    }
}
