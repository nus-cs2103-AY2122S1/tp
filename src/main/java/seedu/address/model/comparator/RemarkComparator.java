package seedu.address.model.comparator;
import java.util.Comparator;

import seedu.address.model.person.Person;

public class RemarkComparator implements Comparator<Person> {
    private final String comparator = "remark";

    @Override
    public int compare(Person p1, Person p2) {
        if (p1.getRemark() == null) {
            if (p2.getRemark() == null) {
                return 0;
            }
            return 1;
        }
        if (p2.getRemark() == null) {
            return -1;
        }
        return p1.getRemark().value.compareTo(p2.getRemark().value);
    }

    @Override
    public String toString() {
        return this.comparator;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof RemarkComparator // instanceof handles nulls
                && comparator.equals(((RemarkComparator) other).comparator)); // state check
    }
}
