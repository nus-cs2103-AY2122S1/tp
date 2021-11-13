package seedu.address.model.tag;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.model.tag.exceptions.DuplicateTagException;
import seedu.address.model.tag.exceptions.TagNotFoundException;

public class UniqueTagListTest {
    private final UniqueTagList tags = new UniqueTagList();
    private final Tag tagA = new Tag("hi");
    private final Tag tagB = new Tag("hi");
    private final Tag tagC = new Tag("bye");

    @Test
    public void add() {
        // null -> throws exception
        assertThrows(NullPointerException.class, () -> tags.add(null));

        // duplicate tag -> throws exception
        tags.add(tagA);
        assertThrows(DuplicateTagException.class, () -> tags.add(tagA));
        assertThrows(DuplicateTagException.class, () -> tags.add(tagB));
    }

    @Test
    public void hasTagName() {
        tags.add(tagA);

        assertTrue(tags.hasTagName("hi"));
        assertFalse(tags.hasTagName("bye"));
    }

    @Test
    public void getTag() {
        tags.add(tagA);

        // has tag
        assertEquals(tags.getTag("hi"), tagA);

        // no tag
        assertThrows(TagNotFoundException.class, () -> tags.getTag("bye"));
    }

    @Test
    public void removeByFields() {
        // null
        assertThrows(NullPointerException.class, () -> tags.removeByFields(null));

        // nothing to remove
        assertThrows(TagNotFoundException.class, () -> tags.removeByFields(x -> x.equals("bye")));

        // remove tag successfully
        tags.add(tagA);
        tags.removeByFields(x -> x.equals(tagA));
        assertEquals(tags, new UniqueTagList());
    }

    @Test
    public void setTags() {
        // duplicate tags
        assertThrows(DuplicateTagException.class, () -> tags.setTags(new ArrayList<Tag>(List.of(tagA, tagB, tagC))));
    }

    @Test
    public void equals() {
        UniqueTagList other = new UniqueTagList();
        tags.setTags(new ArrayList<Tag>(List.of(tagA, tagC)));
        other.setTags(new ArrayList<Tag>(List.of(tagB, tagC)));

        // same object
        assertTrue(tags.equals(tags));

        // different class
        assertFalse(tags.equals("hi"));

        // same internal list
        assertTrue(tags.equals(other));

        // different internal list
        assertFalse(tags.equals(new UniqueTagList()));
    }
}
