package seedu.programmer.model.student;

import java.util.Optional;

import seedu.programmer.commons.util.CollectionUtil;

/**
 * Stores the details to query the students with. Each non-empty field value will be used to query
 * for the student from the list;
 */
public class QueryStudentDescriptor {
    private String name;
    private String studentId;
    private String classId;

    public QueryStudentDescriptor() {
    }

    /**
     * Returns true if at least one field is edited.
     */
    public boolean isAnyFieldToBeQueried() {
        return CollectionUtil.isAnyNonNull(name, studentId, classId);
    }

    public void setName(String name) {
        this.name = name;
    }

    public Optional<String> getName() {
        return Optional.ofNullable(name);
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public Optional<String> getStudentId() {
        return Optional.ofNullable(studentId);
    }

    public void setClassId(String classId) {
        this.classId = classId;
    }

    public Optional<String> getClassId() {
        return Optional.ofNullable(classId);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof QueryStudentDescriptor)) {
            return false;
        }

        // state check
        QueryStudentDescriptor e = (QueryStudentDescriptor) other;

        return getName().equals(e.getName())
                && getStudentId().equals(e.getStudentId())
                && getClassId().equals(e.getClassId());
    }
}
