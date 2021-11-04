package seedu.address.model.util;

import java.time.MonthDay;
import java.util.Comparator;
import java.util.Optional;

import seedu.address.model.person.Birthday;
import seedu.address.model.person.Person;

/**
 * Comparator for comparing people by birthday. When used with sorter, person with earlier birthdays comes
 * before person with later birthdays.
 */
public class PersonBirthdayComparator implements Comparator<Person> {
    @Override
    public int compare(Person o1, Person o2) {
        Optional<Birthday> o1Birthday = o1.getBirthday();
        Optional<Birthday> o2Birthday = o2.getBirthday();
        assert (o1Birthday.isPresent() && o2Birthday.isPresent());
        MonthDay md1 = MonthDay.from(o1Birthday.get().birthdate);
        MonthDay md2 = MonthDay.from(o2Birthday.get().birthdate);
        return md1.compareTo(md2);
    }
}
