package seedu.address.model.sorter;

import seedu.address.model.person.Person;

import java.util.Comparator;

public class TutorialGroupComparator implements Comparator<Person> {
    private final String comparator = "tutorial group";

    @Override
    public int compare(Person p1, Person p2) {
        return p1.getTutorialGroup().value.compareTo(p2.getTutorialGroup().value);
    }

    @Override
    public String toString() {
        return this.comparator;
    }
}
