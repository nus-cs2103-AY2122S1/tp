package seedu.smartnus.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.smartnus.logic.commands.CommandTestUtil.DESC_AMY;
import static seedu.smartnus.logic.commands.CommandTestUtil.DESC_BOB;
import static seedu.smartnus.logic.commands.CommandTestUtil.VALID_IMPORTANCE_2;
import static seedu.smartnus.logic.commands.CommandTestUtil.VALID_QUESTION_4;
import static seedu.smartnus.logic.commands.CommandTestUtil.VALID_TAG_3;

import org.junit.jupiter.api.Test;

import seedu.smartnus.logic.commands.EditCommand.EditQuestionDescriptor;
import seedu.smartnus.testutil.EditQuestionDescriptorBuilder;

public class EditQuestionDescriptorTest {

    @Test
    public void equals() {
        // same values -> returns true
        EditCommand.EditQuestionDescriptor descriptorWithSameValues = new EditQuestionDescriptor(DESC_AMY);
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
        EditCommand.EditQuestionDescriptor editedAmy = new EditQuestionDescriptorBuilder(DESC_AMY)
                .withName(VALID_QUESTION_4).build();
        assertFalse(DESC_AMY.equals(editedAmy));

        // different importance -> returns false
        editedAmy = new EditQuestionDescriptorBuilder(DESC_AMY).withImportance(VALID_IMPORTANCE_2).build();
        assertFalse(DESC_AMY.equals(editedAmy));

        // different tags -> returns false
        editedAmy = new EditQuestionDescriptorBuilder(DESC_AMY).withTags(VALID_TAG_3).build();
        assertFalse(DESC_AMY.equals(editedAmy));
    }
}
