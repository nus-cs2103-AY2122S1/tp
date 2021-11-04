package seedu.programmer.model.student;

import static java.util.Objects.requireNonNull;

public class Lab implements DisplayableObject {

    public static final String MESSAGE_LAB_SCORE_AND_LAB_NUMBER_REQUIREMENT =
            "Both Lab Number and Score to be should be provided together.";
    public static final String MESSAGE_LAB_NUMBER_CONSTRAINT =
            "Lab number should be between 0 and 14 (non-inclusive).";
    public static final String MESSAGE_LAB_SCORE_CONSTRAINT = "Lab score should be a non-negative integer.";
    public static final String MESSAGE_LAB_TOTAL_SCORE_CONSTRAINT =
            "Lab total score should be between 0 and 100 (inclusive)";

    private LabNum labNum;
    private LabResult actualScore;
    private LabTotal totalScore;

    /**
     * Constructs a marked Lab Object.
     * @param labNum the labNum of the lab
     * @param actualScore  the score obtained by the student
     * @param totalScore the total score
     * */
    public Lab(LabNum labNum, LabResult actualScore, LabTotal totalScore) {
        requireNonNull(totalScore);
        this.labNum = labNum;
        this.actualScore = actualScore;
        this.totalScore = totalScore;
    }

    /**
     * Constructs a unmarked Lab Object.
     * @param labNum the labNum of the lab
     * @param totalScore the total score */
    public Lab(LabNum labNum, LabTotal totalScore) {
        requireNonNull(totalScore);
        this.labNum = labNum;
        this.actualScore = new LabResult(LabResult.getPlaceholder());
        this.totalScore = totalScore;
    }

    /**
     * @param labNum the labNum of the lab
     * */
    public Lab(LabNum labNum) {
        this.labNum = labNum;
    }

    public Lab(){}

    public LabNum getLabNum() {
        return labNum;
    }

    public LabResult getLabResult() {
        return actualScore;
    }

    public LabTotal getLabTotal() {
        return totalScore;
    }

    public String getLabNumValue() {
        return labNum.getLabNum().toString();
    }

    public String getLabResultValue() {
        return actualScore.toString();
    }

    public String getLabTotalValue() {
        return totalScore.toString();
    }

    public void updateActualScore(LabResult value) {
        this.actualScore = value;
    }

    /**
     * Updates the labNum of the lab
     * @param newLabNum the new lab number
     */
    public void updateLabNum(LabNum newLabNum) {
        if (newLabNum.getLabNum() > 0) {
            this.labNum = newLabNum;
        }
    }

    /**
     * Updates the totalScore of the lab
     * @param total new total score
     */
    public void updateTotal(LabTotal total) {
        if (total != null) {
            this.totalScore = total;
        }
    }

    public boolean isMarked() {
        return !(actualScore.getLabResult().equals(LabResult.getPlaceholder()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Lab // instanceof handles nulls
                && labNum.getLabNum() == ((Lab) other).getLabNum().getLabNum());
    }

    /**
     * Returns true if a given string is a valid score.
     */
    public static boolean isValidScore (Integer score) {
        return score >= 0;
    }

    @Override
    public String toString() {
        return "Lab " + this.labNum;
    }

    /**
     * Make a copy of an existing Lab.
     * @return a copy of the lab
     */
    public Lab copy() {
        return new Lab(labNum, actualScore, totalScore);
    }
}
