package seedu.address.model.person;

import seedu.address.commons.util.StringUtil;

import java.util.List;

import static java.util.Objects.requireNonNull;

public class AddressContainsKeywordsPredicate extends AttributeContainsKeywordsPredicate {

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

    public boolean addressContainsWords(Person person, List<String> keywords) {
        requireNonNull(keywords);
        requireNonNull(person);

        String preppedName = person.getAddress().value.toLowerCase();
        return StringUtil.containsWordsInOrderIgnoreCase(preppedName, keywords);
    }
}
