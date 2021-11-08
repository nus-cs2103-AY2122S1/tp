package seedu.address.model.person;

import java.util.function.Predicate;

/**
 * Tests that a {@code Person}'s {@code Name} matches any of the keywords given.
 */
public class FindABuddyPredicate implements Predicate<Person> {
    private static final int LIMIT = 5;

    private int count = 0;

    /**
     * Test if the input {@code person} matches the predicate.
     *
     * @param person to be tested against.
     * @return Boolean representation of whether the
     * person is the same as the predicate.
     */
    @Override
    public boolean test(Person person) {
        count++;
        return count <= LIMIT;
    }

    /**
     * Method to compare two FindABuddyPredicate objects.
     *
     * @param other is the object that is going to be compared
     *              to the FindABuddyPredicate object that called this method.
     * @return boolean representation of whether the FindABuddyPredicate
     * object is equal to the other object passed as parameter.
     */
    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        return (other instanceof FindABuddyPredicate); // state check
    }

}
