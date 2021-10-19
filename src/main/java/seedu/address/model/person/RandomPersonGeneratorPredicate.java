package seedu.address.model.person;

import java.util.Random;
import java.util.function.Predicate;

/**
 * Tests that a {@code Person}'s {@code Name} matches any of the keywords given.
 */
public class RandomPersonGeneratorPredicate implements Predicate<Person> {
    private static Random random = new Random();

    private int count = 0;

    @Override
    public boolean test(Person person) {
        if (count > 5) {
            return false;
        }
        boolean take = (random.nextInt(3) + 1) % 3 == 0;
        if (take) {
            count += 1;
        }
        return take;
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        return (other instanceof RandomPersonGeneratorPredicate); // state check
    }

}
