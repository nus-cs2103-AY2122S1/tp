package seedu.address.logic.position;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.position.CommandTestUtil.DESC_ADMIN_ASSISTANT;
import static seedu.address.logic.position.CommandTestUtil.DESC_BOOKKEEPER;
import static seedu.address.logic.position.CommandTestUtil.VALID_STATUS_OPEN;
import static seedu.address.logic.position.CommandTestUtil.VALID_TITLE_BOOKKEEPER;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.EditPositionDescriptorBuilder;

public class EditPositionDescriptorTest {

    @Test
    public void equals() {
        // same values -> returns true
        EditPositionCommand.EditPositionDescriptor descriptorWithSameValues =
                new EditPositionCommand.EditPositionDescriptor(DESC_ADMIN_ASSISTANT);
        assertTrue(DESC_ADMIN_ASSISTANT.equals(descriptorWithSameValues));

        // same object -> returns true
        assertTrue(DESC_ADMIN_ASSISTANT.equals(DESC_ADMIN_ASSISTANT));

        // null -> returns false
        assertFalse(DESC_ADMIN_ASSISTANT.equals(null));

        // different types -> returns false
        assertFalse(DESC_ADMIN_ASSISTANT.equals(5));

        // different values -> returns false
        assertFalse(DESC_ADMIN_ASSISTANT.equals(DESC_BOOKKEEPER));

        // different title -> returns false
        EditPositionCommand.EditPositionDescriptor editedAdminAssistant =
                new EditPositionDescriptorBuilder(DESC_ADMIN_ASSISTANT).withTitle(VALID_TITLE_BOOKKEEPER).build();
        assertFalse(DESC_ADMIN_ASSISTANT.equals(editedAdminAssistant));

        // different status -> returns false
        editedAdminAssistant =
                new EditPositionDescriptorBuilder(DESC_ADMIN_ASSISTANT).withPositionStatus(VALID_STATUS_OPEN).build();
        assertFalse(DESC_ADMIN_ASSISTANT.equals(editedAdminAssistant));

    }
}
