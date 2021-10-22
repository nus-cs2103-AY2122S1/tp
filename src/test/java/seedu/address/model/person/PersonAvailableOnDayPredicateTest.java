package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.DayOfWeek;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import seedu.address.testutil.PersonBuilder;

public class PersonAvailableOnDayPredicateTest {

    @Test
    @Disabled
    public void test_personAvailableOnDay_returnsTrue() {
        List<DayOfWeek> availability = Arrays.asList(DayOfWeek.MONDAY, DayOfWeek.FRIDAY);
        Person person = new PersonBuilder().withAvailability(availability).build();
        PersonAvailableOnDayPredicate predicate = new PersonAvailableOnDayPredicate(1);
        assertTrue(predicate.test(person));
    }

    @Test
    @Disabled
    public void test_personNotAvailableOnDay_returnsFalse() {
        List<DayOfWeek> availability = Arrays.asList(DayOfWeek.MONDAY, DayOfWeek.FRIDAY);
        Person person = new PersonBuilder().withAvailability(availability).build();
        PersonAvailableOnDayPredicate predicate = new PersonAvailableOnDayPredicate(3);
        assertFalse(predicate.test(person));

        PersonAvailableOnDayPredicate predicateSecond = new PersonAvailableOnDayPredicate(8);
        assertFalse(predicateSecond.test(person));
    }
}
