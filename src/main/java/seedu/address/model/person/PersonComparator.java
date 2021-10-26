package seedu.address.model.person;

import java.util.Comparator;

import seedu.address.logic.parser.Prefix;

/**
 * Compares Person Objects by the given {@code Prefix}
 */

public class PersonComparator implements Comparator<Person> {

    private final Prefix prefix;
    private final boolean reverse;

    /**
     * Creates PersonComparator Object with the given {@code Prefix} and reverses the comparison it if {@code boolean}
     * is true
     */
    public PersonComparator (Prefix prefix, boolean reverse) {
        this.prefix = prefix;
        this.reverse = reverse;
    }

    @Override
    public int compare(Person p1, Person p2) {
        if (reverse) {
            return p2.compare(p1, prefix);
        }
        return p1.compare(p2, prefix);
    }
}
