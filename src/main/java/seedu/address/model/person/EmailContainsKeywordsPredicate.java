package seedu.address.model.person;

import seedu.address.commons.util.StringUtil;

import java.util.List;

import static java.util.Objects.requireNonNull;

public class EmailContainsKeywordsPredicate extends AttributeContainsKeywordsPredicate {

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

    public boolean emailContainsWords(Person person, List<String> keywords) {
        requireNonNull(keywords);
        requireNonNull(person);

        String preppedName = person.getEmail().value.toLowerCase();
        return StringUtil.containsWordsInOrderIgnoreCase(preppedName, keywords);
    }
}
