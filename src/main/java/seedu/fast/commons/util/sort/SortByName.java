package seedu.fast.commons.util.sort;

import java.util.Comparator;

import seedu.fast.model.person.Person;

public class SortByName implements Comparator<Person> {

    public static final String KEYWORD = "name";

    @Override
    public int compare(Person p1, Person p2) {
        return p1.getName().fullName.compareTo(p2.getName().fullName);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (other instanceof SortByName) {
            return true;
        }

        return false;
    }
}
