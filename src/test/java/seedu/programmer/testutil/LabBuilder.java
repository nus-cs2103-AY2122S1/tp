package seedu.programmer.testutil;

import seedu.programmer.model.student.Lab;
import seedu.programmer.model.student.LabNum;
import seedu.programmer.model.student.LabResult;
import seedu.programmer.model.student.LabTotal;

public class LabBuilder {
    public static final int DEFAULT_TITLE = 1;
    public static final Integer DEFAULT_VALUE = null;
    public static final Integer DEFAULT_TOTAL = 20;

    private LabNum labNum;
    private LabResult value;
    private LabTotal total;

    /**
     * Creates a {@code labBuilder} with the default details.
     */
    public LabBuilder() {
        labNum = new LabNum(DEFAULT_TITLE);
        value = new LabResult(DEFAULT_VALUE);
        total = new LabTotal(DEFAULT_TOTAL);
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
        this.value = new LabResult(value);
        return this;
    }

    /**
     * Sets the {@code total} of the {@code lab} that we are building.
     */
    public LabBuilder withTotal(Integer total) {
        this.total = new LabTotal(total);
        return this;
    }

    public Lab build() {
        return new Lab(labNum, value, total);
    }
}
