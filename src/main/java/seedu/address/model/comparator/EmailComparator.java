package seedu.address.model.comparator;
import java.util.Comparator;

import seedu.address.model.person.Person;

public class EmailComparator implements Comparator<Person> {
    private final String comparator = "email";

    @Override
    public int compare(Person p1, Person p2) {
        if (p1.getEmail().toString().isEmpty()) {
            if (p2.getEmail().toString().isEmpty()) {
                return 0;
            }
            return 1;
        }
        if (p2.getEmail().toString().isEmpty()) {
            return -1;
        }
        return p1.getEmail().toString().toLowerCase().compareTo(p2.getEmail().toString().toLowerCase());
    }

    @Override
    public String toString() {
        return this.comparator;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof EmailComparator // instanceof handles nulls
                && comparator.equals(((EmailComparator) other).comparator)); // state check
    }
}
