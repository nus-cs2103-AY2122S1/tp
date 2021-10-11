package seedu.fast.commons.util.sort;

import java.util.Comparator;

import seedu.fast.model.person.Person;

public class SortByPriority implements Comparator<Person> {

    public static final String KEYWORD = "priority";

    @Override
    public int compare(Person p1, Person p2) {
        return p1.getPriority() - p2.getPriority();
    }
}
