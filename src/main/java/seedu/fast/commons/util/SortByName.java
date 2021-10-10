package seedu.fast.commons.util;

import java.util.Comparator;

import seedu.fast.model.person.Person;

public class SortByName implements Comparator<Person> {

    public static final String KEYWORD = "name";

    @Override
    public int compare(Person o1, Person o2) {
        return o1.getName().fullName.compareTo(o2.getName().fullName);
    }
}
