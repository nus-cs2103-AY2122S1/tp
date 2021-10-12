package seedu.address.model.person.comparators;

import seedu.address.model.person.Person;

import java.util.Comparator;

import static java.util.Objects.requireNonNull;

public class SortByDisposableIncome implements Comparator<Person>  {

    private SortDirection direction;

    public SortByDisposableIncome(SortDirection direction) {
        requireNonNull(direction);
        this.direction = direction;
    }

    @Override
    public int compare(Person a, Person b) {
        //cause i need the sort direction here, i implement compareTo in riskAppetite
        //cause if not i need double check here for empty values
        //wrt person a
        if (a.getDisposableIncome().value.isEmpty()) {
            return 1;
        } else {
            if (b.getDisposableIncome().value.isEmpty()) {
                return -1;
            }

            int result = a.getDisposableIncome().value.compareTo(b.getDisposableIncome().value);
            return direction.isAscending() ? result : -result;
        }
    }

}
