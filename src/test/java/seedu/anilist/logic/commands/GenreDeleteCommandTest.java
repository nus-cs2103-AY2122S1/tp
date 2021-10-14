package seedu.anilist.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.anilist.logic.commands.CommandTestUtil.VALID_GENRE_SHOUNEN;
import static seedu.anilist.logic.commands.CommandTestUtil.VALID_GENRE_SUPERHERO;
import static seedu.anilist.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.anilist.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.anilist.logic.commands.CommandTestUtil.showAnimeAtIndex;
import static seedu.anilist.testutil.TypicalAnimes.getTypicalAnimeList;
import static seedu.anilist.testutil.TypicalIndexes.INDEX_FIRST_ANIME;
import static seedu.anilist.testutil.TypicalIndexes.INDEX_SECOND_ANIME;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

import seedu.anilist.commons.core.Messages;
import seedu.anilist.commons.core.index.Index;
import seedu.anilist.model.AnimeList;
import seedu.anilist.model.Model;
import seedu.anilist.model.ModelManager;
import seedu.anilist.model.UserPrefs;
import seedu.anilist.model.anime.Anime;
import seedu.anilist.model.genre.Genre;
import seedu.anilist.testutil.AnimeBuilder;
import seedu.anilist.testutil.GenresDescriptorBuilder;

public class GenreDeleteCommandTest {
    private static final String ANIME_ONE_GENRE = "friends";

    private Model model = new ModelManager(getTypicalAnimeList(), new UserPrefs());

    @Test
    public void execute_allFieldsSpecifiedUnfilteredList_success() {
        Anime updatedAnime = new AnimeBuilder(model.getFilteredAnimeList().get(INDEX_FIRST_ANIME.getZeroBased()))
                .withGenres()
                .build();
        Set<Genre> genreSet = new HashSet<>();
        genreSet.add(new Genre(ANIME_ONE_GENRE));
        GenreDeleteCommand.GenresDescriptor descriptor = new GenresDescriptorBuilder()
                .withGenre(genreSet)
                .build();


        GenreDeleteCommand genreDeleteCommand = new GenreDeleteCommand(INDEX_FIRST_ANIME, descriptor);

        String expectedMessage = String.format(genreDeleteCommand.MESSAGE_SUCCESS, descriptor, updatedAnime);

        Model expectedModel = new ModelManager(new AnimeList(model.getAnimeList()), new UserPrefs());
        expectedModel.setAnime(model.getFilteredAnimeList().get(0), updatedAnime);

        assertCommandSuccess(genreDeleteCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_filteredList_success() {
        showAnimeAtIndex(model, INDEX_FIRST_ANIME);

        Anime updatedAnime = new AnimeBuilder(model.getFilteredAnimeList().get(INDEX_FIRST_ANIME.getZeroBased()))
                .withGenres()
                .build();
        Set<Genre> genreSet = new HashSet<>();
        genreSet.add(new Genre(ANIME_ONE_GENRE));
        GenreDeleteCommand.GenresDescriptor descriptor = new GenresDescriptorBuilder()
                .withGenre(genreSet)
                .build();


        GenreDeleteCommand genreDeleteCommand = new GenreDeleteCommand(INDEX_FIRST_ANIME, descriptor);

        String expectedMessage = String.format(genreDeleteCommand.MESSAGE_SUCCESS, descriptor, updatedAnime);

        Model expectedModel = new ModelManager(new AnimeList(model.getAnimeList()), new UserPrefs());
        expectedModel.setAnime(model.getFilteredAnimeList().get(0), updatedAnime);

        assertCommandSuccess(genreDeleteCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_unfilteredListNoSuchGenre_success() {
        Anime updatedAnime = new AnimeBuilder(model.getFilteredAnimeList().get(INDEX_FIRST_ANIME.getZeroBased()))
                .withGenres(ANIME_ONE_GENRE)
                .build();
        Set<Genre> genreSet = new HashSet<>();
        genreSet.add(new Genre(VALID_GENRE_SHOUNEN));
        GenreDeleteCommand.GenresDescriptor descriptor = new GenresDescriptorBuilder()
                .withGenre(genreSet)
                .build();


        GenreDeleteCommand genreDeleteCommand = new GenreDeleteCommand(INDEX_FIRST_ANIME, descriptor);

        String expectedMessage = String.format(genreDeleteCommand.MESSAGE_SUCCESS, descriptor, updatedAnime);

        Model expectedModel = new ModelManager(new AnimeList(model.getAnimeList()), new UserPrefs());
        expectedModel.setAnime(model.getFilteredAnimeList().get(0), updatedAnime);

        assertCommandSuccess(genreDeleteCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_unfilteredListNoSuchGenreWithValidGenre_success() {
        Anime updatedAnime = new AnimeBuilder(model.getFilteredAnimeList().get(INDEX_FIRST_ANIME.getZeroBased()))
                .withGenres()
                .build();
        Set<Genre> genreSet = new HashSet<>();
        genreSet.add(new Genre(ANIME_ONE_GENRE));
        genreSet.add(new Genre(VALID_GENRE_SHOUNEN));
        GenreDeleteCommand.GenresDescriptor descriptor = new GenresDescriptorBuilder()
                .withGenre(genreSet)
                .build();


        GenreDeleteCommand genreDeleteCommand = new GenreDeleteCommand(INDEX_FIRST_ANIME, descriptor);

        String expectedMessage = String.format(genreDeleteCommand.MESSAGE_SUCCESS, descriptor, updatedAnime);

        Model expectedModel = new ModelManager(new AnimeList(model.getAnimeList()), new UserPrefs());
        expectedModel.setAnime(model.getFilteredAnimeList().get(0), updatedAnime);

        assertCommandSuccess(genreDeleteCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidAnimeIndexUnfilteredList_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredAnimeList().size() + 1);
        Set<Genre> genreSet = new HashSet<>();
        genreSet.add(new Genre(ANIME_ONE_GENRE));
        GenreDeleteCommand.GenresDescriptor descriptor = new GenresDescriptorBuilder()
                .withGenre(genreSet)
                .build();
        GenreDeleteCommand genreDeleteCommand = new GenreDeleteCommand(outOfBoundIndex, descriptor);

        assertCommandFailure(genreDeleteCommand, model, Messages.MESSAGE_INVALID_ANIME_DISPLAYED_INDEX);
    }

    /**
     * Edit filtered list where index is larger than size of filtered list,
     * but smaller than size of anime list
     */
    @Test
    public void execute_invalidAnimeIndexFilteredList_failure() {
        showAnimeAtIndex(model, INDEX_FIRST_ANIME);
        Index outOfBoundIndex = INDEX_SECOND_ANIME;
        // ensures that outOfBoundIndex is still in bounds of anime list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getAnimeList().getAnimeList().size());
        Set<Genre> genreSet = new HashSet<>();
        genreSet.add(new Genre(ANIME_ONE_GENRE));
        GenreDeleteCommand.GenresDescriptor descriptor = new GenresDescriptorBuilder()
                .withGenre(genreSet)
                .build();
        GenreDeleteCommand genreDeleteCommand = new GenreDeleteCommand(outOfBoundIndex, descriptor);

        assertCommandFailure(genreDeleteCommand, model, Messages.MESSAGE_INVALID_ANIME_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        Set<Genre> genreSet = new HashSet<>();
        genreSet.add(new Genre(VALID_GENRE_SHOUNEN));
        GenreDeleteCommand.GenresDescriptor descriptor = new GenresDescriptorBuilder()
                .withGenre(genreSet)
                .build();
        final GenreDeleteCommand standardCommand = new GenreDeleteCommand(INDEX_FIRST_ANIME, descriptor);

        // same values -> returns true
        GenreDeleteCommand.GenresDescriptor copyDescriptor = new GenreCommand.GenresDescriptor(descriptor);
        GenreDeleteCommand commandWithSameValues = new GenreDeleteCommand(INDEX_FIRST_ANIME, copyDescriptor);
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearCommand()));

        // different index -> returns false
        assertFalse(standardCommand.equals(new GenreDeleteCommand(INDEX_SECOND_ANIME, descriptor)));

        // different descriptor -> returns false
        Set<Genre> differentGenreSet = new HashSet<>();
        differentGenreSet.add(new Genre(VALID_GENRE_SUPERHERO));
        GenreDeleteCommand.GenresDescriptor differentDescriptor = new GenresDescriptorBuilder()
                .withGenre(differentGenreSet)
                .build();
        assertFalse(standardCommand.equals(new GenreDeleteCommand(INDEX_FIRST_ANIME, differentDescriptor)));
    }
}
