package seedu.edrecord.storage;

import java.util.Map;
import java.util.Optional;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.edrecord.commons.exceptions.IllegalValueException;
import seedu.edrecord.model.assignment.Grade;
import seedu.edrecord.model.assignment.Score;
import seedu.edrecord.model.assignment.Weightage;

/**
 * Jackson-friendly version of {@link Grade}.
 */
public class JsonAdaptedGrade {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Assignment %s field is missing!";

    private final String status;
    private final String score;
    private final Integer id;

    /**
     * Constructs a {@code JsonAdaptedGrade} with the given details.
     */
    @JsonCreator
    public JsonAdaptedGrade(@JsonProperty("status") String status,
                            @JsonProperty("score") String score,
                            @JsonProperty("id") Integer id) {
        this.status = status;
        this.score = score;
        this.id = id;
    }

    /**
     * Converts a given {@code ID} and {@code Grade} into this class for Jackson use.
     */
    public JsonAdaptedGrade(int id, Grade source) {
        this.id = id;
        this.status = String.valueOf(source.getStatus());
        this.score = String.valueOf(source.getScore().orElse(null));
    }

    /**
     * Converts this {@code JsonAdaptedGrade} object into the model's {@code ID} and {@code Grade} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted Grade.
     */
    public Map.Entry<Integer, Grade> toModelType() throws IllegalValueException {
        if (id == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, "ID"));
        }

        if (status == null) {
            throw new IllegalValueException(
                    String.format(MISSING_FIELD_MESSAGE_FORMAT, Grade.GradeStatus.class.getSimpleName()));
        }
        Grade.GradeStatus modelStatus;
        switch (status) {
        case "NOT_SUBMITTED":
            modelStatus = Grade.GradeStatus.NOT_SUBMITTED;
            break;
        case "SUBMITTED":
            modelStatus = Grade.GradeStatus.SUBMITTED;
            break;
        case "GRADED":
            modelStatus = Grade.GradeStatus.GRADED;
            break;
        default:
            throw new IllegalValueException(Weightage.MESSAGE_CONSTRAINTS);
        }

        if (score == null) {
            throw new IllegalValueException(
                    String.format(MISSING_FIELD_MESSAGE_FORMAT, Score.class.getSimpleName()));
        }
        Optional<Score> modelScore;
        if (score.equals("null")) {
            modelScore = Optional.empty();
        } else if (Score.isValidScore(score)) {
            modelScore = Optional.of(new Score(score));
        } else {
            throw new IllegalValueException(Score.MESSAGE_CONSTRAINTS);
        }

        return Map.entry(id, new Grade(modelScore, modelStatus));
    }

}
