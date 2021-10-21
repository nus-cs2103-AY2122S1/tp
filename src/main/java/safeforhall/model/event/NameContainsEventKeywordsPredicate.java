package safeforhall.model.event;

import java.util.List;
import java.util.function.Predicate;

import safeforhall.commons.util.StringUtil;

/**
 * Tests that a {@code Event}'s {@code Name} matches any of the keywords given.
 */
public class NameContainsEventKeywordsPredicate implements Predicate<Event> {
    private final List<String> keywords;

    public NameContainsEventKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Event event) {
        return keywords.stream()
                .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(event.getEventName().eventName, keyword));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof NameContainsEventKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((NameContainsEventKeywordsPredicate) other).keywords)); // state check
    }

}
