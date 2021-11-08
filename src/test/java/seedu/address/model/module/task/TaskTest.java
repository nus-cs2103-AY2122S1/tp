package seedu.address.model.module.task;

import static seedu.address.logic.commands.CommandTestUtil.VALID_POEM_TASK_DEADLINE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_POEM_TASK_NAME;
import static seedu.address.testutil.TypicalTasks.MEETING;
import static seedu.address.testutil.TypicalTasks.MEETING_DONE;
import static seedu.address.testutil.TypicalTasks.PROJECT;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import seedu.address.testutil.TaskBuilder;

class TaskTest {

    @Test
    public void isSameType() {
        // same name, same deadline, same status -> returns true
        Task meetingCopy = new TaskBuilder(MEETING).build();
        Assertions.assertTrue(MEETING.isSameType(meetingCopy));

        // same name, same deadline, different status -> returns true
        Assertions.assertTrue(MEETING.isSameType(MEETING_DONE));

        // different name -> returns false
        Task editedTask1 = new TaskBuilder(MEETING).withName(VALID_POEM_TASK_NAME).build();
        Assertions.assertFalse(MEETING.isSameType(editedTask1));

        // different deadline -< returns false
        Task editedTask2 = new TaskBuilder(MEETING).withDeadline("12/12/2022 23:59").build();
        Assertions.assertFalse(MEETING.isSameType(editedTask2));
    }

    @Test
    public void equals() {
        // same values -> returns true
        Task meetingCopy = new TaskBuilder(MEETING).build();
        Assertions.assertTrue(MEETING.equals(meetingCopy));

        // same object -> returns true
        Assertions.assertTrue(MEETING.equals(MEETING));

        // null -> returns false
        Assertions.assertFalse(MEETING.equals(null));

        // different type -> returns false
        Assertions.assertFalse(MEETING.equals(5));

        // different task -> returns false
        Assertions.assertFalse(MEETING.equals(PROJECT));

        // different name -> returns false
        Task editedTask = new TaskBuilder(MEETING).withName(VALID_POEM_TASK_NAME).build();
        Assertions.assertFalse(MEETING.equals(editedTask));

        // different done status -> returns false
        Assertions.assertFalse(MEETING.equals(MEETING_DONE));

        // different deadline -> return false
        Task editedDeadlineMeeting = new TaskBuilder(MEETING).withDeadline(VALID_POEM_TASK_DEADLINE).build();
        Assertions.assertFalse(MEETING.equals(editedDeadlineMeeting));
    }

}
