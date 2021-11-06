package seedu.address.model.comparator;
import java.util.Comparator;

import seedu.address.model.person.Person;

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
        return p1.getFirstTag().getTagName().toLowerCase().compareTo(p2.getFirstTag().getTagName().toLowerCase());
    }

    @Override
    public String toString() {
        return this.comparator;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof TagComparator // instanceof handles nulls
                && comparator.equals(((TagComparator) other).comparator)); // state check
    }
}
