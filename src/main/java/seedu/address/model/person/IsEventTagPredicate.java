package seedu.address.model.person;

import java.util.function.Predicate;

import seedu.address.model.tag.Tag;

/**
 * Tests that a {@code Person}'s {@code Tag} matches any of the keywords given.
 */
public class IsEventTagPredicate implements Predicate<Person> {

    @Override
    public boolean test(Person person) {
        return person.getTags().stream().anyMatch(Tag::isEvent);
    }

    /**
     * Method to compare two IsEventTagPredicate objects.
     *
     * @param other is the object that is going to be compared
     *              to the IsEventTagPredicate object that called this method.
     * @return boolean representation of whether the IsEventTagPredicate
     * object is equal to the other object passed as parameter.
     */
    @Override
    public boolean equals(Object other) {
        return other instanceof IsEventTagPredicate;
    }

}

