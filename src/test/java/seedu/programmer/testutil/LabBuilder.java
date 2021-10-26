package seedu.programmer.testutil;

import seedu.programmer.model.student.Lab;

public class LabBuilder {
    public static final int DEFAULT_TITLE = 1;
    public static final Double DEFAULT_VALUE = null;
    public static final Double DEFAULT_TOTAL = 20.0;

    private int labNum;
    private Double value;
    private Double total;

    /**
     * Creates a {@code labBuilder} with the default details.
     */
    public LabBuilder() {
        labNum = DEFAULT_TITLE;
        value = DEFAULT_VALUE;
        total = DEFAULT_TOTAL;
    }

    /**
     * Initializes the labBuilder with the data of {@code labToCopy}.
     */
    public LabBuilder(Lab labToCopy) {
        labNum = labToCopy.getLabNum();
        value = labToCopy.getActualScore();
        total = labToCopy.getTotalScore();
    }

    /**
     * Sets the {@code labNum} of the {@code lab} that we are building.
     */
    public LabBuilder withLabNum(int labNum) {
        this.labNum = labNum;
        return this;
    }

    /**
     * Sets the {@code value} of the {@code lab} that we are building.
     */
    public LabBuilder withResult(Double value) {
        this.value = value;
        return this;
    }

    /**
     * Sets the {@code total} of the {@code lab} that we are building.
     */
    public LabBuilder withTotal(Double total) {
        this.total = total;
        return this;
    }

    public Lab build() {
        return new Lab(labNum, value, total);
    }
}
