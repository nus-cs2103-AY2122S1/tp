package seedu.teachbook.model.gradeobject;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Represents a grading system in the TeachBook.
 */
public class GradingSystem {

    public static final Grade NOT_GRADED = new Grade("");

    private final List<Grade> gradeList;

    /**
     * Constructs a grading system with the specified list of grades.
     *
     * @param gradeList a list of {@code Grade} to be used in this grading system in the order of high to low.
     */
    public GradingSystem(List<Grade> gradeList) {
        this.gradeList = gradeList;
    }

    /**
     * Constructs a grading system with an empty list of grade. Such a grading system indicates it is not in use.
     */
    public GradingSystem() {
        this.gradeList = new ArrayList<>();
    }

    /**
     * Returns the grade list of this grading system.
     *
     * @return the grade list of this grading system.
     */
    public List<Grade> getGradeList() {
        return gradeList;
    }

    /**
     * Returns {@code true} if the specified grade is a grade being used in this grading system.
     * More specifically, returns {@code true} if the grade is contained in the grade list of this grading system.
     *
     * @param grade grade whose use in this grading system is to be tested.
     * @return {@code true} if the specified grade is a grade being used in this grading system.
     */
    public boolean hasGrade(Grade grade) {
        return gradeList.contains(grade);
    }

    /**
     * Adds the specified grade to this grading system as the lowest grade.
     * More specifically, appends the grade to the grade list of this grading system.
     *
     * @param grade graded to be added to this grading system.
     */
    public void addGrade(Grade grade) {
        assert !hasGrade(grade);
        gradeList.add(grade);
    }

    /**
     * Returns {@code true} if the specified grade is a grade being used by this grading system or
     * the grade equals to {@code NOT_GRADED}.
     *
     * @param grade grade whose validity is to be tested.
     * @return {@code true} if the grade is valid.
     */
    public boolean isValidGrade(Grade grade) {
        return gradeList.contains(grade) || grade.equals(NOT_GRADED);
    }

    /**
     * Returns {@code true} if this grading system is in use.
     * More specifically, returns {@code true} if the grade list of this grading system is not empty.
     *
     * @return {@code true} if this grading system is in use.
     */
    public boolean isInUse() {
        return gradeList.size() != 0;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        result.append("[ ");
        result.append(gradeList.stream().map(Grade::toString).collect(Collectors.joining(" > ")));
        result.append(" ]");
        return result.toString();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof GradingSystem // instanceof handles nulls
                && gradeList.equals(((GradingSystem) other).gradeList)); // state check
    }

}
