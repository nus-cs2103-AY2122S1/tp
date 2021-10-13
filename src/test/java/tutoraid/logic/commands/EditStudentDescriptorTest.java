package tutoraid.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static tutoraid.logic.commands.CommandTestUtil.DESC_AMY;
import static tutoraid.logic.commands.CommandTestUtil.DESC_BOB;
import static tutoraid.logic.commands.CommandTestUtil.VALID_PARENT_NAME_BOB;
import static tutoraid.logic.commands.CommandTestUtil.VALID_PARENT_PHONE_BOB;
import static tutoraid.logic.commands.CommandTestUtil.VALID_STUDENT_NAME_BOB;
import static tutoraid.logic.commands.CommandTestUtil.VALID_STUDENT_PHONE_BOB;

import org.junit.jupiter.api.Test;
import tutoraid.logic.commands.EditStudentCommand.EditStudentDescriptor;
import tutoraid.testutil.EditStudentDescriptorBuilder;

public class EditStudentDescriptorTest {

    @Test
    public void equals() {
        // same values -> returns true
        EditStudentDescriptor descriptorWithSameValues = new EditStudentDescriptor(DESC_AMY);
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
        EditStudentDescriptor editedAmy = new EditStudentDescriptorBuilder(DESC_AMY)
                .withStudentName(VALID_STUDENT_NAME_BOB)
                .build();
        assertFalse(DESC_AMY.equals(editedAmy));

        // different student phone -> returns false
        editedAmy = new EditStudentDescriptorBuilder(DESC_AMY)
                .withStudentPhone(VALID_STUDENT_PHONE_BOB)
                .build();
        assertFalse(DESC_AMY.equals(editedAmy));

        // different parent name -> returns false
        editedAmy = new EditStudentDescriptorBuilder(DESC_AMY)
                .withParentName(VALID_PARENT_NAME_BOB)
                .build();
        assertFalse(DESC_AMY.equals(editedAmy));

        // different parent phone -> returns false
        editedAmy = new EditStudentDescriptorBuilder(DESC_AMY)
                .withParentPhone(VALID_PARENT_PHONE_BOB)
                .build();
        assertFalse(DESC_AMY.equals(editedAmy));
    }
}
