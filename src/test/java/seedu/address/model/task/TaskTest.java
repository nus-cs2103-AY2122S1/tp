package seedu.address.model.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static seedu.address.testutil.TypicalTasks.DISCUSSION;
import static seedu.address.testutil.TypicalTasks.MEETING;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.ClearCommand;
import seedu.address.testutil.TaskBuilder;

public class TaskTest {

    @Test
    public void equals() {
        // same values -> return true
        Task meetingCopy = new TaskBuilder(MEETING).build();
        assertEquals(MEETING, meetingCopy);

        // same object -> returns true
        assertEquals(MEETING, MEETING);

        // null -> returns false
        assertNotEquals(null, MEETING);

        // different type -> returns false
        assertNotEquals(new ClearCommand(), MEETING);

        // different task -> returns false
        assertNotEquals(MEETING, DISCUSSION);

        // different name -> returns false
        Task editedMeeting = new TaskBuilder(MEETING).withName("Evening Run").build();
        assertNotEquals(editedMeeting, MEETING);

        // different date -> returns false
        editedMeeting = new TaskBuilder(MEETING).withDate("2021-10-10").build();
        assertNotEquals(editedMeeting, MEETING);

        // different time -> returns false
        editedMeeting = new TaskBuilder(MEETING).withTime("18:00").build();
        assertNotEquals(editedMeeting, MEETING);

        // different venue -> returns false
        editedMeeting = new TaskBuilder(MEETING).withVenue("Park").build();
        assertNotEquals(editedMeeting, MEETING);

        // different isDone -> returns false
        editedMeeting = new TaskBuilder(MEETING).withDone(true).build();
        assertNotEquals(editedMeeting, MEETING);
    }
}
