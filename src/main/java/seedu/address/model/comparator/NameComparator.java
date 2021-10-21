package seedu.address.model.comparator;

import seedu.address.model.person.Person;

import java.util.Comparator;

public class NameComparator implements Comparator<Person> {
    private final String comparator = "name";

    @Override
    public int compare(Person p1, Person p2) {
        if (p1.getName() == null) {
            if (p2.getName() == null) {
                return 0;
            }
            return 1;
        }
        if (p2.getName() == null) {
            return -1;
        }
        return p1.getName().fullName.compareTo(p2.getName().fullName);
    }

    @Override
    public String toString() {
        return this.comparator;
    }
}
