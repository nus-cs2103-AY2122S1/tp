package seedu.address.model.person.comparators;

import seedu.address.model.person.Person;

import java.util.Comparator;

import static java.util.Objects.requireNonNull;

public class SortByAddress implements Comparator<Person> {
    private SortDirection direction;

    public SortByAddress(SortDirection direction) {
        requireNonNull(direction);
        this.direction = direction;
    }

    @Override
    public int compare(Person a, Person b) {
        //cause i need the sort direction here, i implement compareTo in riskAppetite
        //cause if not i need double check here for empty values
        //wrt person a
        if (a.getAddress().value.isEmpty()) {
            return 1;
        } else {
            if (b.getAddress().value.isEmpty()) {
                return -1;
            }

            int result = a.getAddress().value.compareTo(b.getAddress().value);
            return direction.isAscending() ? result : -result;
        }
    }
}
