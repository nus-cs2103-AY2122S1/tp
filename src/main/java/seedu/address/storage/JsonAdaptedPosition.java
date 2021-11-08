package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.position.Position;
import seedu.address.model.position.Position.PositionStatus;
import seedu.address.model.position.Title;

public class JsonAdaptedPosition {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Position's %s field is missing!";

    private final String title;

    private final PositionStatus positionStatus;

    /**
     * Constructs a {@code JsonAdaptedPosition} with the given {@code positionName}.
     */
    @JsonCreator
    public JsonAdaptedPosition(@JsonProperty("title") String title,
                               @JsonProperty("positionStatus") PositionStatus positionStatus) {
        this.title = title;
        this.positionStatus = positionStatus;
    }

    /**
     * Converts a given {@code Position} into this class for Jackson use.
     */
    public JsonAdaptedPosition(Position source) {
        title = source.getTitle().fullTitle;
        positionStatus = source.getStatus();
    }

    public String getTitle() {
        return title;
    }

    /**
     * Converts this Jackson-friendly adapted position object into the model's {@code Position} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted position.
     */
    public Position toModelType() throws IllegalValueException {
        if (title == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Title.class.getSimpleName()));
        }
        if (!Title.isValidTitle(title)) {
            throw new IllegalValueException(Title.MESSAGE_CONSTRAINTS);
        }

        final Title modelTitle = new Title(title);

        Position position = new Position(modelTitle);
        position.setStatus(positionStatus);
        return position;
    }
}
