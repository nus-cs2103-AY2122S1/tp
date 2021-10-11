package seedu.address.model.person;

import java.util.function.Predicate;

public class PersonAvailableOnDayPredicate implements Predicate<Person> {
    private final String day;

    public PersonAvailableOnDayPredicate(String day) {
        this.day = day;
    }

    @Override
    public boolean test(Person person) {
        return person.isAvailableOnDay(day);
    }
}
