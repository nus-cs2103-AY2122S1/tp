package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.DESC_MEETING;
import static seedu.address.logic.commands.CommandTestUtil.DESC_RUN;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TASK_DATE_1;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TASK_NAME_TASK1;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TASK_TIME_1;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TASK_VENUE_1;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.EditTaskCommand.EditTaskDescriptor;
import seedu.address.testutil.EditTaskDescriptorBuilder;

public class EditTaskDescriptorTest {

    @Test
    public void equals() {
        // same values -> return true
        EditTaskDescriptor descriptorWithSameValues = new EditTaskDescriptor(DESC_MEETING);
        assertTrue(DESC_MEETING.equals(descriptorWithSameValues));

        // same object -> return true
        assertTrue(DESC_MEETING.equals(DESC_MEETING));

        // null -> return false
        assertFalse(DESC_MEETING.equals(null));

        // different types -> return false
        assertFalse(DESC_MEETING.equals("STRING"));

        // different values -> return false
        assertFalse(DESC_MEETING.equals(DESC_RUN));

        // different task name -> return false
        EditTaskDescriptor editedMeeting = new EditTaskDescriptorBuilder(DESC_MEETING)
                .withTaskName(VALID_TASK_NAME_TASK1).build();
        assertFalse(DESC_MEETING.equals(editedMeeting));

        // different task date -> return false
        editedMeeting = new EditTaskDescriptorBuilder(DESC_MEETING)
                .withTaskDate(VALID_TASK_DATE_1).build();
        assertFalse(DESC_MEETING.equals(editedMeeting));

        // different task time -> return false
        editedMeeting = new EditTaskDescriptorBuilder(DESC_MEETING)
                .withTaskTime(VALID_TASK_TIME_1).build();
        assertFalse(DESC_MEETING.equals(editedMeeting));

        // different task venue -> return false
        editedMeeting = new EditTaskDescriptorBuilder(DESC_MEETING)
                .withVenue(VALID_TASK_VENUE_1).build();
        assertFalse(DESC_MEETING.equals(editedMeeting));
    }
}
