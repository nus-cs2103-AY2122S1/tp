package seedu.programmer.model.student;

//todo: for testing show feature only
public class Lab {
    private String title;
    private Double actualScore = 0.0;
    private Double totalScore;

    /**
     * @param title the title of the lab
     * @param actualScore  the score obtained by the student
     * @param totalScore the total score
     * */
    public Lab(String title, Double actualScore, Double totalScore) {
        this.title = title;
        this.actualScore = actualScore;
        this.totalScore = totalScore;
    }

    /**
     * @param title the title of the lab
     * @param totalScore the total score
     * */
    public Lab(String title, Double totalScore) {
        this.title = title;
        this.totalScore = totalScore;
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

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Lab // instanceof handles nulls
                && title.equals(((Lab) other).getTitle())
                && actualScore.equals(((Lab) other).getActualScore()))
                && totalScore.equals(((Lab) other).getTotalScore()); // state check
    }

}
