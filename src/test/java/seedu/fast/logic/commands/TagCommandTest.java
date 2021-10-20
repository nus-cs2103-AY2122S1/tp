package seedu.fast.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.fast.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.fast.testutil.TypicalIndexes.INDEX_SECOND_PERSON;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

import seedu.fast.model.tag.Tag;

public class TagCommandTest {

    @Test
    public void equals() {
        Set<Tag> emptySet = new HashSet<>();
        Set<Tag> setWithOneTag = new HashSet<>();
        Tag normalTag = Tag.createTag("test");
        setWithOneTag.add(normalTag);

        final TagCommand standardCommand = new TagCommand(INDEX_FIRST_PERSON, emptySet, setWithOneTag);

        // same values -> returns true
        TagCommand commandWithSameValues = new TagCommand(INDEX_FIRST_PERSON, emptySet, setWithOneTag);
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearCommand()));

        // different index -> returns false
        assertFalse(standardCommand.equals(new TagCommand(INDEX_SECOND_PERSON, emptySet, setWithOneTag)));

        // different sets -> returns false
        assertFalse(standardCommand.equals(new TagCommand(INDEX_FIRST_PERSON, setWithOneTag, emptySet)));
    }

    //TODO: Add more testcases
    // for case when no parameters specified e.g. 'tag 1', should throw error or simply return the success message?

}
