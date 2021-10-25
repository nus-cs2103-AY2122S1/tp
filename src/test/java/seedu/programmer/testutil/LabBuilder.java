package seedu.programmer.testutil;

import seedu.programmer.model.student.Lab;

public class LabBuilder {
    public static final String DEFAULT_TITLE = "1";
    public static final Double DEFAULT_VALUE = null;
    public static final Double DEFAULT_TOTAL = 20.0;

    private String title;
    private Double value;
    private Double total;

    /**
     * Creates a {@code labBuilder} with the default details.
     */
    public LabBuilder() {
        title = DEFAULT_TITLE;
        value = DEFAULT_VALUE;
        total = DEFAULT_TOTAL;
    }

    /**
     * Initializes the labBuilder with the data of {@code labToCopy}.
     */
    public LabBuilder(Lab labToCopy) {
        title = labToCopy.getTitle();
        value = labToCopy.getActualScore().doubleValue();
        total = labToCopy.getTotalScore().doubleValue();
    }

    /**
     * Sets the {@code title} of the {@code lab} that we are building.
     */
    public LabBuilder withTitle(String title) {
        this.title = title;
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
        return new Lab(title, value, total);
    }
}
