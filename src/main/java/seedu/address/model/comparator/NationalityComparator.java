package seedu.address.model.comparator;

import seedu.address.model.person.Person;

import java.util.Comparator;

public class NationalityComparator implements Comparator<Person> {
    private final String comparator = "nationality";

    @Override
    public int compare(Person p1, Person p2) {
        if (p1.getNationality() == null) {
            if (p2.getNationality() == null) {
                return 0;
            }
            return 1;
        }
        if (p2.getNationality() == null) {
            return -1;
        }
        return p1.getNationality().value.compareTo(p2.getNationality().value);
    }

    @Override
    public String toString() {
        return this.comparator;
    }
}
