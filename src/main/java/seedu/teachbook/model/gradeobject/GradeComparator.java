package seedu.teachbook.model.gradeobject;

import static seedu.teachbook.model.gradeobject.GradingSystem.NOT_GRADED;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import seedu.teachbook.model.student.Student;


public class GradeComparator implements Comparator<Student> {

    private final List<Grade> gradeList;

    /**
     * Creates a new comparator for grades and adds UNGRADED to the list of grades for easier comparison.
     *
     * @param gradeList list of grades set in the grading system to be compared.
     */
    public GradeComparator(List<Grade> gradeList) {
        this.gradeList = new ArrayList<>(gradeList);
        this.gradeList.add(NOT_GRADED);
    }

    @Override
    public int compare(Student s1, Student s2) {
        return Integer.compare(gradeList.indexOf(s1.getGrade()), gradeList.indexOf(s2.getGrade()));
    }

}
