package seedu.address.model.person;

import java.util.List;
import java.util.function.Predicate;

/**
 * Tests that a {@code Person}'s {@code Telegram} matches any of the keywords given.
 */
public class TelegramHandleContainsKeywordsPredicate implements Predicate<Person> {
    private final List<String> keywords;

    /**
     * Creates a TelegramHandleContainsKeywordsPredicate which contains
     * a list of {@code keywords}.
     *
     * @param keywords of Telegram handles.
     */
    public TelegramHandleContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    /**
     * Test if the input {@code person} matches the predicate
     * by comparing the Telegram handles.
     *
     * @param person to be tested against.
     * @return Boolean representation of whether the
     * person is the same as the predicate.
     */
    @Override
    public boolean test(Person person) {
        String telegramHandle = person.getTelegram().value.toLowerCase();
        return keywords.stream()
                .anyMatch(keyword -> telegramHandle.contains(keyword.toLowerCase()));
    }

    /**
     * Method to compare two TelegramHandleContainsKeywordsPredicate objects.
     *
     * @param other is the object that is going to be compared
     *              to the TelegramHandleContainsKeywordsPredicate object that called this method.
     * @return boolean representation of whether the TelegramHandleContainsKeywordsPredicate
     * object is equal to the other object passed as parameter.
     */
    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof TelegramHandleContainsKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((TelegramHandleContainsKeywordsPredicate) other).keywords)); // state check
    }
}
