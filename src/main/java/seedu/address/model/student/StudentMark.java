package seedu.address.model.student;

public enum StudentMark {
    POOR(0),
    LOW(1),
    AVG(2),
    GOOD(3),
    HIGH(4),
    EXCELLENT(5);

    private final int mark;

    public static final String MESSAGE_CONSTRAINTS = "Mark can only be within range:\n"
                                                    + "Poor, Low, Avg, Good, High, Excellent";

    StudentMark(int mark) {
        this.mark = mark;
    }

}
