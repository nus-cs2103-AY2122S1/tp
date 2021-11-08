package seedu.address.model.person;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.address.commons.util.StringUtil;

/**
 * Tests that a {@code Person}'s {@code Address} matches any of the keywords given.
 */
public class AddressContainsKeywordsPredicate extends AttributeContainsKeywordsPredicate {

    /**
     * Constructor for an AddressContainsKeywordsPredicate.
     *
     * @param keywords The {@code List<String>} that contains keywords used to find a person.
     */
    public AddressContainsKeywordsPredicate(List<String> keywords) {
        super(keywords);
    }

    @Override
    public boolean test(Person person) {
        return addressContainsWords(person, keywords);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddressContainsKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((AddressContainsKeywordsPredicate) other).keywords)); // state check
    }

    /**
     * Checks if a person's address contains the specified keywords.
     *
     * @param person A {@code Person} whose address might match the keywords.
     * @param keywords A {@code List<String>} that might contain words that match the address of the person.
     * @throws java.lang.NullPointerException if person or keywords is null
     */
    public boolean addressContainsWords(Person person, List<String> keywords) {
        requireNonNull(keywords);
        requireNonNull(person);

        String preppedName = person.getAddress().value.toLowerCase();
        return StringUtil.containsWordsInOrderIgnoreCase(preppedName, keywords);
    }
}
