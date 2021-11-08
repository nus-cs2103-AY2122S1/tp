package seedu.address.model.util;

import java.util.Comparator;

import seedu.address.model.person.Person;

/*
 * Represents the comparators that can be used for sorting.
 */
public class SortUtil {
    public static final Comparator<Person> SORT_BY_NAME = Comparator.comparing(i -> i.getName().toString());
    public static final Comparator<Person> SORT_BY_LAST_VISIT =
            Comparator.comparing(i -> i.getLastVisit().get().getDateTime());
    public static final Comparator<Person> SORT_BY_NEXT_VISIT =
            Comparator.comparing(i -> i.getVisit().get().getDateTime());
}
