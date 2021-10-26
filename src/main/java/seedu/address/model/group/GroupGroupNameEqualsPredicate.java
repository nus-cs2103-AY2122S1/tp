package seedu.address.model.group;

import java.util.function.Predicate;

/**
 * Tests that a {@code Group}'s {@code GroupName} matches any of the keywords given.
 */
public class GroupGroupNameEqualsPredicate implements Predicate<Group> {
    private final GroupName groupName;

    public GroupGroupNameEqualsPredicate(GroupName groupName) {
        this.groupName = groupName;
    }

    @Override
    public boolean test(Group group) {
        return this.groupName.equals(group.getGroupName());
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof GroupGroupNameEqualsPredicate) // instanceof handles nulls
                && groupName.equals(((GroupGroupNameEqualsPredicate) other).groupName);
    }
}
