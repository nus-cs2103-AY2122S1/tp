package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.friends.EditFriendCommand;
import seedu.address.testutil.EditFriendDescriptorBuilder;

public class EditFriendDescriptorTest {

    @Test
    public void equals() {
        // same values -> returns true
        EditFriendCommand.EditFriendDescriptor descriptorWithSameValues =
                new EditFriendCommand.EditFriendDescriptor(DESC_AMY);
        assertTrue(DESC_AMY.equals(descriptorWithSameValues));

        // same object -> returns true
        assertTrue(DESC_AMY.equals(DESC_AMY));

        // null -> returns false
        assertFalse(DESC_AMY.equals(null));

        // different types -> returns false
        assertFalse(DESC_AMY.equals(5));

        // different values -> returns false
        assertFalse(DESC_AMY.equals(DESC_BOB));

        // different name -> returns false
        EditFriendCommand.EditFriendDescriptor editedAmy =
                new EditFriendDescriptorBuilder(DESC_AMY).withFriendName(VALID_NAME_BOB).build();
        assertFalse(DESC_AMY.equals(editedAmy));
    }
}
