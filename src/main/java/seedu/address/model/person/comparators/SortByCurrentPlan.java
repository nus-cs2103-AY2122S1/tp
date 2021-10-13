package seedu.address.model.person.comparators;

import static java.util.Objects.requireNonNull;

import java.util.Comparator;

import seedu.address.model.person.Person;

public class SortByCurrentPlan implements Comparator<Person> {
    private SortDirection direction;

    /**
     * @param direction to sort by. Either asc or dsc.
     */
    public SortByCurrentPlan(SortDirection direction) {
        requireNonNull(direction);
        this.direction = direction;
    }

    @Override
    public int compare(Person a, Person b) {
        //cause i need the sort direction here, i implement compareTo in riskAppetite
        //cause if not i need double check here for empty values
        //wrt person a
        if (a.getCurrentPlan().value.isEmpty()) {
            return 1;
        } else {
            if (b.getCurrentPlan().value.isEmpty()) {
                return -1;
            }
            int result = a.getCurrentPlan().value.compareTo(b.getCurrentPlan().value);
            return direction.isAscending() ? result : -result;
        }
    }

}
