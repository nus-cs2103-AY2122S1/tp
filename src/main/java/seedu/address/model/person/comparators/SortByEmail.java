package seedu.address.model.person.comparators;

import seedu.address.model.person.Person;

import java.util.Comparator;

import static java.util.Objects.requireNonNull;

public class SortByEmail implements Comparator<Person> {

    private SortDirection direction;

    public SortByEmail(SortDirection direction) {
        requireNonNull(direction);
        this.direction = direction;
    }

    @Override
    public int compare(Person a, Person b) {
        int result = a.getEmail().value.compareTo(b.getEmail().value);
        return direction.isAscending() ? result : -result;
    }
}
