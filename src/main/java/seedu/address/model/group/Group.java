package seedu.address.model.group;

public class Group {

    private GroupName groupName;

    public Group(GroupName groupName) {
        this.groupName = groupName;
    }

    public GroupName getGroupName() {
        return groupName;
    }
}
