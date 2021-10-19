package seedu.address.model.person;

import seedu.address.commons.util.StringUtil;
import seedu.address.model.tag.Tag;

import java.util.List;

import static java.util.Objects.requireNonNull;

public class TagContainsKeywordsPredicate extends AttributeContainsKeywordsPredicate {
    public TagContainsKeywordsPredicate(List<String> keywords) {
        super(keywords);
    }

    @Override
    public boolean test(Person person) {
        return tagsContainWords(person, keywords);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof PhoneContainsKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((PhoneContainsKeywordsPredicate) other).keywords)); // state check
    }

    public boolean tagsContainWords(Person person, List<String> keywords) {
        requireNonNull(keywords);
        requireNonNull(person);

        for (Tag tag : person.getTags()) {
            if (StringUtil.containsWordsInOrderIgnoreCase(tag.tagName, keywords)) {
                return true;
            }
        }
        return false;
    }
}
