package seedu.address.model.student;

public enum StudentMark {
    POOR(0),
    LOW(1),
    AVG(2),
    GOOD(3),
    HIGH(4),
    EXCELLENT(5);

    public static final String MESSAGE_CONSTRAINTS = "Mark can only be within range:\n"
            + "Poor, Low, Avg, Good, High, Excellent";

    private final int mark;

    StudentMark(int mark) {
        this.mark = mark;
    }

}
