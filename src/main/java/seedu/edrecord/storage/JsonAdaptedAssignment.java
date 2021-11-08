package seedu.edrecord.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.edrecord.commons.exceptions.IllegalValueException;
import seedu.edrecord.model.assignment.Assignment;
import seedu.edrecord.model.assignment.Score;
import seedu.edrecord.model.assignment.Weightage;
import seedu.edrecord.model.name.Name;

/**
 * Jackson-friendly version of {@link Assignment}.
 */
public class JsonAdaptedAssignment {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Assignment %s field is missing!";

    private final String name;
    private final String weightage;
    private final String maxScore;
    private final Integer id;

    /**
     * Constructs a {@code JsonAdaptedAssignment} with the given details.
     */
    @JsonCreator
    public JsonAdaptedAssignment(@JsonProperty("name") String name, @JsonProperty("weightage") String weightage,
                                 @JsonProperty("maxScore") String maxScore, @JsonProperty("id") Integer id) {
        this.name = name;
        this.weightage = weightage;
        this.maxScore = maxScore;
        this.id = id;
    }

    /**
     * Converts a given {@code Assignment} into this class for Jackson use.
     */
    public JsonAdaptedAssignment(Assignment source) {
        name = source.getName().name;
        weightage = String.valueOf(source.getWeightage().weightage);
        maxScore = String.valueOf(source.getMaxScore().score);
        id = source.getId();
    }

    /**
     * Converts this {@code JsonAdaptedAssignment} object into the model's {@code Assignment} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted assignment.
     */
    public Assignment toModelType() throws IllegalValueException {
        if (name == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName()));
        }
        if (!Name.isValidName(name)) {
            throw new IllegalValueException(Name.MESSAGE_CONSTRAINTS);
        }
        final Name modelName = new Name(name);

        if (weightage == null) {
            throw new IllegalValueException(
                    String.format(MISSING_FIELD_MESSAGE_FORMAT, Weightage.class.getSimpleName()));
        }
        if (!Weightage.isValidWeightage(weightage)) {
            throw new IllegalValueException(Weightage.MESSAGE_CONSTRAINTS);
        }
        final Weightage modelWeightage = new Weightage(weightage);

        if (maxScore == null) {
            throw new IllegalValueException(
                    String.format(MISSING_FIELD_MESSAGE_FORMAT, Score.class.getSimpleName()));
        }
        if (!Score.isValidScore(maxScore)) {
            throw new IllegalValueException(Score.MESSAGE_CONSTRAINTS);
        }
        final Score modelScore = new Score(maxScore);

        if (id == null) {
            throw new IllegalValueException(
                    String.format(MISSING_FIELD_MESSAGE_FORMAT, "ID"));
        }

        return new Assignment(modelName, modelWeightage, modelScore, id);
    }

}
