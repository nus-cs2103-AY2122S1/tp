package seedu.programmer.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import seedu.programmer.model.student.Lab;

import static java.util.Objects.requireNonNull;

public class JsonAdaptedLab {
    private String labNumValue;
    private String actualScoreValue;
    private String totalScoreValue;

    /**
     * Constructs a {@code JsonAdaptedStudent} with the given student details.
     */
    @JsonCreator
    public JsonAdaptedLab(@JsonProperty("labNumValue") String labNumValue, @JsonProperty("actualScoreValue") String actualScoreValue,
                              @JsonProperty("totalScoreValue") String totalScoreValue) {
        this.labNumValue = labNumValue;
        //todo: change to actualScoreValue and totalScoreValue when the relevant classes are ready.
        this.actualScoreValue = actualScoreValue;
        this.totalScoreValue = totalScoreValue;
    }

    public JsonAdaptedLab(Lab lab) {
        requireNonNull(lab);
        this.labNumValue = lab.getLabNumValue();
        this.actualScoreValue = lab.getActualScoreValue();
        this.totalScoreValue = lab.getTotalScoreValue();
    }

    public String getLabNumValue() {
        return labNumValue;
    }

    public String getActualScoreValue() {
        return actualScoreValue;
    }

    public String getTotalScoreValue() {
        return totalScoreValue;
    }

    public Integer getLabNum() {
        return Integer.parseInt(labNumValue);
    }

    public Integer getActualScore() {
        return Integer.parseInt(actualScoreValue);
    }

    public Integer getTotalScore() {
        return Integer.parseInt(totalScoreValue);
    }
}
