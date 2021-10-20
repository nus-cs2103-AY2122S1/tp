package safeforhall.model.person;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static safeforhall.testutil.TypicalPersons.ALICE;

import org.junit.jupiter.api.Test;

import safeforhall.testutil.PersonBuilder;

public class NameMissedDeadlinePredicateTest {
    @Test
    public void test_nameWithinRangeFound_returnsTrue() {
        NameMissedDeadlinePredicate predicate = new NameMissedDeadlinePredicate("f", new LastDate("10-10-2021"));
        assertTrue(predicate.test(new PersonBuilder().withFet("02-10-2021").build()));

        predicate = new NameMissedDeadlinePredicate("c", new LastDate("10-10-2021"));
        assertTrue(predicate.test(new PersonBuilder().withCollection("02-10-2021").build()));

        predicate = new NameMissedDeadlinePredicate("f", new LastDate("10-10-2021"));
        assertTrue(predicate.test(new PersonBuilder().withFet("01-10-2021").build()));

        predicate = new NameMissedDeadlinePredicate("c", new LastDate("10-10-2021"));
        assertTrue(predicate.test(new PersonBuilder().withCollection("31-09-2021").build()));

        predicate = new NameMissedDeadlinePredicate("f", new LastDate("10-10-2021"));
        assertTrue(predicate.test(new PersonBuilder().withFet("30-09-2021").build()));
    }

    @Test
    public void test_nameWithinRangeNotFound_returnsFalse() {
        NameMissedDeadlinePredicate predicate = new NameMissedDeadlinePredicate("f", new LastDate("11-10-2021"));
        assertFalse(predicate.test(new PersonBuilder().withFet("10-10-2021").build()));

        predicate = new NameMissedDeadlinePredicate("c", new LastDate("11-10-2021"));
        assertFalse(predicate.test(new PersonBuilder().withCollection("10-10-2021").build()));

        predicate = new NameMissedDeadlinePredicate("f", new LastDate("10-10-2021"));
        assertFalse(predicate.test(new PersonBuilder().withFet("09-10-2021").build()));

        predicate = new NameMissedDeadlinePredicate("c", new LastDate("10-10-2021"));
        assertFalse(predicate.test(new PersonBuilder().withCollection("09-10-2021").build()));

        predicate = new NameMissedDeadlinePredicate("f", new LastDate("10-10-2021"));
        assertFalse(predicate.test(new PersonBuilder().withFet("14-10-2021").build()));

        predicate = new NameMissedDeadlinePredicate("f", new LastDate("21-10-2021"));
        assertFalse(predicate.test(new PersonBuilder().withFet("14-10-2021").build()));
    }

    @Test
    public void testGetDeadlinePeriod() {
        NameMissedDeadlinePredicate predicate = new NameMissedDeadlinePredicate("f", new LastDate("11-10-2021"));
        assertEquals(predicate.getDeadlinePeriod(ALICE), 1);

        predicate = new NameMissedDeadlinePredicate("c", new LastDate("11-10-2021"));
        assertEquals(predicate.getDeadlinePeriod(ALICE), 1);
    }
}
