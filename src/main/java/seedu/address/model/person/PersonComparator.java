package seedu.address.model.person;

import seedu.address.logic.parser.Prefix;

import java.util.Comparator;

/**
 * Compares Person Objects by the given {@code Prefix}
 */

public class PersonComparator implements Comparator<Person> {

    private final Prefix prefix;

    public PersonComparator (Prefix prefix) {
        this.prefix = prefix;
    }

    @Override
    public int compare(Person p1, Person p2) {
        System.out.print(prefix + " " + p1.getName() + " " + p2.getName() + " " + p1.compare(p2, prefix) + "\n");
        return p1.compare(p2, prefix);
    }
}
