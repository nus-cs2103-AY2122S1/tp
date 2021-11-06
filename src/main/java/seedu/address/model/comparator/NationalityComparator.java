package seedu.address.model.comparator;
import java.util.Comparator;

import seedu.address.model.person.Person;


public class NationalityComparator implements Comparator<Person> {
    private final String comparator = "nationality";

    @Override
    public int compare(Person p1, Person p2) {
        if (p1.getNationality().toString().isEmpty()) {
            if (p2.getNationality().toString().isEmpty()) {
                return 0;
            }
            return 1;
        }
        if (p2.getNationality().toString().isEmpty()) {
            return -1;
        }
        return p1.getNationality().toString().toLowerCase().compareTo(p2.getNationality().toString().toLowerCase());
    }

    @Override
    public String toString() {
        return this.comparator;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof NationalityComparator // instanceof handles nulls
                && comparator.equals(((NationalityComparator) other).comparator)); // state check
    }
}
