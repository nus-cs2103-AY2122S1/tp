package seedu.address.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.HrManager;
import seedu.address.model.ReadOnlyHrManager;
import seedu.address.model.position.Position;

/**
 * An Immutable HrManager that is serializable to JSON format.
 */
@JsonRootName(value = "HrManagerPositions")
public class JsonSerializableHrManagerPositions {
    public static final String MESSAGE_DUPLICATE_POSITIONS = "Positions list contains duplicate position(s).";

    private final List<JsonAdaptedPosition> positions = new ArrayList<>();

    /**
     * Constructs a {@code JsonSerializableHrManagerPositions} with the given positions.
     */
    @JsonCreator
    public JsonSerializableHrManagerPositions(@JsonProperty("positions") List<JsonAdaptedPosition> positions) {
        this.positions.addAll(positions);
    }

    /**
     * Converts a given {@code HrManager} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializableHrManagerPositions}.
     */
    public JsonSerializableHrManagerPositions(ReadOnlyHrManager source) {
        positions.addAll(source.getPositionList().stream().map(JsonAdaptedPosition::new)
                .collect(Collectors.toList()));

    }

    /**
     * Converts this serializable HrManagerPositions into the model's {@code HrManager} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public HrManager toModelType() throws IllegalValueException {
        HrManager hrManager = new HrManager();
        for (JsonAdaptedPosition jsonAdaptedPosition : positions) {
            Position position = jsonAdaptedPosition.toModelType();
            if (hrManager.hasPosition(position)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_POSITIONS);
            }
            hrManager.addPosition(position);
        }
        return hrManager;
    }
}
