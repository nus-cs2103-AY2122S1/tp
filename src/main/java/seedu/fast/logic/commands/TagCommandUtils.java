package seedu.fast.logic.commands;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import seedu.fast.commons.util.TagUtil;
import seedu.fast.model.tag.PriorityTag;
import seedu.fast.model.tag.Tag;

public class TagCommandUtils {

    public static List<String> tagNames = new ArrayList<>();
    static{
        tagNames.add(PriorityTag.HighPriority.NAME);
        tagNames.add(PriorityTag.MediumPriority.NAME);
        tagNames.add(PriorityTag.LowPriority.NAME);
    };

    /**
     * Checks if a given Set of Tags contains multiple Priority tags.
     *
     * @param tags The Set of Tags to be checked.
     * @return A boolean indicating the result.
     */
    public static boolean hasMultiplePriorityTags(Set<Tag> tags) {
        int numberOfPriorityTags = 0;
        for (Tag tag : tags) {
            if (tag.getPriority() != TagUtil.NO_PRIORITY) {
                numberOfPriorityTags += 1;
            }
        }
        return numberOfPriorityTags > 1;
    }

}
