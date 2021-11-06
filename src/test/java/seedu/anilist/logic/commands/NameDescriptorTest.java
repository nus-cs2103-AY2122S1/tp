package seedu.anilist.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static seedu.anilist.logic.commands.CommandTestUtil.DESC_NAME_AKIRA;
import static seedu.anilist.logic.commands.CommandTestUtil.DESC_NAME_BNHA;
import static seedu.anilist.logic.commands.CommandTestUtil.VALID_NAME_BNHA;

import org.junit.jupiter.api.Test;

import seedu.anilist.testutil.NameDescriptorBuilder;

public class NameDescriptorTest {
    @Test
    public void equals() {
        // same values -> returns true
        RenameCommand.NameDescriptor descriptorWithSameValues =
            new RenameCommand.NameDescriptor(DESC_NAME_AKIRA);
        assertEquals(DESC_NAME_AKIRA, descriptorWithSameValues);

        // same object -> returns true
        assertEquals(DESC_NAME_AKIRA, DESC_NAME_AKIRA);

        // null -> returns false
        assertNotEquals(null, DESC_NAME_AKIRA);

        // different types -> returns false
        assertNotEquals(5, DESC_NAME_AKIRA);

        // different values -> returns false
        assertNotEquals(DESC_NAME_AKIRA, DESC_NAME_BNHA);

        // different names -> returns false
        RenameCommand.NameDescriptor renamedDesc =
            new NameDescriptorBuilder(DESC_NAME_AKIRA).withName(VALID_NAME_BNHA).build();
        assertNotEquals(DESC_NAME_AKIRA, renamedDesc);
    }
}
