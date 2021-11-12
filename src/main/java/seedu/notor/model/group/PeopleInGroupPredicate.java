package seedu.notor.model.group;

import static java.util.Objects.requireNonNull;

import java.util.HashMap;
import java.util.function.Predicate;

import seedu.notor.model.person.Person;

/**
 * Returns true if person is in group.
 */
public class PeopleInGroupPredicate implements Predicate<Person> {
    private final HashMap<String, Person> peopleInGroup;

    /**
     * Constructor for the name finding predicate
     *
     * @param group A parameter that might be a substring to search the name for
     */
    public PeopleInGroupPredicate(Group group) {
        requireNonNull(group);
        this.peopleInGroup = group.getPeople();
    }

    @Override
    public boolean test(Person person) {
        return peopleInGroup.containsKey(person.getName().toString());
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof PeopleInGroupPredicate // instanceof handles nulls
                && peopleInGroup.equals(((PeopleInGroupPredicate) other).peopleInGroup)); // state check
    }

}
