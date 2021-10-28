package seedu.address.model.student;

import java.util.function.Predicate;

/**
 * Tests that a {@code Student}'s {@code GroupName} matches the given group name.
 */
public class GroupNamePredicate implements Predicate<Student> {
    private final ClassCode classCode;

    public GroupNamePredicate(ClassCode classCode) {
        this.classCode = classCode;
    }

    public ClassCode getClassCode() {
        return classCode;
    }

    @Override
    public boolean test(Student student) {
        return student.getClassCode().equals(classCode);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same instance
                || other instanceof GroupNamePredicate // instanceof handles null
                && classCode.equals(((GroupNamePredicate) other).getClassCode()); // state check
    }
}
