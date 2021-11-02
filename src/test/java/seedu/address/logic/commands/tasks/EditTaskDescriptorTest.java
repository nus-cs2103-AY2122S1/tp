package seedu.address.logic.commands.tasks;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.DESC_QUIZ1;
import static seedu.address.logic.commands.CommandTestUtil.DESC_QUIZ2;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DEADLINE_QUIZ2;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DESCRIPTION_QUIZ2;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.tasks.EditTaskCommand.EditTaskDescriptor;
import seedu.address.testutil.EditTaskDescriptorBuilder;

public class EditTaskDescriptorTest {

    @Test
    public void equals() {
        // same values -> returns true
        EditTaskCommand.EditTaskDescriptor descriptorWithSameValues = new EditTaskDescriptor(DESC_QUIZ1);
        assertTrue(DESC_QUIZ1.equals(descriptorWithSameValues));

        // same object -> returns true
        assertTrue(DESC_QUIZ1.equals(DESC_QUIZ1));

        // null -> returns false
        assertFalse(DESC_QUIZ1.equals(null));

        // different types -> returns false
        assertFalse(DESC_QUIZ1.equals(5));

        // different values -> returns false
        assertFalse(DESC_QUIZ1.equals(DESC_QUIZ2));

        // different description -> returns false
        EditTaskDescriptor editedQuiz1 = new EditTaskDescriptorBuilder(DESC_QUIZ1)
                .withDescription(VALID_DESCRIPTION_QUIZ2).build();
        assertFalse(DESC_QUIZ1.equals(editedQuiz1));

        // different deadline -> returns false
        editedQuiz1 = new EditTaskDescriptorBuilder(DESC_QUIZ1).withDeadline(VALID_DEADLINE_QUIZ2).build();
        assertFalse(DESC_QUIZ1.equals(editedQuiz1));

    }
}
