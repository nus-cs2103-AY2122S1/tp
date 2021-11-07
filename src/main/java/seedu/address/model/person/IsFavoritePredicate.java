package seedu.address.model.person;

import java.util.function.Predicate;

public class IsFavoritePredicate implements Predicate<Person> {
    private final boolean isFavorite;

    public IsFavoritePredicate(boolean isFavorite) {
        this.isFavorite = isFavorite;
    }

    @Override
    public boolean test(Person person) {
        return isFavorite == person.isFavorite();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof IsFavoritePredicate // instanceof handles nulls
                && isFavorite == ((IsFavoritePredicate) other).isFavorite); // state check
    }
}
