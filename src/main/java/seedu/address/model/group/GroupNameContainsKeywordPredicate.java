package seedu.address.model.group;

import java.util.function.Predicate;

public class GroupNameContainsKeywordPredicate implements Predicate<Group> {
    private final GroupName groupName;

    public GroupNameContainsKeywordPredicate(GroupName groupName) {
        this.groupName = groupName;
    }

    @Override
    public boolean test(Group group) {
        return this.groupName.equals(group.getGroupName());
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
            || (other instanceof GroupNameContainsKeywordPredicate) // instanceof handles nulls
            && groupName.equals(((GroupNameContainsKeywordPredicate) other).groupName);
    }
}
