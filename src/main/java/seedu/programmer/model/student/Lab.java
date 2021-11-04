package seedu.programmer.model.student;

import static java.util.Objects.requireNonNull;

public class Lab implements DisplayableObject {

    public static final String MESSAGE_LAB_SCORE_AND_LAB_NUMBER_REQUIREMENT =
            "Both Lab Number and Score to be should be provided together.";
    public static final String MESSAGE_LAB_NUMBER_CONSTRAINT = "Lab number should be a positive integer.";
    public static final String MESSAGE_LAB_SCORE_CONSTRAINT = "Lab score should be a non-negative integer.";
    private static final Integer UNMARKED_ACTUAL_SCORE_PLACEHOLDER = -1;


    private int labNum;
    private Integer actualScore;
    private Integer totalScore;

    /**
     * Constructs a marked Lab Object.
     * @param labNum the labNum of the lab
     * @param actualScore  the score obtained by the student
     * @param totalScore the total score
     * */
    public Lab(int labNum, Integer actualScore, Integer totalScore) {
        requireNonNull(totalScore);
        this.labNum = labNum;
        this.actualScore = actualScore;
        this.totalScore = totalScore;
    }

    /**
     * Constructs a unmarked Lab Object.
     * @param labNum the labNum of the lab
     * @param totalScore the total score */
    public Lab(int labNum, Integer totalScore) {
        requireNonNull(totalScore);
        this.labNum = labNum;
        this.actualScore = UNMARKED_ACTUAL_SCORE_PLACEHOLDER;
        this.totalScore = totalScore;
    }

    /**
     * @param labNum the labNum of the lab
     * */
    public Lab(int labNum) {
        this.labNum = labNum;
    }

    public Lab(){}

    public int getLabNum() {
        return labNum;
    }

    public Integer getActualScore() {
        return actualScore;
    }

    public Integer getTotalScore() {
        return totalScore;
    }

    public void updateActualScore(Integer value) {
        this.actualScore = value;
    }

    /**
     * Updates the labNum of the lab
     * @param newLabNum
     */
    public void updateLabNum(int newLabNum) {
        if (newLabNum > 0) {
            this.labNum = newLabNum;
        }
    }

    /**
     * Updates the totalScore of the lab
     * @param total new total score
     */
    public void updateTotal(Integer total) {
        if (total != null) {
            this.totalScore = total;
        }
    }

    public boolean isMarked() {
        return !actualScore.equals(UNMARKED_ACTUAL_SCORE_PLACEHOLDER);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Lab // instanceof handles nulls
                && labNum == ((Lab) other).getLabNum());
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
     * @return
     */
    public Lab copy() {
        return new Lab(labNum, actualScore, totalScore);
    }
}
