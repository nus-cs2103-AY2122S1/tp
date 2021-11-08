package seedu.address.logic.interview;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.interview.CommandTestUtil.DESC_INTERVIEW_ADMIN_ASSISTANT;
import static seedu.address.logic.interview.CommandTestUtil.DESC_INTERVIEW_MANAGER;
import static seedu.address.logic.interview.CommandTestUtil.VALID_DATE_OTHER_DATE;
import static seedu.address.logic.interview.CommandTestUtil.VALID_DURATION_TIME_OTHER_DURATION;
import static seedu.address.logic.interview.CommandTestUtil.VALID_POSITION_MANAGER_NAME;
import static seedu.address.logic.interview.CommandTestUtil.VALID_STATUS_COMPLETED;
import static seedu.address.logic.interview.CommandTestUtil.VALID_TIME_OTHER_TIME;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.EditInterviewDescriptorBuilder;

public class EditInterviewDescriptorTest {

    @Test
    public void equals() {
        // same values -> returns true
        EditInterviewCommand.EditInterviewDescriptor descriptorWithSameValues =
                new EditInterviewCommand.EditInterviewDescriptor(DESC_INTERVIEW_ADMIN_ASSISTANT);
        assertTrue(DESC_INTERVIEW_ADMIN_ASSISTANT.equals(descriptorWithSameValues));

        // same object -> returns true
        assertTrue(DESC_INTERVIEW_ADMIN_ASSISTANT.equals(DESC_INTERVIEW_ADMIN_ASSISTANT));

        // null -> returns false
        assertFalse(DESC_INTERVIEW_ADMIN_ASSISTANT.equals(null));

        // different types -> returns false
        assertFalse(DESC_INTERVIEW_ADMIN_ASSISTANT.equals(5));

        // different values -> returns false
        assertFalse(DESC_INTERVIEW_ADMIN_ASSISTANT.equals(DESC_INTERVIEW_MANAGER));

        // different position -> returns false
        EditInterviewCommand.EditInterviewDescriptor editedInterview =
                new EditInterviewDescriptorBuilder(DESC_INTERVIEW_ADMIN_ASSISTANT)
                        .withPosition(VALID_POSITION_MANAGER_NAME).build();
        assertFalse(DESC_INTERVIEW_ADMIN_ASSISTANT.equals(editedInterview));

        // different date -> returns false
        editedInterview = new EditInterviewDescriptorBuilder(DESC_INTERVIEW_ADMIN_ASSISTANT)
                .withDate(VALID_DATE_OTHER_DATE).build();
        assertFalse(DESC_INTERVIEW_ADMIN_ASSISTANT.equals(editedInterview));

        // different time -> returns false
        editedInterview = new EditInterviewDescriptorBuilder(DESC_INTERVIEW_ADMIN_ASSISTANT)
                .withStartTime(VALID_TIME_OTHER_TIME).build();
        assertFalse(DESC_INTERVIEW_ADMIN_ASSISTANT.equals(editedInterview));

        // different duration -> returns false
        editedInterview = new EditInterviewDescriptorBuilder(DESC_INTERVIEW_ADMIN_ASSISTANT)
                .withDuration(VALID_DURATION_TIME_OTHER_DURATION).build();
        assertFalse(DESC_INTERVIEW_ADMIN_ASSISTANT.equals(editedInterview));

        // different status -> return false
        editedInterview = new EditInterviewDescriptorBuilder(DESC_INTERVIEW_ADMIN_ASSISTANT)
                .withStatus(VALID_STATUS_COMPLETED).build();
        assertFalse(DESC_INTERVIEW_ADMIN_ASSISTANT.equals(editedInterview));
    }
}
