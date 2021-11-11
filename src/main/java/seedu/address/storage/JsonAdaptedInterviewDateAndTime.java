package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.application.InterviewDateAndTime;
import seedu.address.model.application.Requirement;

/**
 * Jackson-friendly version of {@link seedu.address.model.application.InterviewDateAndTime}.
 */
class JsonAdaptedInterviewDateAndTime {

    private final String interviewDateAndTime;

    /**
     * Constructs a {@code JsonAdaptedInterviewDateAndTime} with the given {@code interviewDateAndTime}.
     */
    @JsonCreator
    public JsonAdaptedInterviewDateAndTime(String interviewDateAndTime) {
        this.interviewDateAndTime = interviewDateAndTime;
    }

    /**
     * Converts a given {@code InterviewDateAndTime} into this class for Jackson use.
     */
    public JsonAdaptedInterviewDateAndTime(InterviewDateAndTime source) {
        interviewDateAndTime = source.value;
    }

    @JsonValue
    public String getInterviewDateAndTime() {
        return interviewDateAndTime;
    }

    /**
     * Converts this Jackson-friendly adapted InterviewDateAndTime
     * object into the model's {@code InterviewDateAndTime} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted interview date and time.
     */
    public InterviewDateAndTime toModelType() throws IllegalValueException {
        if (!Requirement.isValidRequirement(interviewDateAndTime)) {
            throw new IllegalValueException(Requirement.MESSAGE_CONSTRAINTS);
        }
        return new InterviewDateAndTime(interviewDateAndTime);
    }

}
