package seedu.address.model.participant;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalParticipants.ALEX;
import static seedu.address.testutil.TypicalParticipants.BERNICE;

import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

import org.junit.jupiter.api.Test;

import seedu.address.model.participant.exceptions.DuplicateParticipantException;
import seedu.address.model.participant.exceptions.ParticipantNotFoundException;

public class UniqueParticipantListTest {
    private final UniqueParticipantList uniqueParticipantList = new UniqueParticipantList();
    private final UniqueParticipantList secondUniqueParticipantList = new UniqueParticipantList();

    @Test
    public void contains_nullParticipant_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueParticipantList.contains(null));
    }

    @Test
    public void contains_participantNotInList_returnsFalse() {
        assertFalse(uniqueParticipantList.contains(ALEX));
    }

    @Test
    public void contains_participantInList_returnsTrue() {
        uniqueParticipantList.add(ALEX);
        assertTrue(uniqueParticipantList.contains(ALEX));
    }

    @Test
    public void add_nullParticipant_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueParticipantList.add(null));
    }

    @Test
    public void add_duplicateParticipant_throwsDuplicateParticipantException() {
        uniqueParticipantList.add(ALEX);
        assertThrows(DuplicateParticipantException.class, () -> uniqueParticipantList.add(ALEX));
    }

    @Test
    public void remove_nullEvent_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueParticipantList.remove(null));
    }

    @Test
    public void remove_participantDoesNotExist_throwsEventNotFoundException() {
        assertThrows(ParticipantNotFoundException.class, () -> uniqueParticipantList.remove(BERNICE));
    }

    @Test
    public void remove_existingParticipant_removesParticipant() {
        uniqueParticipantList.add(ALEX);
        uniqueParticipantList.remove(ALEX);
        UniqueParticipantList expectedUniqueParticipantList = new UniqueParticipantList();
        assertEquals(expectedUniqueParticipantList, uniqueParticipantList);
    }

    @Test
    public void setParticipant_nullTargetParticipant_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueParticipantList.setParticipant(null, ALEX));
    }

    @Test
    public void setParticipant_nullEditedParticipant_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueParticipantList.setParticipant(ALEX, null));
    }

    @Test
    public void setParticipant_targetParticipantNotInList_throwsParticipantNotFoundException() {
        assertThrows(ParticipantNotFoundException.class, () -> uniqueParticipantList.setParticipant(ALEX, BERNICE));
    }

    @Test
    public void setParticipant_listContainsEditedParticipant_throwsDuplicateParticipantException() {
        uniqueParticipantList.add(ALEX);
        uniqueParticipantList.add(BERNICE);
        assertThrows(DuplicateParticipantException.class, () -> uniqueParticipantList.setParticipant(ALEX, BERNICE));
    }

    @Test
    public void setParticipant_existingParticipant_replacesParticipant() {
        uniqueParticipantList.add(ALEX);
        uniqueParticipantList.setParticipant(ALEX, BERNICE);
        UniqueParticipantList expectedUniqueParticipantList = new UniqueParticipantList();
        expectedUniqueParticipantList.add(BERNICE);
        assertEquals(expectedUniqueParticipantList, uniqueParticipantList);
    }

    @Test
    public void setParticipants_uniqueParticipantList_replacesOwnListWithProvidedUniqueParticipantList() {
        uniqueParticipantList.add(ALEX);
        UniqueParticipantList expectedUniqueParticipantList = new UniqueParticipantList();
        expectedUniqueParticipantList.add(BERNICE);
        uniqueParticipantList.setParticipants(expectedUniqueParticipantList);
        assertEquals(expectedUniqueParticipantList, uniqueParticipantList);
    }

    @Test
    public void setParticipants_nullList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueParticipantList.setParticipants(
                (List<Participant>) null));
    }

    @Test
    public void setParticipants_list_replacesOwnListWithProvidedList() {
        uniqueParticipantList.add(ALEX);
        List<Participant> participantList = Collections.singletonList(ALEX);
        uniqueParticipantList.setParticipants(participantList);
        UniqueParticipantList expectedUniqueParticipantList = new UniqueParticipantList();
        expectedUniqueParticipantList.add(ALEX);
        assertEquals(expectedUniqueParticipantList, uniqueParticipantList);
    }

    @Test
    public void setParticipants_listWithDuplicateParticipants_throwsDuplicateParticipantException() {
        List<Participant> listWithDuplicateParticipants = Arrays.asList(ALEX, ALEX);
        assertThrows(DuplicateParticipantException.class, () ->
                uniqueParticipantList.setParticipants(listWithDuplicateParticipants));
    }

    @Test
    public void asUnmodifiableObservableList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () ->
                uniqueParticipantList.asUnmodifiableObservableList().remove(0));
    }

    @Test
    public void iterator_checkForNextInEmptyList_returnFalse() {
        Iterator<Participant> iterator = uniqueParticipantList.iterator();
        assertFalse(iterator.hasNext());
    }

    @Test
    public void iterator_getNextInEmptyList_throwsError() {
        Iterator<Participant> iterator = uniqueParticipantList.iterator();
        assertThrows(NoSuchElementException.class, iterator::next);
    }

    @Test
    public void testEquals() {
        secondUniqueParticipantList.add(ALEX);

        // same list
        assertTrue(uniqueParticipantList.equals(uniqueParticipantList));

        // null -> returns false
        assertFalse(uniqueParticipantList.equals(null));

        // second list contains event
        assertFalse(uniqueParticipantList.equals(secondUniqueParticipantList));

        // add same event to first list
        uniqueParticipantList.add(ALEX);
        assertTrue(uniqueParticipantList.equals(secondUniqueParticipantList));
    }

    @Test
    public void testHashCode() {
        // same list -> returns same hashcode
        assertEquals(uniqueParticipantList.hashCode(), uniqueParticipantList.hashCode());

        // different lists -> returns different hashcode
        secondUniqueParticipantList.add(BERNICE);
        assertNotEquals(uniqueParticipantList.hashCode(), secondUniqueParticipantList.hashCode());
    }
}
