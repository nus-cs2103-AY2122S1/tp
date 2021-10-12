package seedu.programmer.model.student;

import java.util.Optional;
import java.util.function.Predicate;

import seedu.programmer.commons.util.StringUtil;
import seedu.programmer.logic.commands.ViewCommand;

/**
 * Tests that a {@code Student}'s {@code Name}, {@code ClassId}, {@code StudentId} matches any of the query given.
 */

public class StudentDetailContainsQueryPredicate implements Predicate<Student> {
    private final ViewCommand.QueryStudentDescriptor queryFields;

    public StudentDetailContainsQueryPredicate(ViewCommand.QueryStudentDescriptor queryFields) {
        this.queryFields = queryFields;
    }

    @Override
    public boolean test(Student student) {
        return testIfStudentMatchesQueryFields(student, queryFields);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof StudentDetailContainsQueryPredicate // instanceof handles nulls
                && queryFields.equals(((StudentDetailContainsQueryPredicate) other).queryFields)); // state check
    }

    /**
     * Tests if {@code student} fields matches that given in {@code queryFields}.
     * @param student to be tested to ascertain if the fields matches.
     * @param queryFields contains the respective fields to be checked against the student's.
     * @return true if all the non-null fields matches that of the {@code student} and false otherwise.
     */
    private boolean testIfStudentMatchesQueryFields (Student student, ViewCommand.QueryStudentDescriptor queryFields) {
        Optional<String> nameToBeQueried = queryFields.getName();
        Optional<String> sidToBeQueried = queryFields.getStudentId();
        Optional<String> cidToBeQueried = queryFields.getClassId();

        Name studentName = student.getName();
        StudentId studentSid = student.getStudentId();
        ClassId studentCid = student.getClassId();

        return checkOptionalContainsField(nameToBeQueried, studentName.fullName)
                && checkOptionalContainsField(sidToBeQueried, studentSid.studentId)
                && checkOptionalContainsField(cidToBeQueried, studentCid.classId);
    }

    /**
     * Checks if {@code str} character sequence matches any of the content of the {@code optional}.
     * @param optional to check against with.
     * @param str the char sequence to be checked if contained in the {@code optional}.
     * @return true if {@code str} is contained within {@code optional} and false otherwise.
     */
    private boolean checkOptionalContainsField(Optional<String> optional, String str) {
        if (!optional.isPresent()) {
            return true;
        }

        return StringUtil.containsPhraseIgnoreCase(str, optional.get());
    }
}
