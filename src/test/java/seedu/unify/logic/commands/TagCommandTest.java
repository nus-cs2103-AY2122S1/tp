package seedu.unify.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.unify.testutil.TypicalIndexes.INDEX_FIRST_TASK;
import static seedu.unify.testutil.TypicalIndexes.INDEX_SECOND_TASK;

import org.junit.jupiter.api.Test;

public class TagCommandTest {
    @Test
    public void equals() {
        TagCommand.TagTaskDescriptor tagTaskDescriptor = new TagCommand.TagTaskDescriptor();
        TagCommand firstTag = new TagCommand(INDEX_FIRST_TASK, tagTaskDescriptor);
        TagCommand secondTag = new TagCommand(INDEX_SECOND_TASK, tagTaskDescriptor);


        // same object -> returns true
        assertTrue(firstTag.equals(firstTag));

        // same values -> returns true
        TagCommand firstCopy = new TagCommand(INDEX_FIRST_TASK, tagTaskDescriptor);
        assertTrue(firstTag.equals(firstCopy));

        // different types -> returns false
        assertFalse(firstTag.equals(INDEX_FIRST_TASK));

        // null -> returns false
        assertFalse(firstTag.equals(null));

        // different tasks -> returns false
        assertFalse(firstTag.equals(secondTag));
    }
}
