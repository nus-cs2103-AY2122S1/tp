package seedu.placebook.model.person;

import java.util.List;
import java.util.function.Predicate;

import seedu.placebook.commons.util.StringUtil;
import seedu.placebook.model.tag.Tag;

/**
 * Tests that a {@code Person}'s {@code Tags} matches any of the keywords given.
 */
public class PersonHasTagsPredicate implements Predicate<Person> {
    private final List<String> keywords;

    public PersonHasTagsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Person person) {
        Tag[] tagArray = new Tag[person.getTags().size()];
        tagArray = person.getTags().toArray(tagArray);
        for (Tag tag : tagArray) {
            String tagName = tag.getTagName();
            if (keywords.stream().anyMatch(keyword -> StringUtil.containsWordIgnoreCase(keyword, tagName))) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof PersonHasTagsPredicate // instanceof handles nulls
                && keywords.equals(((PersonHasTagsPredicate) other).keywords)); // state check
    }

}
