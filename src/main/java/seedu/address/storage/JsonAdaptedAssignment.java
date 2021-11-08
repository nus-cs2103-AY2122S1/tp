package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.assignment.Assignment;
import seedu.address.model.assignment.Description;
import seedu.address.model.assignment.DueDate;
import seedu.address.model.assignment.Status;

/**
 * Jackson-friendly version of {@link Assignment}.
 */
class JsonAdaptedAssignment {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Assignment's %s field is missing!";

    private final String description;
    private final String dueDate;
    private final String status;

    /**
     * Constructs a {@code JsonAdaptedAssignment} with the given assignment details.
     */
    @JsonCreator
    public JsonAdaptedAssignment(String assignment) {
        String[] details = assignment.split("\\|", 3);
        this.description = details[0];
        this.dueDate = details[1];
        this.status = details[2];
    }

    /**
     * Converts a given {@code Assignment} into this class for Jackson use.
     */
    public JsonAdaptedAssignment(Assignment source) {
        description = source.getDescription().description;
        dueDate = source.getDueDate().value;
        status = source.getStatus().value;
    }

    @JsonValue
    public String getAssignmentDetails() {
        return description + "|" + dueDate + "|" + status;
    }

    /**
     * Converts this Jackson-friendly adapted tag object into the model's {@code Assignment} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted tag.
     */
    public Assignment toModelType() throws IllegalValueException {
        if (description == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    Description.class.getSimpleName()));
        }
        if (!Description.isValidDescription(description)) {
            throw new IllegalValueException(Description.MESSAGE_CONSTRAINTS);
        }

        final Description modelDescription = new Description(description);

        if (dueDate == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    DueDate.class.getSimpleName()));
        }
        if (!DueDate.isValidDueDateOutput(dueDate)) {
            throw new IllegalValueException(DueDate.OUTPUT_CONSTRAINTS);
        }

        final DueDate modelDueDate = DueDate.createDueDate(dueDate);

        if (status == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    Status.class.getSimpleName()));
        }
        if (!Status.isValidStatus(status)) {
            throw new IllegalValueException(Status.MESSAGE_CONSTRAINTS);
        }

        final Status modelStatus = Status.createStatus(status);

        return new Assignment(modelDescription, modelDueDate, modelStatus);
    }

}
