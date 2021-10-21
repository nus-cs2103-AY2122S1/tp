package seedu.address.model.sorter;

import seedu.address.model.person.Person;

import java.util.Comparator;

public class GenderComparator implements Comparator<Person> {
    private final String comparator = "gender";

    @Override
    public int compare(Person p1, Person p2) {
        return p1.getGender().gender.compareTo(p2.getGender().gender);
    }

    @Override
    public String toString() {
        return this.comparator;
    }
}
