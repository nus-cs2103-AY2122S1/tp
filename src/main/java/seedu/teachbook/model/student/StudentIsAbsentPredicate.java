package seedu.teachbook.model.student;

import java.util.function.Predicate;

/**
 * Tests that a {@code Student}'s {@code Attendance} status is absent.
 */
public class StudentIsAbsentPredicate implements Predicate<Student> {

    @Override
    public boolean test(Student student) {
        return !student.isPresent();
    }

    @Override
    public boolean equals(Object other) {
        return (other instanceof StudentIsAbsentPredicate);
    }

}
