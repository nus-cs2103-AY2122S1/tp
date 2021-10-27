package seedu.edrecord.model.person;

import java.util.HashMap;

import seedu.edrecord.model.assignment.Assignment;
import seedu.edrecord.model.assignment.Grade;

public class AssignmentGradeMap {

    public static final String MESSAGE_CONSTRAINTS = "Assignment and Grade map must be separated by a colon.";

    private final HashMap<Assignment, Grade> grades = new HashMap<>();

    /**
     * Creates a new HashMap containing the student's respective grades for assignments.
     */
    public AssignmentGradeMap() {
    }

    public void add(Assignment assignment, Grade grade) {
        grades.put(assignment, grade);
    }

    public void addAll(AssignmentGradeMap grades) {
        this.grades.putAll(grades.getGrades());
    }

    /**
     * @return A HashMap of Module and their Group mapping.
     */
    public HashMap<Assignment, Grade> getGrades() {
        return grades;
    }

    /**
     * @return Whether the assignment grade map is empty.
     */
    public boolean isEmpty() {
        return grades.isEmpty();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof AssignmentGradeMap)) {
            return false;
        }

        AssignmentGradeMap otherModuleGroupMap = (AssignmentGradeMap) other;
        return this.grades.equals(otherModuleGroupMap.getGrades());
    }

    @Override
    public int hashCode() {
        return grades.hashCode();
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        grades.forEach((asg, grade) -> builder.append(asg).append(", ").append(grade).append("; "));
        return builder.toString();
    }
}
