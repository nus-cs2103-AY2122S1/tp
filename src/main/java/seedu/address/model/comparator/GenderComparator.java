package seedu.address.model.comparator;

import seedu.address.model.person.Person;

import java.util.Comparator;

public class GenderComparator implements Comparator<Person> {
    private final String comparator = "gender";

    @Override
    public int compare(Person p1, Person p2) {
        if (p1.getGender() == null) {
            if (p2.getGender() == null) {
                return 0;
            }
            return 1;
        }
        if (p2.getGender() == null) {
            return -1;
        }
        return p1.getGender().gender.compareTo(p2.getGender().gender);
    }

    @Override
    public String toString() {
        return this.comparator;
    }
}
