package seedu.fast.logic.commands;

import java.util.Set;

import seedu.fast.model.tag.Tag;

public class TagCommandUtils {

    /**
     * Checks if a given Set of Tags contain a Tag that is equal to a given Tag.
     * Conditions for Tags to be equal: They are the same object, or they have the same Tag name.
     *
     * @param tags The Set of Tags to be checked.
     * @param newTag The tag to be compared with.
     * @return A boolean indicating the result.
     */
    public static boolean checkIfContains(Set<Tag> tags, Tag newTag) {
        for (Tag tag : tags) {
            if (tag.equals(newTag)) {
                return true;
            }
        }
        return false;
    }

}
