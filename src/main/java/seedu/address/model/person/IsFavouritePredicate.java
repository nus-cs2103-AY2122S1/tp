package seedu.address.model.person;

import java.util.function.Predicate;

/**
 * Tests that a {@code Person}'s {@code isFavourite} is true.
 */
public class IsFavouritePredicate implements Predicate<Person> {

    @Override
    public boolean test(Person person) {
        return person.getIsFavourite();
    }
}
