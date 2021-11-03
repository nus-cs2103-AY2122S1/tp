package seedu.address.model.person;

import java.util.Collections;
import java.util.Set;
import java.util.function.Predicate;

import seedu.address.model.tag.Tag;

import static seedu.address.model.person.Rating.isEmptyRating;

/**
 * Tests that a {@code Person}'s {@code CategoryCode, Rating} matches the specified {@code CategoryCode, Rating}.
 */
public class IsFilterablePredicate implements Predicate<Person> {
    private final Set<CategoryCode> categoryCodes;
    private final Rating rating;
    private final Set<Tag> tags;

    /**
     * Constructs a Predicate object.
     * @param categoryCodes
     * @param rating
     */
    public IsFilterablePredicate(Set<CategoryCode> categoryCodes, Rating rating, Set<Tag> tags) {
        this.categoryCodes = categoryCodes;
        this.rating = rating;
        this.tags = tags;
    }

    @Override
    public boolean test(Person person) {
        CategoryCode category = person.getCategoryCode();
        Rating personRating = person.getRating();
        Set<Tag> personTags = person.getTags();
        boolean isSameRating = personRating.equals(rating);
        boolean isSameCategoryCodes = categoryCodes.contains(category);
        boolean isSameTags = !(Collections.disjoint(tags, personTags));
        boolean noRating = isEmptyRating(rating);

        if (categoryCodes.isEmpty() && tags.isEmpty()) {
            return isSameRating;
        } else if (tags.isEmpty() && noRating) {
            return isSameCategoryCodes;
        } else if (categoryCodes.isEmpty() && noRating) {
            return isSameTags;
        } else if (categoryCodes.isEmpty()) {
            return isSameRating && isSameTags;
        } else if (tags.isEmpty()) {
            return isSameRating && isSameCategoryCodes;
        } else if (noRating) {
            return isSameTags && isSameCategoryCodes;
        } else {
            return isSameRating && isSameCategoryCodes && isSameTags;
        }
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof IsFilterablePredicate// instanceof handles nulls
                && categoryCodes.equals(((IsFilterablePredicate) other).categoryCodes)
                && rating.equals(((IsFilterablePredicate) other).rating)
                && tags.equals(((IsFilterablePredicate) other).tags)); // state check
    }

}
