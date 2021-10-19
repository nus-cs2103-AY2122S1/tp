package seedu.address.model.person;

import seedu.address.commons.util.StringUtil;

import java.util.List;

import static java.util.Objects.requireNonNull;

public class PhoneContainsKeywordsPredicate extends AttributeContainsKeywordsPredicate {
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

    public boolean phoneContainsWords(Person person, List<String> keywords) {
        requireNonNull(keywords);
        requireNonNull(person);

        String preppedPhone = person.getPhone().value.toLowerCase();
        return StringUtil.containsWordsInOrderIgnoreCase(preppedPhone, keywords);
    }
}
