package seedu.fast.logic.parser.sort;

import java.util.Comparator;

import seedu.fast.model.person.Person;

/**
 * Custom comparator that compares Person objects by their Tags.
 */
public class SortByPriority implements Comparator<Person> {

    public static final String KEYWORD = "priority";

    @Override
    public int compare(Person p1, Person p2) {
        return p1.getPriority() - p2.getPriority();
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (other instanceof SortByPriority) {
            return true;
        }

        return false;
    }
}
