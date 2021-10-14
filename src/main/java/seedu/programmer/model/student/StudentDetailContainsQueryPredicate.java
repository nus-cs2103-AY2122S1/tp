package seedu.programmer.model.student;

import java.util.function.Predicate;

/**
 * Tests that a {@code Student}'s {@code Name}, {@code ClassId}, {@code StudentId} matches any of the query given.
 */

public class StudentDetailContainsQueryPredicate implements Predicate<Student> {
    private final QueryStudentDescriptor queryFields;

    public StudentDetailContainsQueryPredicate(QueryStudentDescriptor queryFields) {
        this.queryFields = queryFields;
    }

    @Override
    public boolean test(Student student) {
        return queryFields.doesStudentMatchDescriptor(student);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof StudentDetailContainsQueryPredicate // instanceof handles nulls
                && queryFields.equals(((StudentDetailContainsQueryPredicate) other).queryFields)); // state check
    }
}
