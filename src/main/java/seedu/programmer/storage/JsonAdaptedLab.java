package seedu.programmer.storage;

import static java.util.Objects.requireNonNull;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.programmer.model.student.Lab;

public class JsonAdaptedLab {
    public static final String NON_NULL_ADAPTED_LAB = "The adaptedLab cannot be null.";

    private final int labNumValue;
    private final int actualScoreValue;
    private final int totalScoreValue;

    /**
     * Constructs a {@code JsonAdaptedLab} with the given lab details.
     */
    @JsonCreator
    public JsonAdaptedLab(@JsonProperty("labNumValue") int labNumValue,
                          @JsonProperty("actualScoreValue") int actualScoreValue,
                          @JsonProperty("totalScoreValue") int totalScoreValue) {
        this.labNumValue = labNumValue;
        this.actualScoreValue = actualScoreValue;
        this.totalScoreValue = totalScoreValue;
    }


    /**
     * Converts a given {@code lab} into this class for Jackson use.
     */
    public JsonAdaptedLab(Lab lab) {
        requireNonNull(lab);
        this.labNumValue = lab.getLabNumValue();
        this.actualScoreValue = lab.getLabResultValue();
        this.totalScoreValue = lab.getLabTotalValue();
    }

    public Integer getLabNum() {
        return labNumValue;
    }

    public Integer getLabResult() {
        return actualScoreValue;
    }

    public Integer getLabTotal() {
        return totalScoreValue;
    }
}
