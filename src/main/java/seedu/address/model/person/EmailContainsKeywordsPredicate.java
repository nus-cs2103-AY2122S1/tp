package seedu.address.model.person;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.address.commons.util.StringUtil;

/**
 * Tests that a {@code Person}'s {@code Email} matches the keywords given.
 */
public class EmailContainsKeywordsPredicate extends AttributeContainsKeywordsPredicate {

    /**
     * Constructor for an EmailContainsKeywordsPredicate.
     *
     * @param keywords The {@code List<String>} that contains keywords used to find a person.
     */
    public EmailContainsKeywordsPredicate(List<String> keywords) {
        super(keywords);
    }

    @Override
    public boolean test(Person person) {
        return emailContainsWords(person, keywords);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof EmailContainsKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((EmailContainsKeywordsPredicate) other).keywords)); // state check
    }

    /**
     * Checks if a person's email contains the specified keywords.
     *
     * @param person A {@code Person} whose email might match the keywords.
     * @param keywords A {@code List<String>} that might contain words that match the email of the person.
     * @throws java.lang.NullPointerException if person or keywords is null
     */
    public boolean emailContainsWords(Person person, List<String> keywords) {
        requireNonNull(keywords);
        requireNonNull(person);

        String preppedName = person.getEmail().value.toLowerCase();
        return StringUtil.containsWordsInOrderIgnoreCase(preppedName, keywords);
    }
}
