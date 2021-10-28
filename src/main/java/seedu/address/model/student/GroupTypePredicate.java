package seedu.address.model.student;

import java.util.function.Predicate;

import seedu.address.model.tutorialgroup.GroupType;

/**
 * Tests that a {@code Student}'s {@code GroupType} matches the given group type.
 */
public class GroupTypePredicate implements Predicate<Student> {
    private final GroupType groupType;

    public GroupTypePredicate(GroupType groupType) {
        this.groupType = groupType;
    }

    public GroupType getGroupType() {
        return groupType;
    }

    @Override
    public boolean test(Student student) {
        return student.getGroupType().equals(groupType);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same instance
                || other instanceof GroupTypePredicate // instanceof handles null
                && groupType.equals(((GroupTypePredicate) other).getGroupType()); // state check
    }
}
