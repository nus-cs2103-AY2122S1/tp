package seedu.programmer.testutil;

import seedu.programmer.model.student.Lab;
import seedu.programmer.model.student.LabNum;

public class LabBuilder {
    public static final int DEFAULT_TITLE = 1;
    public static final Integer DEFAULT_VALUE = null;
    public static final Integer DEFAULT_TOTAL = 20;

    private LabNum labNum;
    private Integer value;
    private Integer total;

    /**
     * Creates a {@code labBuilder} with the default details.
     */
    public LabBuilder() {
        labNum = new LabNum(DEFAULT_TITLE);
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
        this.labNum = new LabNum(labNum);
        return this;
    }

    /**
     * Sets the {@code value} of the {@code lab} that we are building.
     */
    public LabBuilder withResult(Integer value) {
        this.value = value;
        return this;
    }

    /**
     * Sets the {@code total} of the {@code lab} that we are building.
     */
    public LabBuilder withTotal(Integer total) {
        this.total = total;
        return this;
    }

    public Lab build() {
        return new Lab(labNum, value, total);
    }
}
