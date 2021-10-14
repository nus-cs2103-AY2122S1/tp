package seedu.address.model.item;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_COUNT_BAGEL;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ID_BAGEL;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ID_DONUT;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BAGEL;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_BAKED;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_POPULAR;
import static seedu.address.testutil.TypicalItems.APPLE_PIE;
import static seedu.address.testutil.TypicalItems.BANANA_MUFFIN;

import org.junit.jupiter.api.Test;

import seedu.address.model.tag.Tag;
import seedu.address.testutil.ItemDescriptorBuilder;

import java.util.HashSet;
import java.util.Set;

public class ItemDescriptorTest {

    @Test
    public void setAndGetFields_success() {

        // Set Name
        ItemDescriptor descriptor = new ItemDescriptor();
        descriptor.setName(new Name(VALID_NAME_BAGEL));
        assertEquals(descriptor.getName().get(), new Name(VALID_NAME_BAGEL));

        // Set Id
        descriptor = new ItemDescriptor();
        descriptor.setId(VALID_ID_BAGEL);
        assertEquals(descriptor.getId().get(), VALID_ID_BAGEL);

        // Set Count
        descriptor = new ItemDescriptor();
        descriptor.setCount(Integer.parseInt(VALID_COUNT_BAGEL));
        assertEquals(descriptor.getCount().get(), Integer.parseInt(VALID_COUNT_BAGEL));

        // Set Tags
        descriptor = new ItemDescriptor();
        Set<Tag> tagSet = new HashSet<>();
        tagSet.add(new Tag(VALID_TAG_BAKED));
        tagSet.add(new Tag(VALID_TAG_POPULAR));

        descriptor.setTags(tagSet);
        assertEquals(descriptor.getTags().get(), tagSet);
    }

    @Test
    public void getFields_emptyFields_emptyOptionals() {
        ItemDescriptor descriptor = new ItemDescriptor();

        // Get Name
        assertTrue(descriptor.getName().isEmpty());

        // Get Id
        assertTrue(descriptor.getId().isEmpty());

        // Get Count
        assertTrue(descriptor.getCount().isEmpty());

        // Set Tags
        assertTrue(descriptor.getTags().isEmpty());
    }

    @Test
    public void equals() {
        // same values -> returns true
        ItemDescriptor pieDescriptor = new ItemDescriptorBuilder(APPLE_PIE).build();
        ItemDescriptor pieDescriptorCopy = new ItemDescriptorBuilder(pieDescriptor).build();
        assertTrue(pieDescriptor.equals(pieDescriptorCopy));

        // same object -> returns true
        assertTrue(pieDescriptor.equals(pieDescriptor));

        // null -> returns false
        assertFalse(pieDescriptor.equals(null));

        // different type -> returns false
        assertFalse(pieDescriptor.equals(5));

        // different item -> returns false
        ItemDescriptor muffinDescriptor = new ItemDescriptorBuilder(BANANA_MUFFIN).build();
        assertFalse(pieDescriptor.equals(muffinDescriptor));

        // different name -> returns false
        ItemDescriptor editedPie = new ItemDescriptorBuilder(pieDescriptor)
                .withName(VALID_NAME_BAGEL).build();
        assertFalse(pieDescriptor.equals(editedPie));

        // different id -> returns false
        editedPie = new ItemDescriptorBuilder(pieDescriptor)
                .withId(VALID_ID_BAGEL).build();
        assertFalse(pieDescriptor.equals(editedPie));

        // different count -> returns false
        int newCount = APPLE_PIE.getCount() + 1;
        editedPie = new ItemDescriptorBuilder(pieDescriptor)
                .withCount(newCount).build();
        assertFalse(pieDescriptor.equals(editedPie));

        // different tags -> returns false
        editedPie = new ItemDescriptorBuilder(pieDescriptor)
                .withTags(VALID_TAG_POPULAR).build();
        assertFalse(pieDescriptor.equals(editedPie));
    }
}