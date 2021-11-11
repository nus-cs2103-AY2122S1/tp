package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.DESC_AMAZON;
import static seedu.address.logic.commands.CommandTestUtil.DESC_BYTEDANCE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DEADLINE_BYTEDANCE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_INTERVIEW_DATE_AND_TIME_BYTEDANCE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BYTEDANCE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_POSITION_BYTEDANCE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PRIORITY_BYTEDANCE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_REQUIREMENTS_BYTEDANCE;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.EditApplicationDescriptorBuilder;

public class EditApplicationDescriptorTest {

    @Test
    public void equals() {
        // same values -> returns true
        EditCommand.EditApplicationDescriptor descriptorWithSameValues = new EditCommand
                .EditApplicationDescriptor(DESC_AMAZON);
        assertTrue(DESC_AMAZON.equals(descriptorWithSameValues));

        // same object -> returns true
        assertTrue(DESC_AMAZON.equals(DESC_AMAZON));

        // null -> returns false
        assertFalse(DESC_AMAZON.equals(null));

        // different types -> returns false
        assertFalse(DESC_AMAZON.equals(5));

        // different values -> returns false
        assertFalse(DESC_AMAZON.equals(DESC_BYTEDANCE));

        // different name -> returns false
        EditCommand.EditApplicationDescriptor editedAmazon = new EditApplicationDescriptorBuilder(DESC_AMAZON)
                .withCompany(VALID_NAME_BYTEDANCE).build();
        assertFalse(DESC_AMAZON.equals(editedAmazon));

        // different position -> returns false
        editedAmazon = new EditApplicationDescriptorBuilder(DESC_AMAZON).withPosition(VALID_POSITION_BYTEDANCE).build();
        assertFalse(DESC_AMAZON.equals(editedAmazon));

        // different deadline -> returns false
        editedAmazon = new EditApplicationDescriptorBuilder(DESC_AMAZON).withDeadline(VALID_DEADLINE_BYTEDANCE).build();
        assertFalse(DESC_AMAZON.equals(editedAmazon));

        // different priority -> returns false
        editedAmazon = new EditApplicationDescriptorBuilder(DESC_AMAZON).withPriority(VALID_PRIORITY_BYTEDANCE).build();
        assertFalse(DESC_AMAZON.equals(editedAmazon));

        // different requirements -> returns false
        editedAmazon = new EditApplicationDescriptorBuilder(DESC_AMAZON)
                .withRequirements(VALID_REQUIREMENTS_BYTEDANCE).build();
        assertFalse(DESC_AMAZON.equals(editedAmazon));

        // different interview date and time -> returns false
        editedAmazon = new EditApplicationDescriptorBuilder(DESC_AMAZON)
                .withInterviewDateAndTime(VALID_INTERVIEW_DATE_AND_TIME_BYTEDANCE).build();
        assertFalse(DESC_AMAZON.equals(editedAmazon));
    }
}
