package seedu.address.model.person.comparators;

import static java.util.Objects.requireNonNull;

import java.util.Comparator;

import seedu.address.model.person.Person;

public class SortByEmail implements Comparator<Person> {

    private SortDirection direction;

    /**
     * @param direction to sort by. Either asc or dsc.
     */
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
