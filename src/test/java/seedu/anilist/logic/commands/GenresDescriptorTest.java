package seedu.anilist.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.anilist.logic.commands.CommandTestUtil.DESC_GENRE_ACTION;
import static seedu.anilist.logic.commands.CommandTestUtil.DESC_GENRE_SCIENCE_FICTION;
import static seedu.anilist.logic.commands.CommandTestUtil.DESC_NAME_AKIRA;
import static seedu.anilist.logic.commands.CommandTestUtil.VALID_GENRE_ACTION;
import static seedu.anilist.logic.commands.CommandTestUtil.VALID_GENRE_SCIENCE_FICTION;

import java.util.HashSet;

import org.junit.jupiter.api.Test;

import seedu.anilist.testutil.GenresDescriptorBuilder;


public class GenresDescriptorTest {
    @Test
    public void equals() {
        // same values -> returns true
        GenreCommand.GenresDescriptor descriptorWithSameValues =
                new GenresDescriptorBuilder()
                        .withGenre(VALID_GENRE_ACTION).build();
        assertEquals(DESC_GENRE_ACTION, descriptorWithSameValues);

        // same object -> returns true
        assertEquals(DESC_GENRE_ACTION, DESC_GENRE_ACTION);

        // null -> returns false
        assertNotEquals(null, DESC_GENRE_ACTION);

        // different types -> returns false
        assertNotEquals(5, DESC_GENRE_ACTION);

        // different genres -> returns false
        assertNotEquals(DESC_GENRE_ACTION, DESC_GENRE_SCIENCE_FICTION);

        // partial genre matches -> returns false
        GenreCommand.GenresDescriptor descriptorWithMultipleGenres =
                new GenresDescriptorBuilder()
                        .withGenre(VALID_GENRE_ACTION, VALID_GENRE_SCIENCE_FICTION).build();
        assertNotEquals(DESC_NAME_AKIRA, descriptorWithMultipleGenres);
    }

    @Test
    public void hasUsedGenreTest() {
        //usedGenres is not set
        GenreCommand.GenresDescriptor descriptorWithoutUsedGenres = new GenresDescriptorBuilder()
                .withGenre(VALID_GENRE_ACTION).build();
        assertFalse(descriptorWithoutUsedGenres.hasUsedGenres());

        //usedGenres is null
        GenreCommand.GenresDescriptor descriptorWithNullUsedGenres = new GenresDescriptorBuilder()
                .withGenre(VALID_GENRE_ACTION).build();
        descriptorWithNullUsedGenres.setUsedGenres(null);
        assertFalse(descriptorWithNullUsedGenres.hasUsedGenres());

        //usedGenres is empty
        GenreCommand.GenresDescriptor descriptorWithNoUsedGenres = new GenresDescriptorBuilder()
                .withGenre(VALID_GENRE_ACTION).build();
        descriptorWithNoUsedGenres.setUsedGenres(new HashSet<>());
        assertFalse(descriptorWithNoUsedGenres.hasUsedGenres());

        //usedGenres is not empty
        GenreCommand.GenresDescriptor descriptorWithUsedGenres = new GenresDescriptorBuilder()
                .withGenre(VALID_GENRE_ACTION).build();
        descriptorWithUsedGenres.setUsedGenres(descriptorWithUsedGenres.getGenres().get());
        assertTrue(descriptorWithUsedGenres.hasUsedGenres());
    }

    @Test
    public void hasUnusedGenreTest() {
        //unusedGenres is not set
        GenreCommand.GenresDescriptor descriptorWithoutUnusedGenres = new GenresDescriptorBuilder()
                .withGenre(VALID_GENRE_ACTION).build();
        assertFalse(descriptorWithoutUnusedGenres.hasUnusedGenres());

        //unusedGenres is null
        GenreCommand.GenresDescriptor descriptorWithNullUnusedGenres = new GenresDescriptorBuilder()
                .withGenre(VALID_GENRE_ACTION).build();
        descriptorWithNullUnusedGenres.setUnusedGenres(null);
        assertFalse(descriptorWithoutUnusedGenres.hasUnusedGenres());

        //unusedGenres is empty
        GenreCommand.GenresDescriptor descriptorWithNoUnusedGenres = new GenresDescriptorBuilder()
                .withGenre(VALID_GENRE_ACTION).build();
        descriptorWithNoUnusedGenres.setUnusedGenres(new HashSet<>());
        assertFalse(descriptorWithNoUnusedGenres.hasUnusedGenres());

        //unusedGenres is not empty
        GenreCommand.GenresDescriptor descriptorWithUnusedGenres = new GenresDescriptorBuilder()
                .withGenre(VALID_GENRE_ACTION).build();
        descriptorWithUnusedGenres.setUnusedGenres(descriptorWithUnusedGenres.getGenres().get());
        assertTrue(descriptorWithUnusedGenres.hasUnusedGenres());
    }
}
