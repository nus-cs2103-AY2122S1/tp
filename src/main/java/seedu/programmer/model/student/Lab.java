package seedu.programmer.model.student;

import static java.util.Objects.requireNonNull;
import static seedu.programmer.commons.util.AppUtil.checkArgument;

public class Lab {
    private String title;
    private Double actualScore;
    private Double totalScore;
    
    public static final String LAB_SCORE_MESSAGE_CONSTRAINTS = "The total score should be a positive value.";

    /**
     * @param title the title of the lab
     * @param actualScore  the score obtained by the student
     * @param totalScore the total score
     * */
    public Lab(String title, Double actualScore, Double totalScore) {
        requireNonNull(actualScore);
        requireNonNull(totalScore);
        checkArgument(isValidScore(actualScore), LAB_SCORE_MESSAGE_CONSTRAINTS);
        checkArgument(isValidScore(totalScore), LAB_SCORE_MESSAGE_CONSTRAINTS);
        this.title = title;
        this.actualScore = actualScore;
        this.totalScore = totalScore;
    }

    /**
     * @param title the title of the lab
     * @param totalScore the total score
     * */
    public Lab(String title, Double totalScore) {
        requireNonNull(title);
        requireNonNull(totalScore);
        checkArgument(isValidScore(totalScore), LAB_SCORE_MESSAGE_CONSTRAINTS);
        this.title = title;
        this.actualScore = Double.NaN;
        this.totalScore = totalScore;
    }

    /**
     * @param title the title of the lab
     * */
    public Lab(String title) {
        this.title = title;
    }

    public Lab(){}

    public String getTitle() {
        return title;
    }

    public Double getActualScore() {
        return actualScore;
    }

    public Double getTotalScore() {
        return totalScore;
    }

    public void updateActualScore(Double value) {
        this.actualScore = value;
    }

    /**
     * Updates the title of the lab
     * @param newTitle
     */
    public void updateTitle(String newTitle) {
        if (newTitle != null) {
            this.title = newTitle;
        }
    }

    /**
     * Updates the totalScore of the lab
     * @param total
     */
    public void updateTotal(Double total) {
        if (total != null) {
            this.totalScore = total;
        }
    }

    public boolean isMarked() {
        return !actualScore.equals(Double.NaN);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Lab // instanceof handles nulls
                && title.equals(((Lab) other).getTitle()));
    }

    /**
     * Returns true if a given string is a valid lab title.
     */
    public static boolean isValidLab (String title) {
        return title.length() == 2;
    }

    /**
     * Returns true if a given string is a valid score.
     */
    public static boolean isValidScore (Double score) {
        System.out.println(score);
        return score >= 0;
    }

    @Override
    public String toString() {
        return this.title;
    }
}
