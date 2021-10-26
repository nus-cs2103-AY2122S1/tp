package seedu.address.model.tutorialgroup;

import java.util.Objects;

import seedu.address.model.student.ClassCode;

public class TutorialGroup {

    private final GroupNumber groupNumber;
    private final ClassCode classCode;
    private final GroupType groupType;

    /**
     * @param groupNumber GroupName of Tutorial Group.
     * @param classCode ClassCode of Tutorial Group.
     */
    public TutorialGroup(GroupNumber groupNumber, ClassCode classCode, GroupType groupType) {
        this.groupNumber = groupNumber;
        this.classCode = classCode;
        this.groupType = groupType;

    }

    public GroupNumber getGroupNumber() {
        return groupNumber;
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
                && otherGroup.getGroupNumber().equals(getGroupNumber())
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
        return otherGroup.getGroupNumber().equals(getGroupNumber())
                && otherGroup.getClassCode().equals(getClassCode())
                && otherGroup.getGroupType().equals(getGroupType());
    }

    @Override
    public int hashCode() {
        return Objects.hash(groupNumber, classCode, groupType);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("name: ")
                .append(getGroupNumber())
                .append("; class: ")
                .append(getClassCode())
                .append("; type: ")
                .append(getGroupType());

        return builder.toString();
    }
}
