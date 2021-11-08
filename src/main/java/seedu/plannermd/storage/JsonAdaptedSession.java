package seedu.plannermd.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.plannermd.commons.exceptions.IllegalValueException;
import seedu.plannermd.model.appointment.Duration;
import seedu.plannermd.model.appointment.Session;


/**
 * Jackson-friendly version of {@link Session}.
 */
public class JsonAdaptedSession {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Appointment's %s field is missing!";

    public static final String IDENTIFIER_START_TIME = "Start Time";

    private final String start;
    private final Integer duration;

    /**
     * Constructs a {@code JsonAdaptedSession} with the given person details.
     */
    @JsonCreator
    public JsonAdaptedSession(@JsonProperty("start") String start,
                              @JsonProperty("duration") Integer duration) {
        this.start = start;
        this.duration = duration;
    }

    /**
     * Converts a given {@code Session} into this class for Jackson use.
     */
    public JsonAdaptedSession(Session source) {
        start = source.getFormattedStartTime();
        duration = source.getMinutes();
    }

    /**
     * Converts this Jackson-friendly adapted session object into the model's
     * {@code Session} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in
     *                               the adapted session.
     */
    public Session toModelType() throws IllegalValueException {

        if (start == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, IDENTIFIER_START_TIME));
        }
        if (!Session.isValidTime(start)) {
            throw new IllegalValueException(Session.MESSAGE_CONSTRAINTS);
        }

        if (duration == null) {
            throw new IllegalValueException(
                    String.format(MISSING_FIELD_MESSAGE_FORMAT, Duration.class.getSimpleName()));
        }
        if (!Duration.isValidDuration(duration)) {
            throw new IllegalValueException(Duration.MESSAGE_CONSTRAINTS);
        }
        final Duration modelDuration = new Duration(duration);

        return new Session(start, modelDuration);
    }

}
