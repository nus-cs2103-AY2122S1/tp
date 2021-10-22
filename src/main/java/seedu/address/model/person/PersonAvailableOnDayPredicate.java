package seedu.address.model.person;

import java.util.function.Predicate;

/**
 * Tests that a {@code Person} is available on the given day.
 */
public class PersonAvailableOnDayPredicate implements Predicate<Person> {
    private final int dayNumber;

    public PersonAvailableOnDayPredicate(int dayNumber) {
        this.dayNumber = dayNumber;
    }

    @Override
    public boolean test(Person person) {
        return person.isAvailableOnDay(dayNumber);
    }
}
