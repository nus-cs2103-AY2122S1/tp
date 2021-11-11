package nustracker.model.event;

import static nustracker.logic.commands.CommandTestUtil.VALID_EVENTDATE_FINAL;
import static nustracker.logic.commands.CommandTestUtil.VALID_EVENTNAME_FINAL;
import static nustracker.logic.commands.CommandTestUtil.VALID_EVENTTIME_FINAL;
import static nustracker.logic.commands.CommandTestUtil.VALID_PARTICIPANT_FINAL;
import static nustracker.logic.commands.CommandTestUtil.VALID_PARTICIPANT_TEST;
import static nustracker.testutil.Assert.assertThrows;
import static nustracker.testutil.TypicalAddressBook.getTypicalAddressBook;
import static nustracker.testutil.TypicalEvents.FINAL;
import static nustracker.testutil.TypicalEvents.ORIENTATION;
import static nustracker.testutil.TypicalEvents.TEST;
import static nustracker.testutil.TypicalStudents.ALICE;
import static nustracker.testutil.TypicalStudents.BENSON;
import static nustracker.testutil.TypicalStudents.CARL;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import nustracker.model.student.Student;
import nustracker.model.student.StudentId;
import nustracker.testutil.EventBuilder;

class EventTest {

    @Test
    public void asObservableList_modifyList_throwsUnsupportedOperationException() {
        Event event = new EventBuilder().build();
        assertThrows(UnsupportedOperationException.class, () -> event.getParticipants().remove(0));
        assertThrows(UnsupportedOperationException.class, () -> event.getBlacklist().remove(0));
    }

    @Test
    public void getParticipantsAsStudents() {
        String[] participants = { ALICE.getStudentId().toString(), BENSON.getStudentId().toString(),
                CARL.getStudentId().toString() };
        Event event = new EventBuilder().withParticipants(participants).build();
        Set<Student> studentsFromParticipants = event.getParticipantsAsStudents(getTypicalAddressBook());

        Student[] students = {ALICE, BENSON, CARL};

        assertEquals(studentsFromParticipants, new HashSet<>(Arrays.asList(students)));
    }

    @Test
    public void isInEvent() {
        assertTrue(TEST.isInEvent(new StudentId(VALID_PARTICIPANT_TEST)));
        assertFalse(FINAL.isInEvent(new StudentId(VALID_PARTICIPANT_TEST)));
        assertFalse(FINAL.isInEvent(null));
    }

    @Test
    public void isBlacklisted() {
        Event event = new EventBuilder().withBlacklist("e9123119").build();
        assertTrue(event.isBlacklisted(new StudentId("e9123119")));
        assertFalse(event.isBlacklisted(new StudentId("e9000000")));
        assertFalse(event.isBlacklisted(null));
    }

    @Test
    public void getNewEventWithUpdatedParticipants() {
        String[] newParticipants = {"e1234567", "e0123456"};
        Event event = new EventBuilder(ORIENTATION).withParticipants(newParticipants).build();
        assertEquals(event,
                ORIENTATION.getNewEventWithUpdatedParticipants(
                        Arrays.stream(newParticipants)
                                .map(Participant::new)
                                .collect(Collectors.toSet())
                )
        );
    }

    @Test
    public void pseudoEvent() {
        Event event = new EventBuilder().withName("Pseudo test").build();
        Event pseudoEvent = Event.pseudoEvent(new EventName("Pseudo test"));
        assertTrue(pseudoEvent.isSameEvent(event));
    }

    @Test
    public void isSameEvent() {
        // same object -> returns true
        assertTrue(TEST.isSameEvent(TEST));

        // null -> returns false
        assertFalse(TEST.isSameEvent(null));

        // same name, all other attributes different -> returns true
        Event editedTest = new EventBuilder(TEST).withDate(VALID_EVENTDATE_FINAL).withTime(VALID_EVENTTIME_FINAL)
                .withParticipants(VALID_PARTICIPANT_FINAL).build();
        assertTrue(TEST.isSameEvent(editedTest));

        // different name, all other attributes same -> returns false
        editedTest = new EventBuilder(TEST).withName(VALID_EVENTNAME_FINAL).build();
        assertFalse(TEST.isSameEvent(editedTest));

        // name differs in case, all other attributes same -> returns true
        Event editedFinal = new EventBuilder(FINAL).withName(VALID_EVENTNAME_FINAL.toLowerCase()).build();
        assertTrue(FINAL.isSameEvent(editedFinal));

        // name has trailing spaces, all other attributes same -> returns false
        String nameWithTrailingSpaces = VALID_EVENTNAME_FINAL + " ";
        editedFinal = new EventBuilder(FINAL).withName(nameWithTrailingSpaces).build();
        assertFalse(FINAL.isSameEvent(editedFinal));
    }

    @Test
    public void equals() {
        // same values -> returns true
        Event testCopy = new EventBuilder(TEST).build();
        assertTrue(TEST.equals(testCopy));

        // same object -> returns true
        assertTrue(TEST.equals(TEST));

        // null -> returns false
        assertFalse(TEST.equals(null));

        // different type -> returns false
        assertFalse(TEST.equals(5));

        // different event -> returns false
        assertFalse(TEST.equals(FINAL));

        // different name -> returns false
        Event editedTest = new EventBuilder(TEST).withName(VALID_EVENTNAME_FINAL).build();
        assertFalse(TEST.equals(editedTest));

        // different date -> returns false
        editedTest = new EventBuilder(TEST).withDate(VALID_EVENTDATE_FINAL).build();
        assertFalse(TEST.equals(editedTest));

        // different time -> returns false
        editedTest = new EventBuilder(TEST).withTime(VALID_EVENTTIME_FINAL).build();
        assertFalse(TEST.equals(editedTest));

        // different participants -> returns false
        editedTest = new EventBuilder(TEST).withParticipants(VALID_PARTICIPANT_FINAL).build();
        assertFalse(TEST.equals(editedTest));

        // different blacklist -> returns false
        editedTest = new EventBuilder(TEST).withBlacklist(VALID_PARTICIPANT_TEST).build();
        assertFalse(TEST.equals(editedTest));
    }

    @Test
    public void toStringTest() {
        String testEvent = "Test event; Date: Oct 09 2021; Time: 06:00 PM; Participants: e1234567; Blacklist: e0544182";
        assertEquals(testEvent, TEST.toString());
    }


}
