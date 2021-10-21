package seedu.address.model.sorter;

import seedu.address.model.person.Person;

import java.util.Comparator;

public class RemarkComparator implements Comparator<Person> {
    private final String comparator = "remark";

    @Override
    public int compare(Person p1, Person p2) {
        return p1.getRemark().value.compareTo(p2.getRemark().value);
    }

    @Override
    public String toString() {
        return this.comparator;
    }
}
