package seedu.address.model.event;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalEvents.ANOTHER_EVENT;
import static seedu.address.testutil.TypicalEvents.SAMPLE_EVENT;

import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

import org.junit.jupiter.api.Test;

import seedu.address.model.event.exceptions.DuplicateEventException;
import seedu.address.model.event.exceptions.EventNotFoundException;

class UniqueEventListTest {

    private final UniqueEventList uniqueEventList = new UniqueEventList();
    private final UniqueEventList secondUniqueEventList = new UniqueEventList();

    @Test
    public void contains_nullEvent_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueEventList.contains(null));
    }

    @Test
    public void contains_eventNotInList_returnsFalse() {
        assertFalse(uniqueEventList.contains(SAMPLE_EVENT));
    }

    @Test
    public void contains_eventInList_returnsTrue() {
        uniqueEventList.add(SAMPLE_EVENT);
        assertTrue(uniqueEventList.contains(SAMPLE_EVENT));
    }

    @Test
    public void add_nullEvent_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueEventList.add(null));
    }

    @Test
    public void add_duplicateEvent_throwsDuplicateEventException() {
        uniqueEventList.add(SAMPLE_EVENT);
        assertThrows(DuplicateEventException.class, () -> uniqueEventList.add(SAMPLE_EVENT));
    }

    @Test
    public void remove_nullEvent_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueEventList.remove(null));
    }

    @Test
    public void remove_eventDoesNotExist_throwsEventNotFoundException() {
        assertThrows(EventNotFoundException.class, () -> uniqueEventList.remove(ANOTHER_EVENT));
    }

    @Test
    public void remove_existingEvent_removesEvent() {
        uniqueEventList.add(SAMPLE_EVENT);
        uniqueEventList.remove(SAMPLE_EVENT);
        UniqueEventList expectedUniqueEventList = new UniqueEventList();
        assertEquals(expectedUniqueEventList, uniqueEventList);
    }

    @Test
    public void setEvent_nullTargetEvent_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueEventList.setEvent(null, SAMPLE_EVENT));
    }

    @Test
    public void setEvent_nullEditedEvent_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueEventList.setEvent(SAMPLE_EVENT, null));
    }

    @Test
    public void setEvent_targetEventNotInList_throwsEventNotFoundException() {
        assertThrows(EventNotFoundException.class, () -> uniqueEventList.setEvent(SAMPLE_EVENT, ANOTHER_EVENT));
    }

    @Test
    public void setEvent_listContainsEditedEvent_throwsDuplicateEventException() {
        uniqueEventList.add(SAMPLE_EVENT);
        uniqueEventList.add(ANOTHER_EVENT);
        assertThrows(DuplicateEventException.class, () -> uniqueEventList.setEvent(ANOTHER_EVENT, SAMPLE_EVENT));
    }

    @Test
    public void setEvent_existingEvent_replacesEvent() {
        uniqueEventList.add(SAMPLE_EVENT);
        uniqueEventList.setEvent(SAMPLE_EVENT, ANOTHER_EVENT);
        UniqueEventList expectedUniqueEventList = new UniqueEventList();
        expectedUniqueEventList.add(ANOTHER_EVENT);
        assertEquals(expectedUniqueEventList, uniqueEventList);
    }

    @Test
    public void setEvents_uniqueEventList_replacesOwnListWithProvidedUniqueEventList() {
        uniqueEventList.add(SAMPLE_EVENT);
        UniqueEventList expectedUniqueEventList = new UniqueEventList();
        expectedUniqueEventList.add(ANOTHER_EVENT);
        uniqueEventList.setEvents(expectedUniqueEventList);
        assertEquals(expectedUniqueEventList, uniqueEventList);
    }

    @Test
    public void setEvents_nullList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueEventList.setEvents((List<Event>) null));
    }

    @Test
    public void setEvents_list_replacesOwnListWithProvidedList() {
        uniqueEventList.add(SAMPLE_EVENT);
        List<Event> eventList = Collections.singletonList(ANOTHER_EVENT);
        uniqueEventList.setEvents(eventList);
        UniqueEventList expectedUniqueEventList = new UniqueEventList();
        expectedUniqueEventList.add(ANOTHER_EVENT);
        assertEquals(expectedUniqueEventList, uniqueEventList);
    }

    @Test
    public void setEvents_listWithDuplicateEvents_throwsDuplicateEventException() {
        List<Event> listWithDuplicateEvents = Arrays.asList(SAMPLE_EVENT, SAMPLE_EVENT);
        assertThrows(DuplicateEventException.class, () -> uniqueEventList.setEvents(listWithDuplicateEvents));
    }

    @Test
    public void asUnmodifiableObservableList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, ()
            -> uniqueEventList.asUnmodifiableObservableList().remove(0));
    }

    @Test
    public void iterator_checkForNextInEmptyList_returnFalse() {
        Iterator<Event> iterator = uniqueEventList.iterator();
        assertFalse(iterator.hasNext());
    }

    @Test
    public void iterator_getNextInEmptyList_throwsError() {
        Iterator<Event> iterator = uniqueEventList.iterator();
        assertThrows(NoSuchElementException.class, iterator::next);
    }

    @Test
    public void testEquals() {
        secondUniqueEventList.add(SAMPLE_EVENT);

        // same list
        assertTrue(uniqueEventList.equals(uniqueEventList));

        // null -> returns false
        assertFalse(uniqueEventList.equals(null));

        // second list contains event
        assertFalse(uniqueEventList.equals(secondUniqueEventList));

        // add same event to first list
        uniqueEventList.add(SAMPLE_EVENT);
        assertTrue(uniqueEventList.equals(secondUniqueEventList));
    }
}
