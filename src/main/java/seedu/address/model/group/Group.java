package seedu.address.model.group;

import seedu.address.model.util.Unique;

/**
 * Stub class right now, used to make commands.
 */
public class Group implements Unique<Group> {

    /**
     * Stub implementation, just allows all groups to be added.
     *
     * @param other Other group to check for similarity.
     * @return Whether the other group is the same as the current group.
     */
    @Override
    public boolean isSame(Group other) {
        return false;
    }
}
