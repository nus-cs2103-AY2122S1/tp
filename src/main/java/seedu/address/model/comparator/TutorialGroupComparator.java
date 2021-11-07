package seedu.address.model.comparator;
import java.util.Comparator;

import seedu.address.model.person.Person;

public class TutorialGroupComparator implements Comparator<Person> {
    private final String comparator = "tutorial group";

    @Override
    public int compare(Person p1, Person p2) {
        if (p1.getTutorialGroup().toString().isEmpty()) {
            if (p2.getTutorialGroup().toString().isEmpty()) {
                return 0;
            }
            return 1;
        }
        if (p2.getTutorialGroup().toString().isEmpty()) {
            return -1;
        }
        return p1.getTutorialGroup().toString().toLowerCase().compareTo(p2.getTutorialGroup().toString().toLowerCase());
    }

    @Override
    public String toString() {
        return this.comparator;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof TutorialGroupComparator // instanceof handles nulls
                && comparator.equals(((TutorialGroupComparator) other).comparator)); // state check
    }
}
