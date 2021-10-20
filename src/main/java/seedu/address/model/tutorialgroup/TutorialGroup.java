package seedu.address.model.tutorialgroup;

import java.util.Objects;

import seedu.address.model.student.ClassCode;

public class TutorialGroup {

    private final GroupName groupName;
    private final ClassCode classCode;
    private final GroupType groupType;

    /**
     * @param groupName GroupName of Tutorial Group.
     * @param classCode ClassCode of Tutorial Group.
     */
    public TutorialGroup(GroupName groupName, ClassCode classCode, GroupType groupType) {
        this.groupName = groupName;
        this.classCode = classCode;
        this.groupType = groupType;

    }

    public GroupName getGroupName() {
        return groupName;
    }

    public ClassCode getClassCode() {
        return classCode;
    }

    public GroupType getGroupType() {
        return groupType;
    }

    /**
     * Returns true if both tutorial classes have the same name.
     * This defines a weaker notion of equality between two tutorial groups.
     */
    public boolean isSameTutorialGroup(TutorialGroup otherGroup) {
        if (otherGroup == this) {
            return true;
        }

        return otherGroup != null
                && otherGroup.getGroupName().equals(getGroupName())
                && otherGroup.getClassCode().equals(getClassCode())
                && otherGroup.getGroupType().equals(getGroupType());

    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof TutorialGroup)) {
            return false;
        }

        TutorialGroup otherGroup = (TutorialGroup) other;
        return otherGroup.getGroupName().equals(getGroupName())
                && otherGroup.getClassCode().equals(getClassCode())
                && otherGroup.getGroupType().equals(getGroupType());
    }

    @Override
    public int hashCode() {
        return Objects.hash(groupName, classCode, groupType);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("name: ")
                .append(getGroupName())
                .append("; class: ")
                .append(getClassCode())
                .append("; type: ")
                .append(getGroupType());

        return builder.toString();
    }
}
