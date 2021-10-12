package seedu.address.model.person.comparators;

import seedu.address.model.person.Person;

import java.util.Comparator;

import static java.util.Objects.requireNonNull;

public class SortByName implements Comparator<Person> {

    private SortDirection direction;

    public SortByName(SortDirection direction) {
        requireNonNull(direction);
        this.direction = direction;
    }

    @Override
    public int compare(Person a, Person b) {
        int result = a.getName().fullName.compareTo(b.getName().fullName);
        return direction.isAscending() ? result : -result;
    }
}
