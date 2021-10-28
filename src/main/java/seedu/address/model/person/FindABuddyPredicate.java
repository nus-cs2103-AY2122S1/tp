package seedu.address.model.person;

import java.util.function.Predicate;

/**
 * Tests that a {@code Person}'s {@code Name} matches any of the keywords given.
 */
public class FindABuddyPredicate implements Predicate<Person> {
    private static final int LIMIT = 5;

    private int count = 0;

    @Override
    public boolean test(Person person) {
        count++;
        return count <= LIMIT;
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        return (other instanceof FindABuddyPredicate); // state check
    }

}
