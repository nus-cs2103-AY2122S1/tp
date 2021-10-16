package seedu.address.model.participant;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.ParticipantBuilder;

public class ParticipantIdTest {
    private final ParticipantId sampleParticipantId = new ParticipantId("pomu1");
    private final ParticipantId sampleParticipantIdCopy = new ParticipantId("pomu1");
    private final ParticipantId differentSampleParticipantId = new ParticipantId("pomu2");

    @Test
    public void encode_differentNameLengths_returnCorrectId() {
        Participant sampleParticipant = new ParticipantBuilder().withName("Alex Yeoh").build();
        Participant shortFirstNameSampleParticipant = new ParticipantBuilder().withName("Al Yeoh").build();
        Participant shortSurnameSampleParticipant = new ParticipantBuilder().withName("Alex Ye").build();
        Participant shortSingleNameSampleParticipant = new ParticipantBuilder().withName("Alex").build();
        Participant longSingleNameSampleParticipant = new ParticipantBuilder().withName("Alexander").build();

        assertEquals("aleyeo", ParticipantId.encode(sampleParticipant).replaceAll("\\d", ""));
        assertEquals("alyeo", ParticipantId.encode(shortFirstNameSampleParticipant).replaceAll("\\d", ""));
        assertEquals("aleye", ParticipantId.encode(shortSurnameSampleParticipant).replaceAll("\\d", ""));
        assertEquals("alex", ParticipantId.encode(shortSingleNameSampleParticipant).replaceAll("\\d", ""));
        assertEquals("alexan", ParticipantId.encode(longSingleNameSampleParticipant).replaceAll("\\d", ""));
    }

    @Test
    public void testEquals() {
        // same name -> return true
        assertEquals(sampleParticipantId, sampleParticipantId);

        // different object, same value -> return true
        assertEquals(sampleParticipantId, sampleParticipantIdCopy);

        // different value -> return false
        assertNotEquals(differentSampleParticipantId, sampleParticipantId);

        // null object -> return false
        assertNotEquals(sampleParticipantId, null);

        // different type -> return false
        assertNotEquals(sampleParticipantId, 5);
    }

    @Test
    public void testHashCode() {
        // same object -> return true
        assertEquals(sampleParticipantId.hashCode(), sampleParticipantId.hashCode());

        // different object, same value -> return true
        assertEquals(sampleParticipantId.hashCode(), sampleParticipantIdCopy.hashCode());

        // different values -> return false
        assertNotEquals(sampleParticipantId.hashCode(), differentSampleParticipantId.hashCode());
    }
}
