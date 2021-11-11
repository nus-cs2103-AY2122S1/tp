package nustracker.model.event;

import static nustracker.logic.commands.CommandTestUtil.VALID_EVENTDATE_FINAL;
import static nustracker.logic.commands.CommandTestUtil.VALID_EVENTTIME_FINAL;
import static nustracker.testutil.Assert.assertThrows;
import static nustracker.testutil.TypicalEvents.FINAL;
import static nustracker.testutil.TypicalEvents.TEST;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import nustracker.model.event.exceptions.DuplicateEventException;
import nustracker.model.event.exceptions.EventNotFoundException;
import nustracker.testutil.EventBuilder;

class UniqueEventListTest {

    private final UniqueEventList uniqueEventList = new UniqueEventList();

    @Test
    public void contains_nullEvent_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueEventList.contains(null));
    }

    @Test
    public void contains_eventNotInList_returnsFalse() {
        Assertions.assertFalse(uniqueEventList.contains(TEST));
    }

    @Test
    public void contains_eventInList_returnsTrue() {
        uniqueEventList.add(TEST);
        Assertions.assertTrue(uniqueEventList.contains(TEST));
    }

    @Test
    public void contains_eventWithSameIdentityFieldsInList_returnsTrue() {
        uniqueEventList.add(TEST);
        Event editedTest = new EventBuilder(TEST).withDate(VALID_EVENTDATE_FINAL).withTime(VALID_EVENTTIME_FINAL)
                .build();
        Assertions.assertTrue(uniqueEventList.contains(editedTest));
    }

    @Test
    public void add_nullEvent_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueEventList.add(null));
    }

    @Test
    public void add_duplicateEvent_throwsDuplicateEventException() {
        uniqueEventList.add(TEST);
        assertThrows(DuplicateEventException.class, () -> uniqueEventList.add(TEST));
    }

    @Test
    public void get_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueEventList.get(null));
    }

    @Test
    public void get_existingEvent_returnsTest() {
        uniqueEventList.add(TEST);
        assertEquals(TEST, uniqueEventList.get(TEST.getName()));
    }

    @Test
    public void get_nonExistingEvent_returnsNull() {
        assertEquals(null, uniqueEventList.get(new EventName("event name does not exist")));
    }

    @Test
    public void setEvent_nullTargetEvent_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueEventList.setEvent(null, TEST));
    }

    @Test
    public void setEvent_nullEditedEvent_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueEventList.setEvent(TEST, null));
    }

    @Test
    public void setEvent_targetEventNotInList_throwsEventNotFoundException() {
        assertThrows(EventNotFoundException.class, () -> uniqueEventList.setEvent(TEST, TEST));
    }

    @Test
    public void setEvent_editedEventIsSameEvent_success() {
        uniqueEventList.add(TEST);
        uniqueEventList.setEvent(TEST, TEST);
        UniqueEventList expectedUniqueEventList = new UniqueEventList();
        expectedUniqueEventList.add(TEST);
        assertEquals(expectedUniqueEventList, uniqueEventList);
    }

    @Test
    public void setEvent_editedEventHasSameIdentity_success() {
        uniqueEventList.add(TEST);
        Event editedTest = new EventBuilder(TEST).withDate(VALID_EVENTDATE_FINAL).withTime(VALID_EVENTTIME_FINAL)
                .build();
        uniqueEventList.setEvent(TEST, editedTest);
        UniqueEventList expectedUniqueEventList = new UniqueEventList();
        expectedUniqueEventList.add(editedTest);
        assertEquals(expectedUniqueEventList, uniqueEventList);
    }

    @Test
    public void setEvent_editedEventHasDifferentIdentity_success() {
        uniqueEventList.add(TEST);
        uniqueEventList.setEvent(TEST, FINAL);
        UniqueEventList expectedUniqueEventList = new UniqueEventList();
        expectedUniqueEventList.add(FINAL);
        assertEquals(expectedUniqueEventList, uniqueEventList);
    }

    @Test
    public void setEvent_editedEventHasNonUniqueIdentity_throwsDuplicateEventException() {
        uniqueEventList.add(TEST);
        uniqueEventList.add(FINAL);
        assertThrows(DuplicateEventException.class, () -> uniqueEventList.setEvent(TEST, FINAL));
    }

    @Test
    public void remove_nullEvent_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueEventList.remove(null));
    }

    @Test
    public void remove_eventDoesNotExist_throwsEventNotFoundException() {
        assertThrows(EventNotFoundException.class, () -> uniqueEventList.remove(TEST));
    }

    @Test
    public void remove_existingEvent_removesEvent() {
        uniqueEventList.add(TEST);
        uniqueEventList.remove(TEST);
        UniqueEventList expectedUniqueEventList = new UniqueEventList();
        assertEquals(expectedUniqueEventList, uniqueEventList);
    }

    @Test
    public void setEvents_nullUniqueEventList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueEventList.setEvents((UniqueEventList) null));
    }

    @Test
    public void setEvents_uniqueEventList_replacesOwnListWithProvidedUniqueEventList() {
        uniqueEventList.add(TEST);
        UniqueEventList expectedUniqueEventList = new UniqueEventList();
        expectedUniqueEventList.add(FINAL);
        uniqueEventList.setEvents(expectedUniqueEventList);
        assertEquals(expectedUniqueEventList, uniqueEventList);
    }

    @Test
    public void setEvents_nullList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueEventList.setEvents((List<Event>) null));
    }

    @Test
    public void setEvents_list_replacesOwnListWithProvidedList() {
        uniqueEventList.add(TEST);
        List<Event> eventList = Collections.singletonList(FINAL);
        uniqueEventList.setEvents(eventList);
        UniqueEventList expectedUniqueEventList = new UniqueEventList();
        expectedUniqueEventList.add(FINAL);
        assertEquals(expectedUniqueEventList, uniqueEventList);
    }

    @Test
    public void setEvents_listWithDuplicateEvents_throwsDuplicateEventException() {
        List<Event> listWithDuplicateEvents = Arrays.asList(TEST, TEST);
        assertThrows(DuplicateEventException.class, () -> uniqueEventList.setEvents(listWithDuplicateEvents));
    }

    @Test
    public void asUnmodifiableObservableList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () ->
                uniqueEventList.asUnmodifiableObservableList().remove(0));
    }

}
