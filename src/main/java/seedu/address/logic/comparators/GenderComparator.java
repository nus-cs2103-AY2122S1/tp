package seedu.address.logic.comparators;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Comparator;

import seedu.address.model.person.Person;

/**
 * A comparator that compares Persons based on their genders.
 */
public class GenderComparator implements Comparator<Person> {
    // Used for sorting based on gender
    // Persons will be shown in this order: Female, Male, N.A
    @Override
    public int compare(Person a, Person b) {
        requireAllNonNull(a, b);
        if (a.getGender().value == "N.A") {
            return 1;
        }
        return a.getGender().value.compareTo(b.getGender().value);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof GenderComparator); // instanceof handles nulls
    }
}
