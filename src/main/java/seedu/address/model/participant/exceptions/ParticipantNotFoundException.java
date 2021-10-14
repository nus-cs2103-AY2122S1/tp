package seedu.address.model.participant.exceptions;

/**
 * Signals that the operation is unable to find the specified Participant.
 */
public class ParticipantNotFoundException extends RuntimeException {
    public ParticipantNotFoundException() {
        super("Participant not found");
    }
}
