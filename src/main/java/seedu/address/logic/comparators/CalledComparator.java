package seedu.address.logic.comparators;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Comparator;

import seedu.address.model.person.Person;

/**
 * A comparator that compares Persons based on whether they are called.
 */
public class CalledComparator implements Comparator<Person> {
    // Used for sorting based on whether person is called
    // Person who isn't called will be shown first
    @Override
    public int compare(Person a, Person b) {
        requireAllNonNull(a, b);
        if (a.getIsCalled().value == b.getIsCalled().value) {
            return 0;
        }
        return a.getIsCalled().value ? 1 : -1;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof CalledComparator); // instanceof handles nulls
    }
}
