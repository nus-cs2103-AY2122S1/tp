package seedu.unify.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.unify.logic.commands.CommandTestUtil.DESC_ASSIGNMENT;
import static seedu.unify.logic.commands.CommandTestUtil.DESC_QUIZ;
import static seedu.unify.logic.commands.CommandTestUtil.VALID_DATE_QUIZ;
import static seedu.unify.logic.commands.CommandTestUtil.VALID_NAME_QUIZ;
import static seedu.unify.logic.commands.CommandTestUtil.VALID_TAG_CCA;
import static seedu.unify.logic.commands.CommandTestUtil.VALID_TIME_QUIZ;

import org.junit.jupiter.api.Test;

import seedu.unify.logic.commands.EditCommand.EditTaskDescriptor;
import seedu.unify.testutil.EditTaskDescriptorBuilder;

public class EditTaskDescriptorTest {

    @Test
    public void equals() {
        // same values -> returns true
        EditTaskDescriptor descriptorWithSameValues = new EditTaskDescriptor(DESC_ASSIGNMENT);
        assertTrue(DESC_ASSIGNMENT.equals(descriptorWithSameValues));

        // same object -> returns true
        assertTrue(DESC_ASSIGNMENT.equals(DESC_ASSIGNMENT));

        // null -> returns false
        assertFalse(DESC_ASSIGNMENT.equals(null));

        // different types -> returns false
        assertFalse(DESC_ASSIGNMENT.equals(5));

        // different values -> returns false
        assertFalse(DESC_ASSIGNMENT.equals(DESC_QUIZ));

        // different name -> returns false
        EditTaskDescriptor editedAssignment = new EditTaskDescriptorBuilder(DESC_ASSIGNMENT)
                .withName(VALID_NAME_QUIZ).build();
        assertFalse(DESC_ASSIGNMENT.equals(editedAssignment));

        // different phone -> returns false
        editedAssignment = new EditTaskDescriptorBuilder(DESC_ASSIGNMENT).withTime(VALID_TIME_QUIZ).build();
        assertFalse(DESC_ASSIGNMENT.equals(editedAssignment));

        // different date -> returns false
        editedAssignment = new EditTaskDescriptorBuilder(DESC_ASSIGNMENT).withDate(VALID_DATE_QUIZ).build();
        assertFalse(DESC_ASSIGNMENT.equals(editedAssignment));

        // different tags -> returns false
        editedAssignment = new EditTaskDescriptorBuilder(DESC_ASSIGNMENT).withTags(VALID_TAG_CCA).build();
        assertFalse(DESC_ASSIGNMENT.equals(editedAssignment));
    }
}
