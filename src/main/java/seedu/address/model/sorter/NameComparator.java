package seedu.address.model.sorter;

import seedu.address.model.person.Person;

import java.util.Comparator;

public class NameComparator implements Comparator<Person> {
    private final String comparator = "name";

    @Override
    public int compare(Person p1, Person p2) {
        return p1.getName().fullName.compareTo(p2.getName().fullName);
    }

    @Override
    public String toString() {
        return this.comparator;
    }
}
