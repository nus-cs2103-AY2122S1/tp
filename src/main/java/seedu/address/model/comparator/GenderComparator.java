package seedu.address.model.comparator;
import java.util.Comparator;

import seedu.address.model.person.Person;


public class GenderComparator implements Comparator<Person> {
    private final String comparator = "gender";

    @Override
    public int compare(Person p1, Person p2) {
        if (p1.getGender().toString().isEmpty()) {
            if (p2.getGender().toString().isEmpty()) {
                return 0;
            }
            return 1;
        }
        if (p2.getGender().toString().isEmpty()) {
            return -1;
        }
        return p1.getGender().toString().toLowerCase().compareTo(p2.getGender().toString().toLowerCase());
    }

    @Override
    public String toString() {
        return this.comparator;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof GenderComparator // instanceof handles nulls
                && comparator.equals(((GenderComparator) other).comparator)); // state check
    }
}
