package seedu.address.model.meeting;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TITLE_1;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalAddressBookObjects.BIOLOGY_CONSULT;
import static seedu.address.testutil.TypicalAddressBookObjects.PRESENTATION;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.model.meeting.exceptions.MeetingConflictException;
import seedu.address.model.meeting.exceptions.MeetingNotFoundException;
import seedu.address.testutil.MeetingBuilder;

public class NonConflictMeetingListTest {

    private final NonConflictMeetingList nonConflictMeetingList = new NonConflictMeetingList();

    @Test
    public void contains_conflictingMeeting_returnsTrue() {
        nonConflictMeetingList.add(PRESENTATION);
        Meeting editedPresentation = new MeetingBuilder(PRESENTATION).withTitle(VALID_TITLE_1)
                .build();
        assertTrue(nonConflictMeetingList.hasConflictWith(editedPresentation));
    }

    @Test
    public void add_nullMeeting_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> nonConflictMeetingList.add(null));
    }

    @Test
    public void add_duplicateMeeting_throwsMeetingConflictException() {
        nonConflictMeetingList.add(PRESENTATION);
        assertThrows(MeetingConflictException.class, () -> nonConflictMeetingList.add(PRESENTATION));
    }

    @Test
    public void remove_nullMeeting_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> nonConflictMeetingList.remove(null));
    }

    @Test
    public void remove_meetingDoesNotExist_throwsMeetingNotFoundException() {
        assertThrows(MeetingNotFoundException.class, () -> nonConflictMeetingList.remove(PRESENTATION));
    }

    @Test
    public void remove_existingMeeting_removesMeeting() {
        nonConflictMeetingList.add(PRESENTATION);
        nonConflictMeetingList.remove(PRESENTATION);
        NonConflictMeetingList expectedNonConflictMeetingList = new NonConflictMeetingList();
        assertEquals(expectedNonConflictMeetingList, nonConflictMeetingList);
    }

    @Test
    public void setMeetings_nullList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> nonConflictMeetingList.setMeetings((List<Meeting>) null));
    }

    @Test
    public void setMeetings_list_replacesOwnListWithProvidedList() {
        nonConflictMeetingList.add(PRESENTATION);
        List<Meeting> meetingList = Collections.singletonList(BIOLOGY_CONSULT);
        nonConflictMeetingList.setMeetings(meetingList);
        NonConflictMeetingList expectedNonConflictMeetingList = new NonConflictMeetingList();
        expectedNonConflictMeetingList.add(BIOLOGY_CONSULT);
        assertEquals(expectedNonConflictMeetingList, nonConflictMeetingList);
    }

    @Test
    public void setMeetings_listWithDuplicateMeetings_throwsMeetingConflictException() {
        List<Meeting> listWithConflictingMeetings = Arrays.asList(PRESENTATION, PRESENTATION);
        assertThrows(MeetingConflictException.class, () ->
                nonConflictMeetingList.setMeetings(listWithConflictingMeetings));
    }

    @Test
    public void asUnmodifiableObservableList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () ->
                nonConflictMeetingList.asUnmodifiableObservableList().remove(0));
    }
}

