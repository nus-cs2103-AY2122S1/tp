package seedu.address.model.comparator;

import seedu.address.model.person.Person;

import java.util.Comparator;

public class TagComparator implements Comparator<Person> {
    private final String comparator = "first tag";

    @Override
    public int compare(Person p1, Person p2) {
        if (p1.getFirstTag() == null) {
            if (p2.getFirstTag() == null) {
                return 0;
            }
            return 1;
        }
        if (p2.getFirstTag() == null) {
            return -1;
        }
        return p1.getFirstTag().tagName.compareTo(p2.getFirstTag().tagName);
    }

    @Override
    public String toString() {
        return this.comparator;
    }
}
