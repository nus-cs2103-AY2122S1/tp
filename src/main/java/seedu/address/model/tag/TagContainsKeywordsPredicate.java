package seedu.address.model.tag;

import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;
import seedu.address.model.person.Person;

/**
 * Tests that a {@code Person}'s {@code Tag}s matches any of the keywords given.
 */
public class TagContainsKeywordsPredicate implements Predicate<Person> {
    private final List<String> keywords;

    public TagContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    /**
     * Checks if keyword can be found in any tags within a set of tags.
     *
     * @param tags Set of tags to be tested.
     * @param keyword String keyword to be found.
     * @return Boolean indicating if keyword can be found in any tags within the tested set of tags.
     */
    private boolean testTags(Set<Tag> tags, String keyword) {
        boolean isKeywordInTags = false;
        Iterator<Tag> tagsIterator = tags.iterator();
        while (tagsIterator.hasNext() && !isKeywordInTags) {
            Tag currentTag = tagsIterator.next();
            isKeywordInTags = StringUtil.containsWordIgnoreCase(currentTag.tagName, keyword);
        }
        return isKeywordInTags;
    }

    @Override
    public boolean test(Person person) {
        return keywords.stream()
                .anyMatch(keyword -> testTags(person.getTags(), keyword));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof TagContainsKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((TagContainsKeywordsPredicate) other).keywords)); // state check
    }
}
