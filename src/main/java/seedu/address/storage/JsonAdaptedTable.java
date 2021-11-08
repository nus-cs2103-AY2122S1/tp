package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.table.Table;

/**
 * Jackson-friendly version of {@link seedu.address.model.table.Table}
 */
public class JsonAdaptedTable {
    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Table's %s field is missing";
    public static final String NUMBER_OF_SEATS_CONSTRAINT = "Number of seats should be a non-zero unsigned integer";
    public static final String TABLE_ID_CONSTRAINT = "Table ID should be a non-zero unsigned integer";

    private final Integer tableId;
    private final Integer numberOfSeats;

    /**
     * Constructs {@code JsonAdaptedTable with the given values}
     */
    @JsonCreator
    public JsonAdaptedTable(
            @JsonProperty("tableId") int tableId, @JsonProperty("numberOfPeople") int numberOfSeats
    ) {
        this.tableId = tableId;
        this.numberOfSeats = numberOfSeats;
    }

    /**
     * Converts a given {@code Table} into this class for Jackson use
     */
    public JsonAdaptedTable(Table source) {
        tableId = source.getTableId();
        numberOfSeats = source.getNumOfSeats();
    }

    /**
     * Converts this Jackson-friendly adapted table object into the model's {@code Table} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted table.
     */
    public Table toModelType() throws IllegalValueException {
        if (tableId == null) {
            throw new IllegalValueException(
                    String.format(MISSING_FIELD_MESSAGE_FORMAT, "Table ID")
            );
        }
        if (tableId <= 0) {
            throw new IllegalValueException(TABLE_ID_CONSTRAINT);
        }
        if (numberOfSeats == null) {
            throw new IllegalValueException(
                    String.format(MISSING_FIELD_MESSAGE_FORMAT, "number of seats")
            );
        }
        if (numberOfSeats <= 0) {
            throw new IllegalValueException(NUMBER_OF_SEATS_CONSTRAINT);
        }
        return new Table(numberOfSeats, tableId);
    }
}
