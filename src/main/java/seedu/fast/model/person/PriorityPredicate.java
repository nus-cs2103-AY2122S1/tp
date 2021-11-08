package seedu.fast.model.person;

import java.util.List;
import java.util.Set;
import java.util.function.Predicate;

import seedu.fast.model.tag.PriorityTag;
import seedu.fast.model.tag.Tag;

/**
 * Tests that a {@code Person}'s {@code PriorityTag} matches any of the priorities given.
 */
public class PriorityPredicate implements Predicate<Person> {
    public final List<String> tags;

    public PriorityPredicate(List<String> tags) {
        this.tags = tags;
    }

    /**
     * Tests to see if a Person has the PriorityTags that are being searched for.
     * @param person The Person being tested.
     * @return true if person contains the desired tags, false otherwise.
     */
    @Override
    public boolean test(Person person) {
        Set<Tag> personsTags = person.getTags();
        for (String predicateTag:tags) {
            String predicateTagName = getTagName(predicateTag);
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
