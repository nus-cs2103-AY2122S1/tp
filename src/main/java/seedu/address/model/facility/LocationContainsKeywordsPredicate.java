package seedu.address.model.facility;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;

/**
 * Tests that a {@code Facility}'s {@code Location} matches any of the keywords given.
 */
public class LocationContainsKeywordsPredicate implements Predicate<Facility> {
    private final List<String> keywords;

    public LocationContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Facility facility) {
        return keywords.stream()
                .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(facility.getLocation().location, keyword));
    }

    @Override
    public boolean equals(Object other) {
        return other == this
                || (other instanceof LocationContainsKeywordsPredicate
                && keywords.equals(((LocationContainsKeywordsPredicate) other).keywords));
    }
}
