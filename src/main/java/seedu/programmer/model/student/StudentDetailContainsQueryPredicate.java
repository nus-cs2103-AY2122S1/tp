package seedu.programmer.model.student;

import java.util.function.Predicate;

/**
 * Tests if a {@code Student}'s fields matches all of the query fields in {@code QueryStudentDescriptor}.
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
