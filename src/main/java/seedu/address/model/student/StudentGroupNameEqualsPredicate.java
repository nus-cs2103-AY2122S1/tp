package seedu.address.model.student;

import java.util.function.Predicate;

import seedu.address.model.group.GroupName;

public class StudentGroupNameEqualsPredicate implements Predicate<Student> {

    private final GroupName groupName;

    public StudentGroupNameEqualsPredicate(GroupName groupName) {
        this.groupName = groupName;
    }

    @Override
    public boolean test(Student student) {
        return groupName.equals(student.getGroupName());
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof StudentGroupNameEqualsPredicate // instanceof handles nulls
                && groupName.equals(((StudentGroupNameEqualsPredicate) other).groupName)); // state check
    }
}
