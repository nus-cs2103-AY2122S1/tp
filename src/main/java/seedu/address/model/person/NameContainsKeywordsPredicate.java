package seedu.address.model.person;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.address.commons.util.StringUtil;

/**
 * Tests that a {@code Person}'s {@code Name} matches any of the keywords given.
 */
public class NameContainsKeywordsPredicate extends AttributeContainsKeywordsPredicate {

    /**
     * Constructor for a NameContainsKeywordsPredicate.
     *
     * @param keywords The {@code List<String>} that contains keywords used to find a person.
     */
    public NameContainsKeywordsPredicate(List<String> keywords) {
        super(keywords);
    }

    @Override
    public boolean test(Person person) {
        return nameContainsWords(person, keywords);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof NameContainsKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((NameContainsKeywordsPredicate) other).keywords)); // state check
    }

    /**
     * Checks if a person's name contains the specified keywords.
     *
     * @param person A {@code Person} whose name might match the keywords.
     * @param keywords A {@code List<String>} that might contain words that match the name of the person.
     * @throws java.lang.NullPointerException if person or keywords is null
     */
    public boolean nameContainsWords(Person person, List<String> keywords) {
        requireNonNull(keywords);
        requireNonNull(person);

        String preppedName = person.getName().fullName.toLowerCase();
        return StringUtil.containsWordsInOrderIgnoreCase(preppedName, keywords);
    }
}
