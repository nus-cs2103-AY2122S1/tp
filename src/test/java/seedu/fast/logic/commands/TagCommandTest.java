package seedu.fast.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.fast.testutil.Assert.assertThrows;
import static seedu.fast.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.fast.testutil.TypicalIndexes.INDEX_SECOND_PERSON;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.fast.model.tag.Tag;

public class TagCommandTest {

    private int zeroIndex = 0;
    private Set<Tag> emptySet = new HashSet<>();
    private Set<Tag> setWithPriorityTag = new HashSet<>();
    private Set<Tag> setWithInvestmentPlanTag = new HashSet<>();
    private Set<Tag> setWithNormalTag = new HashSet<>();
    private Set<Tag> setWithMixedTag = new HashSet<>();

    @BeforeEach
    public void setUp() {
        setWithNormalTag.add(Tag.createTag("test"));
        setWithPriorityTag.add(Tag.createTag("pr/low"));
        setWithInvestmentPlanTag.add(Tag.createTag("ip/travel"));
        setWithMixedTag.add(Tag.createTag("testtest"));
        setWithMixedTag.add(Tag.createTag("pr/med"));
        setWithMixedTag.add(Tag.createTag("ip/property"));
    }

    @Test
    public void constructor_nullArgs_throwsNullPointerException() {
        //null index
        assertThrows(NullPointerException.class, () -> new TagCommand(null,
                setWithMixedTag, setWithMixedTag));

        //null addSet
        assertThrows(NullPointerException.class, () -> new TagCommand(INDEX_FIRST_PERSON,
                null, setWithMixedTag));

        //null deleteSet
        assertThrows(NullPointerException.class, () -> new TagCommand(INDEX_FIRST_PERSON,
                setWithMixedTag, null));

    }

    @Test
    public void equals() {
        final TagCommand standardCommand = new TagCommand(INDEX_FIRST_PERSON, emptySet, setWithMixedTag);

        // same values -> returns true
        TagCommand commandWithSameValues = new TagCommand(INDEX_FIRST_PERSON, emptySet, setWithMixedTag);
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearCommand()));

        // different index -> returns false
        assertFalse(standardCommand.equals(new TagCommand(INDEX_SECOND_PERSON, emptySet, setWithMixedTag)));

        // different sets -> returns false
        assertFalse(standardCommand.equals(new TagCommand(INDEX_FIRST_PERSON, setWithMixedTag, setWithMixedTag)));
        assertFalse(standardCommand.equals(new TagCommand(INDEX_FIRST_PERSON, emptySet, emptySet)));
        assertFalse(standardCommand.equals(new TagCommand(INDEX_FIRST_PERSON, setWithMixedTag, emptySet)));

    }

    //TODO: Add more testcases
    // for case when no parameters specified e.g. 'tag 1', should throw error or simply return the success message?

}
