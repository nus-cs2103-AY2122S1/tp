package seedu.address.model.sorter;

import seedu.address.model.person.Person;

import java.util.Comparator;

public class SocialHandleComparator implements Comparator<Person> {
    private final String comparator = "social handle";

    @Override
    public int compare(Person p1, Person p2) {
        return p1.getSocialHandle().value.compareTo(p2.getSocialHandle().value);
    }

    @Override
    public String toString() {
        return this.comparator;
    }
}
