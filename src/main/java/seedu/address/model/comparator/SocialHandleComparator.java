package seedu.address.model.comparator;

import seedu.address.model.person.Person;

import java.util.Comparator;

public class SocialHandleComparator implements Comparator<Person> {
    private final String comparator = "social handle";

    @Override
    public int compare(Person p1, Person p2) {
        if (p1.getSocialHandle() == null) {
            if (p2.getSocialHandle() == null) {
                return 0;
            }
            return 1;
        }
        if (p2.getSocialHandle() == null) {
            return -1;
        }
        return p1.getSocialHandle().value.compareTo(p2.getSocialHandle().value);
    }

    @Override
    public String toString() {
        return this.comparator;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof SocialHandleComparator // instanceof handles nulls
                && comparator.equals(((SocialHandleComparator) other).comparator)); // state check
    }
}
