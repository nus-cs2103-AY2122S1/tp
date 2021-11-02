package seedu.address.model.contact;

import java.util.Collections;
import java.util.Set;
import java.util.function.Predicate;

import seedu.address.model.tag.Tag;

/**
 * Tests that a {@code Contact}'s {@code CategoryCode, Rating} matches the specified {@code CategoryCode, Rating}.
 */
public class IsFilterablePredicate implements Predicate<Contact> {
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
    public boolean test(Contact contact) {
        CategoryCode category = contact.getCategoryCode();
        Rating contactRating = contact.getRating();
        Set<Tag> contactTags = contact.getTags();
        boolean isSameRating = contactRating.equals(rating);
        boolean isSameCategoryCodes = categoryCodes.contains(category);
        boolean isSameTags = !(Collections.disjoint(tags, contactTags));

        if (categoryCodes.isEmpty() && tags.isEmpty()) {
            return isSameRating;
        } else if (tags.isEmpty()) {
            return isSameRating && isSameCategoryCodes;
        } else if (categoryCodes.isEmpty()) {
            return isSameRating && isSameTags;
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
