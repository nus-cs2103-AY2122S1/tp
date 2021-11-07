package seedu.unify.logic.commands;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

import seedu.unify.model.tag.Tag;


import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.unify.testutil.TypicalIndexes.INDEX_FIRST_TASK;
import static seedu.unify.testutil.TypicalIndexes.INDEX_SECOND_TASK;

public class TagTaskDescriptorTest {

    @Test
    public void equals() {
        Set<Tag> firstSetOfTags = new HashSet<>();
        Set<Tag> secondSetOfTags = new HashSet<>();

        firstSetOfTags.add(new Tag("FirstTag"));
        secondSetOfTags.add(new Tag("SecondTag"));

        TagCommand.TagTaskDescriptor firstTagTaskDescriptor = new TagCommand.TagTaskDescriptor();
        TagCommand.TagTaskDescriptor secondTagTaskDescriptor = new TagCommand.TagTaskDescriptor();

        firstTagTaskDescriptor.setTags(firstSetOfTags);
        secondTagTaskDescriptor.setTags(secondSetOfTags);

        // same object -> returns true
        assertTrue(firstTagTaskDescriptor.equals(firstTagTaskDescriptor));

        // same values -> returns true
        TagCommand.TagTaskDescriptor firstCopy = new TagCommand.TagTaskDescriptor();
        firstCopy.setTags(firstSetOfTags);
        assertTrue(firstTagTaskDescriptor.equals(firstCopy));

        // different types -> returns false
        assertFalse(firstTagTaskDescriptor.equals(INDEX_FIRST_TASK));

        // null -> returns false
        assertFalse(firstTagTaskDescriptor.equals(null));

        // different tasks -> returns false
        assertFalse(firstTagTaskDescriptor.equals(secondTagTaskDescriptor));
    }
}
