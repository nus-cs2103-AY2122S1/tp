package seedu.address.model.student;

import java.util.function.Predicate;

/**
 * Tests that a {@code Student}'s {@code Classcode} matches the given classcode.
 */
public class ClassMemberPredicate implements Predicate<Student> {
    private final ClassCode classCode;

    public ClassMemberPredicate(ClassCode classCode) {
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
                || other instanceof ClassMemberPredicate // instanceof handles null
                && classCode.equals(((ClassMemberPredicate) other).getClassCode()); // state check
    }
}
