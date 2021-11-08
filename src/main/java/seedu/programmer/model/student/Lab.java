package seedu.programmer.model.student;

import static java.util.Objects.requireNonNull;

/**
 * Represents a Lab in the ProgrammerError.
 */
public class Lab implements DisplayableObject {

    public static final String MESSAGE_LAB_SCORE_AND_LAB_NUMBER_REQUIREMENT =
            "Both Lab Number and Score should be provided together.";
    public static final String MESSAGE_LAB_NUMBER_CONSTRAINT =
            "Lab number should be between 1 and 13 (inclusive).";
    public static final String MESSAGE_LAB_SCORE_CONSTRAINT = "Lab score should be a non-negative integer.";
    public static final String MESSAGE_LAB_TOTAL_SCORE_CONSTRAINT =
            "Lab total score should be between 1 and 100 (inclusive)";

    public static final String MESSAGE_LAB_ALREADY_EXISTS = "%1$s already exists.";
    public static final String MESSAGE_LAB_NOT_EXISTS = "%1$s doesn't exist.";

    private LabNum labNum;
    private LabResult actualScore;
    private LabTotal totalScore;

    /**
     * Class constructor for a marked Lab Object.
     *
     * @param labNum The labNum of the lab.
     * @param actualScore The score obtained by the student.
     * @param totalScore The total score.
     */
    public Lab(LabNum labNum, LabResult actualScore, LabTotal totalScore) {
        requireNonNull(totalScore);
        this.labNum = labNum;
        this.actualScore = actualScore;
        this.totalScore = totalScore;
    }

    /**
     * Class constructor for an unmarked Lab Object.
     *
     * @param labNum The labNum of the lab.
     * @param totalScore The total score.
     */
    public Lab(LabNum labNum, LabTotal totalScore) {
        requireNonNull(totalScore);
        this.labNum = labNum;
        this.actualScore = new LabResult(LabResult.getPlaceholder());
        this.totalScore = totalScore;
    }

    /**
     * Class constructor for Lab with a specific lab number.
     *
     * @param labNum The labNum of the lab.
     */
    public Lab(LabNum labNum) {
        this.labNum = labNum;
    }

    /**
     * Empty constructor for Lab.
     */
    public Lab() {}

    /**
     * Returns the labNum of the lab.
     *
     * @return The labNum of the lab.
     */
    public LabNum getLabNum() {
        return labNum;
    }

    /**
     * Returns the student's actual score of the lab.
     *
     * @return The LabResult object.
     */
    public LabResult getLabResult() {
        return actualScore;
    }

    /**
     * Gets the total score of the lab.
     *
     * @return The total score of the lab.
     */
    public LabTotal getLabTotal() {
        return totalScore;
    }

    /**
     * Gets the lab number.
     *
     * @return The lab number.
     */
    public int getLabNumValue() {
        return labNum.getLabNum();
    }

    /**
     * Gets the lab result integer value.
     *
     * @return The lab result integer value.
     */
    public int getLabResultValue() {
        return actualScore.getLabResult();
    }

    /**
     * Gets the total score of the lab.
     *
     * @return The total score of the lab.
     */
    public int getLabTotalValue() {
        return totalScore.getLabTotalScore();
    }

    /**
     * Updates the actual score of the student for the lab.
     *
     * @param value The new actual score.
     */
    public void updateActualScore(LabResult value) {
        this.actualScore = value;
    }

    /**
     * Updates the labNum of the lab.
     *
     * @param newLabNum The new lab number.
     */
    public void updateLabNum(LabNum newLabNum) {
        if (newLabNum.getLabNum() > 0) {
            this.labNum = newLabNum;
        }
    }

    /**
     * Updates the totalScore of the lab.
     *
     * @param total New total score.
     */
    public void updateTotal(LabTotal total) {
        if (total != null) {
            this.totalScore = total;
        }
    }

    /**
     * Returns whether a lab is marked.
     *
     * @return true if a lab is marked.
     */
    public boolean isMarked() {
        return !(actualScore.getLabResult().equals(LabResult.getPlaceholder()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Lab // instanceof handles nulls
                && labNum.getLabNum().equals(((Lab) other).getLabNum().getLabNum()));
    }

    /**
     * Check if the labNum, labResult and labTotal are all identical.
     */
    public boolean isIdenticalLab(Lab otherLab) {
        return labNum.equals(otherLab.getLabNum())
                && actualScore.equals(otherLab.getLabResult())
                && totalScore.equals(otherLab.getLabTotal());
    }

    @Override
    public String toString() {
        return "Lab " + this.labNum;
    }

    /**
     * Make a copy of an existing Lab.
     *
     * @return A copy of the lab.
     */
    public Lab copy() {
        return new Lab(labNum, actualScore, totalScore);
    }
}
