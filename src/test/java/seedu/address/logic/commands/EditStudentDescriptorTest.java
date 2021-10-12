package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.EDIT_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.EDIT_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_GROUP_RECITATION;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ID_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_SCORES_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.EditCommand.EditStudentDescriptor;
import seedu.address.testutil.EditStudentDescriptorBuilder;

public class EditStudentDescriptorTest {

    @Test
    public void equals() {
        // same values -> returns true
        EditStudentDescriptor descriptorWithSameValues = new EditStudentDescriptor(EDIT_DESC_AMY);
        assertTrue(EDIT_DESC_AMY.equals(descriptorWithSameValues));

        // same object -> returns true
        assertTrue(EDIT_DESC_AMY.equals(EDIT_DESC_AMY));

        // null -> returns false
        assertFalse(EDIT_DESC_AMY.equals(null));

        // different types -> returns false
        assertFalse(EDIT_DESC_AMY.equals(5));

        // different values -> returns false
        assertFalse(EDIT_DESC_AMY.equals(EDIT_DESC_BOB));

        // different name -> returns false
        EditStudentDescriptor editedAmy = new EditStudentDescriptorBuilder(EDIT_DESC_AMY).withName(VALID_NAME_BOB).build();
        assertFalse(EDIT_DESC_AMY.equals(editedAmy));

        // different ID -> returns false
        editedAmy = new EditStudentDescriptorBuilder(EDIT_DESC_AMY).withId(VALID_ID_BOB).build();
        assertFalse(EDIT_DESC_AMY.equals(editedAmy));

        // different groups -> returns false
        editedAmy = new EditStudentDescriptorBuilder(EDIT_DESC_AMY).withGroups(VALID_GROUP_RECITATION).build();
        assertFalse(EDIT_DESC_AMY.equals(editedAmy));

        // different scores -> returns false
        editedAmy = new EditStudentDescriptorBuilder(EDIT_DESC_AMY).withScores(VALID_SCORES_BOB).build();
        assertFalse(EDIT_DESC_AMY.equals(editedAmy));

        // different tags -> returns false
        editedAmy = new EditStudentDescriptorBuilder(EDIT_DESC_AMY).withTags(VALID_TAG_HUSBAND).build();
        assertFalse(EDIT_DESC_AMY.equals(editedAmy));
    }
}
