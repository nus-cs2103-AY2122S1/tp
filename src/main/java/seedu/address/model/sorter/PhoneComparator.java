package seedu.address.model.sorter;

import seedu.address.model.person.Person;

import java.util.Comparator;

public class PhoneComparator implements Comparator<Person> {
    private final String comparator = "phone";

    @Override
    public int compare(Person p1, Person p2) {
        return p1.getPhone().value.compareTo(p2.getPhone().value);
    }

    @Override
    public String toString() {
        return this.comparator;
    }
}
