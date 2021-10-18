package seedu.fast.model.person;

import java.util.List;
import java.util.Set;
import java.util.function.Predicate;

import seedu.fast.model.tag.PriorityTag;
import seedu.fast.model.tag.Tag;

/**
 * Tests that a {@code Person}'s {@code Tag} matches any of the tag names given.
 */
public class TagContainsKeyWordsPredicate implements Predicate<Person> {
    public final List<String> tags;

    public TagContainsKeyWordsPredicate(List<String> tags){
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
            for (Tag personsTag:personsTags) {
                if (personsTag.tagName.equals(predicateTagName)) {
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
                || (other instanceof TagContainsKeyWordsPredicate // instanceof handles nulls
                && tags.equals(((TagContainsKeyWordsPredicate) other).tags)); // state check
    }
}
