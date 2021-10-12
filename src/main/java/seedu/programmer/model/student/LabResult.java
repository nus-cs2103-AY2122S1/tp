package seedu.programmer.model.student;

public class LabResult {
    private String title;
    private Double actualScore;
    private Double totalScore;

    public LabResult(String title, Double actualScore, Double totalScore) {
        this.title = title;
        this.actualScore = actualScore;
        this.totalScore = totalScore;
    }

    public LabResult(){}

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
                || (other instanceof LabResult // instanceof handles nulls
                && title.equals(((LabResult) other).getTitle())
                && actualScore.equals(((LabResult) other).getActualScore()))
                && totalScore.equals(((LabResult) other).getTotalScore()); // state check
    }

}
