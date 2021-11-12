package seedu.academydirectory.model.student;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

/**
 * Represents a Student's assessment in the academy directory.
 */
public class Assessment extends SortableInformation {

    public static final String PLACEHOLDER_RA1 = "- Reading Assessment 1: ";
    public static final String PLACEHOLDER_MIDTERM = "- Midterm Examination: ";
    public static final String PLACEHOLDER_RA2 = "- Reading Assessment 2: ";
    public static final String PLACEHOLDER_PE = "- Practical Examination: ";
    public static final String PLACEHOLDER_FINAL = "- Final Examination: ";
    public static final String PLACEHOLDER_TOTAL = "Total score: ";

    public static final List<String> ASSESSMENT_LIST = Arrays.asList(
            "RA1",
            "MIDTERM",
            "RA2",
            "PE",
            "FINAL"
    );

    public static final String MESSAGE_CONSTRAINTS =
            "Grades can only be recorded for the following assessments: RA1, MIDTERM, RA2, PE, FINAL.";

    private final HashMap<String, Integer> assessment;

    /**
     * Constructs a HashMap containing the Assessment and NA for grades.
     */
    public Assessment() {
        assessment = new HashMap<>();
        assessment.put("RA1", -1);
        assessment.put("MIDTERM", -1);
        assessment.put("RA2", -1);
        assessment.put("PE", -1);
        assessment.put("FINAL", -1);
    }

    /**
     * Constructs a HashMap containing the existing Assessments and respective grades.
     */
    public Assessment(HashMap<String, Integer> assessment) {
        this.assessment = assessment;
    }

    @SuppressWarnings("unchecked") // guarantee assessment.clone() returns same type since it is final
    public Assessment getCopy() {
        return new Assessment((HashMap<String, Integer>) assessment.clone());
    }

    /**
     * Returns true if a given string is a valid assessment.
     */
    public static boolean isValidAssessment(String test) {
        return test.equalsIgnoreCase("RA1") || test.equalsIgnoreCase("MIDTERM")
                || test.equalsIgnoreCase("RA2") || test.equalsIgnoreCase("PE")
                || test.equalsIgnoreCase("FINAL");
    }

    public HashMap<String, Integer> getAssessmentHashMap() {
        return assessment;
    }

    public void updateAssessmentGrade(String assessmentName, Integer grade) {
        this.assessment.replace(assessmentName, grade);
    }

    public String getAssessmentGrade(String assessmentName) {
        Integer grade = this.assessment.get(assessmentName);
        return grade == -1 ? "NA" : grade.toString();
    }


    @Override
    public String toString() {
        return "RA1: " + getAssessmentGrade("RA1") + ", "
                + "MIDTERM: " + getAssessmentGrade("MIDTERM") + ", "
                + "RA2: " + getAssessmentGrade("RA2") + ", "
                + "PE: " + getAssessmentGrade("PE") + ", "
                + "FINAL: " + getAssessmentGrade("FINAL");
    }

    public int getTotalGrade() {
        int total = 0;
        for (String assessmentStr : ASSESSMENT_LIST) {
            total += assessment.get(assessmentStr) < 0 ? 0 : assessment.get(assessmentStr);
        }
        return total;
    }

    /**
     * Visualize assessment score of student
     * @return assessment score
     */
    public String visualizeForView() {
        StringBuilder displayedInfo = new StringBuilder();
        displayedInfo.append(PLACEHOLDER_RA1)
                .append(getAssessmentGrade("RA1")).append("\n")
                .append(PLACEHOLDER_MIDTERM)
                .append(getAssessmentGrade("MIDTERM")).append("\n")
                .append(PLACEHOLDER_RA2)
                .append(getAssessmentGrade("RA2")).append("\n")
                .append(PLACEHOLDER_PE)
                .append(getAssessmentGrade("PE")).append("\n")
                .append(PLACEHOLDER_FINAL)
                .append(getAssessmentGrade("FINAL")).append("\n\n")
                .append(PLACEHOLDER_TOTAL).append(getTotalGrade());
        return displayedInfo.toString();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Assessment // instanceof handles nulls
                && assessment.equals(((Assessment) other).assessment)); // state check
    }

    @Override
    public int hashCode() {
        return assessment.hashCode();
    }

    public static Comparator<Student> getIndividualComparator(boolean isAscending, String assessmentStr) {
        return new Comparator<Student>() {
            @Override
            public int compare(Student s1, Student s2) {
                int grade1 = s1.getAssessment().getAssessmentHashMap().get(assessmentStr);
                int grade2 = s2.getAssessment().getAssessmentHashMap().get(assessmentStr);
                return isAscending
                        ? Integer.compare(grade1, grade2)
                        : Integer.compare(grade2, grade1);
            }
        };
    }

    public static Comparator<Student> getAverageComparator(boolean isAscending) {
        return new Comparator<Student>() {
            @Override
            public int compare(Student s1, Student s2) {
                int total1 = s1.getAssessment().getTotalGrade();
                int total2 = s2.getAssessment().getTotalGrade();
                return isAscending
                        ? Integer.compare(total1, total2)
                        : Integer.compare(total2, total1);
            }
        };
    }
}
