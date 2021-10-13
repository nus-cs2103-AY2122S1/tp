package seedu.anilist.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.anilist.logic.commands.CommandTestUtil.DESC_AKIRA;
import static seedu.anilist.logic.commands.CommandTestUtil.DESC_BNHA;
import static seedu.anilist.logic.commands.CommandTestUtil.VALID_GENRE_SUPERHERO;
import static seedu.anilist.logic.commands.CommandTestUtil.VALID_NAME_BNHA;

import org.junit.jupiter.api.Test;

import seedu.anilist.logic.commands.EditCommand.EditAnimeDescriptor;
import seedu.anilist.testutil.EditAnimeDescriptorBuilder;

public class EditAnimeDescriptorTest {

    @Test
    public void equals() {
        // same values -> returns true
        EditAnimeDescriptor descriptorWithSameValues = new EditAnimeDescriptor(DESC_AKIRA);
        assertTrue(DESC_AKIRA.equals(descriptorWithSameValues));

        // same object -> returns true
        assertTrue(DESC_AKIRA.equals(DESC_AKIRA));

        // null -> returns false
        assertFalse(DESC_AKIRA.equals(null));

        // different types -> returns false
        assertFalse(DESC_AKIRA.equals(5));

        // different values -> returns false
        assertFalse(DESC_AKIRA.equals(DESC_BNHA));

        // different name -> returns false
        EditAnimeDescriptor editedAkira = new EditAnimeDescriptorBuilder(DESC_AKIRA).withName(VALID_NAME_BNHA).build();
        assertFalse(DESC_AKIRA.equals(editedAkira));

        // different genres -> returns false
        editedAkira = new EditAnimeDescriptorBuilder(DESC_AKIRA).withGenres(VALID_GENRE_SUPERHERO).build();
        assertFalse(DESC_AKIRA.equals(editedAkira));
    }
}
