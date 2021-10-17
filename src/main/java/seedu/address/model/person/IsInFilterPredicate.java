package seedu.address.model.person;

import java.util.Set;
import java.util.function.Predicate;

/**
 * Tests that a {@code Person}'s {@code CategoryCode, Rating} matches the specified {@code CategoryCode, Rating}.
 */
public class IsInFilterPredicate implements Predicate<Person> {
    private final Set<CategoryCode> categoryCodes;
    private final Rating rating;

    /**
     * Constructs a Predicate object.
     * @param categoryCodes
     * @param rating
     */
    public IsInFilterPredicate(Set<CategoryCode> categoryCodes, Rating rating) {
        this.categoryCodes = categoryCodes;
        this.rating = rating;
    }

    @Override
    public boolean test(Person person) {
        CategoryCode category = person.getCategoryCode();
        Rating personRating = person.getRating();
        boolean isSameRating = personRating.equals(rating);
        boolean isSameCategoryCodes = categoryCodes.contains(category);

        if (categoryCodes.isEmpty()) {
            return isSameRating;
        } else if (rating.equals(new Rating("0"))) {
            return isSameCategoryCodes;
        } else {
            return isSameRating && isSameCategoryCodes;
        }
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof IsInFilterPredicate// instanceof handles nulls
                && categoryCodes.equals(((IsInFilterPredicate) other).categoryCodes)
                && rating.equals(((IsInFilterPredicate) other).rating)); // state check
    }

}
