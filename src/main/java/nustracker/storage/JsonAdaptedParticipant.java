package nustracker.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import nustracker.commons.exceptions.IllegalValueException;
import nustracker.model.event.Participant;
import nustracker.model.student.StudentId;

/**
 * Jackson-friendly version of {@link Participant}.
 */
public class JsonAdaptedParticipant {

    private final String studentId;

    /**
     * Constructs a {@code JsonAdaptedParticipant} with the given {@code StudentId}.
     */
    @JsonCreator
    public JsonAdaptedParticipant(String participantName) {
        this.studentId = participantName;
    }

    /**
     * Converts a given {@code JsonAdaptedParticipant} into this class for Jackson use.
     */
    public JsonAdaptedParticipant(Participant source) {
        studentId = source.studentId;
    }

    @JsonValue
    public String getStudentId() {
        return studentId;
    }

    /**
     * Converts this Jackson-friendly adapted participant object into the model's {@code JsonAdaptedParticipant} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted participant.
     */
    public Participant toModelType() throws IllegalValueException {
        if (!StudentId.isValidStudentId(studentId)) {
            throw new IllegalValueException(StudentId.MESSAGE_CONSTRAINTS);
        }
        return new Participant(studentId);
    }
}
