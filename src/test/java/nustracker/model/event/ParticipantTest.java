package nustracker.model.event;

import static nustracker.testutil.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class ParticipantTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Participant(null));
    }

    @Test
    public void isValidParticipant() {
        // null tag name
        assertThrows(NullPointerException.class, () -> Participant.isValidParticipant(null));

        // test cases are similar to student id test cases
        // invalid participants
        assertFalse(Participant.isValidParticipant("e012323"));
        assertFalse(Participant.isValidParticipant("e123133"));
        assertFalse(Participant.isValidParticipant("e931138"));
        assertFalse(Participant.isValidParticipant("e831639"));
        assertFalse(Participant.isValidParticipant("a123199"));
        assertFalse(Participant.isValidParticipant("b981239"));
        assertFalse(Participant.isValidParticipant("e918329"));
        assertFalse(Participant.isValidParticipant("e912812"));

        // valid participants
        assertTrue(Participant.isValidParticipant("e1234897"));
        assertTrue(Participant.isValidParticipant("e1234890"));
        assertTrue(Participant.isValidParticipant("e8971232"));
        assertTrue(Participant.isValidParticipant("e8912372"));
    }

}
