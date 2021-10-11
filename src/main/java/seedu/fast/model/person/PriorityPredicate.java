package seedu.fast.model.person;

import seedu.fast.model.tag.PriorityTag;
import seedu.fast.model.tag.Tag;

import java.util.Set;
import java.util.function.Predicate;
import java.util.List;

/**
 * Tests that a {@code Person}'s {@code PriorityTag} matches any of the priorities given.
 */
public class PriorityPredicate implements Predicate<Person> {
    public final List<String> tags;

    public PriorityPredicate(List<String> tags) {
        this.tags=tags;
    }

    @Override
    public boolean test(Person person) {
        Set<Tag> personTags = person.getTags();
        for (String tag:tags) {
            for (Tag personTag:personTags) {
                if (personTag.tagName.equals(getTagName(tag))) {
                    return true;
                } else {
                    continue;
                }
            }
        }
        return false;
    }

    private static String getTagName(String tag) {
        switch(tag) {
        case PriorityTag.LowPriority.TERM:
            return PriorityTag.LowPriority.NAME;
        case PriorityTag.MediumPriority.TERM:
            return PriorityTag.MediumPriority.NAME;
        case PriorityTag.HighPriority.TERM:
            return PriorityTag.HighPriority.NAME;
        default:
            return "";
        }
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof PriorityPredicate // instanceof handles nulls
                && tags.equals(((PriorityPredicate) other).tags)); // state check
    }
}
