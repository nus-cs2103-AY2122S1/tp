package seedu.address.model.student;

import java.util.function.Predicate;

public class IsStudentPredicate implements Predicate<Student> {
    private final Student student;

    public IsStudentPredicate(Student student) {
        this.student = student;
    }

    @Override
    public boolean test(Student student) {
        return this.student.isSameStudent(student);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
        || (other instanceof IsStudentPredicate) // instanceof handles nulls
        && student.equals(((IsStudentPredicate) other).student);
    }
}
