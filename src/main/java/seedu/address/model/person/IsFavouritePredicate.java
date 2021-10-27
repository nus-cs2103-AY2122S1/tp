package seedu.address.model.person;

import java.util.function.Predicate;

public class IsFavouritePredicate implements Predicate<Person> {
    private final boolean isFavourite;

    public IsFavouritePredicate(boolean isFavourite) {
        this.isFavourite = isFavourite;
    }

    @Override
    public boolean test(Person person) {
        return isFavourite == person.isFavourite();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof IsFavouritePredicate // instanceof handles nulls
                && isFavourite == ((IsFavouritePredicate) other).isFavourite); // state check
    }
}
