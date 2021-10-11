package seedu.address.model.person;

import java.util.List;
import java.util.function.Predicate;

/**
 * Tests that a {@code Person}'s {@code Telegram} matches any of the keywords given.
 */
public class TelegramHandleContainsKeywordsPredicate implements Predicate<Person> {
    private final List<String> keywords;

    public TelegramHandleContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Person person) {
        String telegramHandle = person.getTelegram().value.toLowerCase();
        return keywords.stream()
                .anyMatch(keyword -> telegramHandle.contains(keyword.toLowerCase()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof TelegramHandleContainsKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((TelegramHandleContainsKeywordsPredicate) other).keywords)); // state check
    }
}
