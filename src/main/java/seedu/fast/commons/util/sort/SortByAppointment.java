package seedu.fast.commons.util.sort;

import java.util.Comparator;

import seedu.fast.model.person.Person;

public class SortByAppointment implements Comparator<Person> {

    public static final String KEYWORD = "appointment";

    @Override
    public int compare(Person p1, Person p2) {
        return p1.getAppointment().convertDate().compareTo(p2.getAppointment().convertDate());
    }
}
