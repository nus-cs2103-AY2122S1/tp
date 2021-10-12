package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.PersonBuilder;

public class PersonAvailableOnDayPredicateTest {

    @Test
    public void test_personAvailableOnDay_returnsTrue() {
        Person person = new PersonBuilder().withAvailability("Mon Tues Fri").build();
        PersonAvailableOnDayPredicate predicate = new PersonAvailableOnDayPredicate("Mon");
        assertTrue(predicate.test(person));
    }

    @Test
    public void test_personNotAvailableOnDay_returnsFalse() {
        Person person = new PersonBuilder().withAvailability("Mon Tues Fri").build();
        PersonAvailableOnDayPredicate predicate = new PersonAvailableOnDayPredicate("Wed");
        assertFalse(predicate.test(person));

        PersonAvailableOnDayPredicate predicateSecond = new PersonAvailableOnDayPredicate("Mond");
        assertFalse(predicateSecond.test(person));
    }
}
