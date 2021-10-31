package seedu.edrecord.model.person;

import java.util.HashMap;

import seedu.edrecord.model.assignment.Assignment;
import seedu.edrecord.model.assignment.Grade;

/**
 * Represents a mapping of the student's {@code Assignment} to the corresponding {@code Grade}.
 */
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
     * @return A HashMap of {@code Assignment} and the corresponding {@code Grade}.
     */
    public HashMap<Assignment, Grade> getGrades() {
        return grades;
    }

    /**
     * @return Whether the HashMap is empty.
     */
    public boolean isEmpty() {
        return grades.isEmpty();
    }

    /**
     * @return Grade if it exists, null otherwise.
     */
    public Grade findGrade(Assignment assignment) {
        return grades.get(assignment);
    }

    /**
     * Removes a grade from the HashMap.
     */
    public void removeGrade(Assignment assignment) {
        grades.remove(assignment);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof AssignmentGradeMap)) {
            return false;
        }

        AssignmentGradeMap otherModuleSet = (AssignmentGradeMap) other;
        return this.grades.equals(otherModuleSet.getGrades());
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
