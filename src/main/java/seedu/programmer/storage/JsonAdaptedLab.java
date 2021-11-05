package seedu.programmer.storage;

import static java.util.Objects.requireNonNull;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.programmer.model.student.Lab;

public class JsonAdaptedLab {
    private String labNumValue;
    private String actualScoreValue;
    private String totalScoreValue;

    /**
     * Constructs a {@code JsonAdaptedLab} with the given lab details.
     */
    @JsonCreator
    public JsonAdaptedLab(@JsonProperty("labNumValue") String labNumValue,
                          @JsonProperty("actualScoreValue") String actualScoreValue,
                          @JsonProperty("totalScoreValue") String totalScoreValue) {
        this.labNumValue = labNumValue;
        this.actualScoreValue = actualScoreValue;
        this.totalScoreValue = totalScoreValue;
    }


    /**
     * Converts a given {@code lab} into this class for Jackson use.
     */
    public JsonAdaptedLab(Lab lab) {
        requireNonNull(lab);
        this.labNumValue = String.valueOf(lab.getLabNumValue());
        this.actualScoreValue = lab.getLabResultValue();
        this.totalScoreValue = lab.getLabTotalValue();
    }

    public String getLabNumValue() {
        return labNumValue;
    }

    public String getLabResultValue() {
        return actualScoreValue;
    }

    public String getLabTotalValue() {
        return totalScoreValue;
    }

    public Integer getLabNum() {
        return Integer.parseInt(labNumValue);
    }

    public Integer getLabResult() {
        return Integer.parseInt(actualScoreValue);
    }

    public Integer getLabTotal() {
        return Integer.parseInt(totalScoreValue);
    }
}
