package seedu.address.model.person;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;

/**
 * Tests that a {@code Person}'s {@code Name}, {@code Phone}, {@code Email} or
 * {@code Telegram Handle} matches any of the keywords given.
 */
public class MatchesKeywordsPredicate implements Predicate<Person> {
    private final List<String> keywords;

    public MatchesKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Person person) {
        return keywords.stream()
                .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(person.getName().toString(), keyword)
                        || StringUtil.containsWordIgnoreCase(person.getPhone().toString(), keyword)
                        || StringUtil.containsWordIgnoreCase(person.getEmail().toString(), keyword)
                        || StringUtil.containsWordIgnoreCase(person.getTelegram().toString(), keyword));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof MatchesKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((MatchesKeywordsPredicate) other).keywords)); // state check
    }

}
