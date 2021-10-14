package seedu.address.model.position;

import seedu.address.commons.util.StringUtil;
import seedu.address.model.person.Person;

import java.util.List;
import java.util.function.Predicate;

/**
 * Tests that a {@code Position}'s {@code Name} matches any of the keywords given.
 */
public class TitleContainsKeywordsPredicate implements Predicate<Position> {
    private final List<String> keywords;

    public TitleContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Position position) {
        return keywords.stream()
                .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(position.getTitle().fullTitle, keyword));
    }

}
