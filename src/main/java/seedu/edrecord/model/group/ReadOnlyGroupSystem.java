package seedu.edrecord.model.group;

import java.util.List;

/**
 * Unmodifiable view of a group system
 */
public interface ReadOnlyGroupSystem {

    /**
     * Returns an unmodifiable view of the group list.
     * This list will not contain any duplicate groups.
     * @return the list of groups.
     */
    List<Group> getGroupList();

}
