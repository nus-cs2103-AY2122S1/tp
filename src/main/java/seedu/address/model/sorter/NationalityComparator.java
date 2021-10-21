package seedu.address.model.sorter;

import seedu.address.model.person.Person;

import java.util.Comparator;

public class NationalityComparator implements Comparator<Person> {
    private final String comparator = "nationality";

    @Override
    public int compare(Person p1, Person p2) {
        return p1.getNationality().value.compareTo(p2.getNationality().value);
    }

    @Override
    public String toString() {
        return this.comparator;
    }
}
