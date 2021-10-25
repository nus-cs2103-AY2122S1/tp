package seedu.fast.model.person;

import java.util.List;
import java.util.Set;
import java.util.function.Predicate;

import seedu.fast.model.tag.Tag;

/**
 * Tests that a {@code Person}'s {@code Tag} matches any of the tag names given.
 */
public class TagMatchesKeywordPredicate implements Predicate<Person> {
    public final List<String> tags;

    public TagMatchesKeywordPredicate(List<String> tags) {
        this.tags = tags;
    }

    /**
     * Tests to see if a Person has the Tags that are being searched for.
     * @param person The Person being tested.
     * @return true if person contains the desired tags, false otherwise.
     */
    @Override
    public boolean test(Person person) {
        Set<Tag> personsTags = person.getTags();
        for (String predicateTagName:tags) {
            String predicateTagNameLowerCase = predicateTagName.toLowerCase();
            for (Tag personsTag:personsTags) {
                String personsTagLowerCase = personsTag.tagName.toLowerCase();
                // ignore case
                if (personsTagLowerCase.equals(predicateTagNameLowerCase)) {
                    return true;
                } else {
                    continue;
                }
            }
        }
        return false;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof TagMatchesKeywordPredicate // instanceof handles nulls
                && tags.equals(((TagMatchesKeywordPredicate) other).tags)); // state check
    }
}
