package seedu.address.model.person;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.address.commons.util.StringUtil;

/**
 * Tests that a {@code Person}'s {@code Phone} matches the keywords given.
 */
public class PhoneContainsKeywordsPredicate extends AttributeContainsKeywordsPredicate {

    /**
     * Constructor for a PhoneContainsKeywordsPredicate.
     *
     * @param keywords The {@code List<String>} that contains keywords used to find a person.
     */
    public PhoneContainsKeywordsPredicate(List<String> keywords) {
        super(keywords);
    }

    @Override
    public boolean test(Person person) {
        return phoneContainsWords(person, keywords);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof PhoneContainsKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((PhoneContainsKeywordsPredicate) other).keywords)); // state check
    }

    /**
     * Checks if a person's phone contains the specified keywords.
     *
     * @param person A {@code Person} whose phone might match the keywords.
     * @param keywords A {@code List<String>} that might contain words that match the phone of the person.
     * @throws java.lang.NullPointerException if person or keywords is null
     */
    public boolean phoneContainsWords(Person person, List<String> keywords) {
        requireNonNull(keywords);
        requireNonNull(person);

        String preppedPhone = person.getPhone().value.toLowerCase();
        return StringUtil.containsWordsInOrderIgnoreCase(preppedPhone, keywords);
    }
}
