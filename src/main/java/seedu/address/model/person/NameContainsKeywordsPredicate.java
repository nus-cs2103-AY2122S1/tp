package seedu.address.model.person;

import seedu.address.commons.util.StringUtil;

import java.util.List;

import static java.util.Objects.requireNonNull;

/**
 * Tests that a {@code Person}'s {@code Name} matches any of the keywords given.
 */
public class NameContainsKeywordsPredicate extends AttributeContainsKeywordsPredicate {

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

    public boolean nameContainsWords(Person person, List<String> keywords) {
        requireNonNull(keywords);
        requireNonNull(person);

        String preppedName = person.getName().fullName.toLowerCase();
        return StringUtil.containsWordsInOrderIgnoreCase(preppedName, keywords);
    }
}
