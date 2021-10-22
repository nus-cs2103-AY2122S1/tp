package seedu.address.model.person;

import java.util.Comparator;

/**
 * Compares two members' names, in string lexicographically.
 */
public class PersonNameComparator implements Comparator<Person> {
    @Override
    public int compare(Person p1, Person p2) {
        String name1 = p1.getName().toString().toLowerCase();
        String name2 = p2.getName().toString().toLowerCase();
        return name1.compareTo(name2);
    }
}
