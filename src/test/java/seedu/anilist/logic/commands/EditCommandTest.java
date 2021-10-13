package seedu.anilist.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.anilist.logic.commands.CommandTestUtil.DESC_AKIRA;
import static seedu.anilist.logic.commands.CommandTestUtil.DESC_BNHA;
import static seedu.anilist.logic.commands.CommandTestUtil.VALID_NAME_BNHA;
import static seedu.anilist.logic.commands.CommandTestUtil.VALID_GENRE_SHOUNEN;
import static seedu.anilist.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.anilist.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.anilist.logic.commands.CommandTestUtil.showAnimeAtIndex;
import static seedu.anilist.testutil.TypicalAnimes.getTypicalAnimeList;
import static seedu.anilist.testutil.TypicalIndexes.INDEX_FIRST_ANIME;
import static seedu.anilist.testutil.TypicalIndexes.INDEX_SECOND_ANIME;

import org.junit.jupiter.api.Test;

import seedu.anilist.commons.core.Messages;
import seedu.anilist.commons.core.index.Index;
import seedu.anilist.logic.commands.EditCommand.EditAnimeDescriptor;
import seedu.anilist.model.AnimeList;
import seedu.anilist.model.Model;
import seedu.anilist.model.ModelManager;
import seedu.anilist.model.UserPrefs;
import seedu.anilist.model.anime.Anime;
import seedu.anilist.testutil.AnimeBuilder;
import seedu.anilist.testutil.EditAnimeDescriptorBuilder;

/**
 * Contains integration tests (interaction with the Model) and unit tests for EditCommand.
 */
public class EditCommandTest {

    private Model model = new ModelManager(getTypicalAnimeList(), new UserPrefs());

    @Test
    public void execute_allFieldsSpecifiedUnfilteredList_success() {
        Anime editedAnime = new AnimeBuilder().build();
        EditAnimeDescriptor descriptor = new EditAnimeDescriptorBuilder(editedAnime).build();
        EditCommand editCommand = new EditCommand(INDEX_FIRST_ANIME, descriptor);

        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_ANIME_SUCCESS, editedAnime);

        Model expectedModel = new ModelManager(new AnimeList(model.getAnimeList()), new UserPrefs());
        expectedModel.setAnime(model.getFilteredAnimeList().get(0), editedAnime);

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_someFieldsSpecifiedUnfilteredList_success() {
        Index indexLastAnime = Index.fromOneBased(model.getFilteredAnimeList().size());
        Anime lastAnime = model.getFilteredAnimeList().get(indexLastAnime.getZeroBased());

        AnimeBuilder animeInList = new AnimeBuilder(lastAnime);
        Anime editedAnime = animeInList.withName(VALID_NAME_BNHA).withGenres(VALID_GENRE_SHOUNEN).build();

        EditCommand.EditAnimeDescriptor descriptor = new EditAnimeDescriptorBuilder().withName(VALID_NAME_BNHA)
                .withGenres(VALID_GENRE_SHOUNEN).build();
        EditCommand editCommand = new EditCommand(indexLastAnime, descriptor);

        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_ANIME_SUCCESS, editedAnime);

        Model expectedModel = new ModelManager(new AnimeList(model.getAnimeList()), new UserPrefs());
        expectedModel.setAnime(lastAnime, editedAnime);

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_noFieldSpecifiedUnfilteredList_success() {
        EditCommand editCommand = new EditCommand(INDEX_FIRST_ANIME, new EditAnimeDescriptor());
        Anime editedAnime = model.getFilteredAnimeList().get(INDEX_FIRST_ANIME.getZeroBased());

        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_ANIME_SUCCESS, editedAnime);

        Model expectedModel = new ModelManager(new AnimeList(model.getAnimeList()), new UserPrefs());

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_filteredList_success() {
        showAnimeAtIndex(model, INDEX_FIRST_ANIME);

        Anime animeInFilteredList = model.getFilteredAnimeList().get(INDEX_FIRST_ANIME.getZeroBased());
        Anime editedAnime = new AnimeBuilder(animeInFilteredList).withName(VALID_NAME_BNHA).build();
        EditCommand editCommand = new EditCommand(INDEX_FIRST_ANIME,
                new EditAnimeDescriptorBuilder().withName(VALID_NAME_BNHA).build());

        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_ANIME_SUCCESS, editedAnime);

        Model expectedModel = new ModelManager(new AnimeList(model.getAnimeList()), new UserPrefs());
        expectedModel.setAnime(model.getFilteredAnimeList().get(0), editedAnime);

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_duplicateAnimeUnfilteredList_failure() {
        Anime firstAnime = model.getFilteredAnimeList().get(INDEX_FIRST_ANIME.getZeroBased());
        EditCommand.EditAnimeDescriptor descriptor = new EditAnimeDescriptorBuilder(firstAnime).build();
        EditCommand editCommand = new EditCommand(INDEX_SECOND_ANIME, descriptor);

        assertCommandFailure(editCommand, model, EditCommand.MESSAGE_DUPLICATE_ANIME);
    }

    @Test
    public void execute_duplicateAnimeFilteredList_failure() {
        showAnimeAtIndex(model, INDEX_FIRST_ANIME);

        // edit anime in filtered list into a duplicate in anime list
        Anime animeInList = model.getAnimeList().getAnimeList().get(INDEX_SECOND_ANIME.getZeroBased());
        EditCommand editCommand = new EditCommand(INDEX_FIRST_ANIME,
                new EditAnimeDescriptorBuilder(animeInList).build());

        assertCommandFailure(editCommand, model, EditCommand.MESSAGE_DUPLICATE_ANIME);
    }

    @Test
    public void execute_invalidAnimeIndexUnfilteredList_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredAnimeList().size() + 1);
        EditCommand.EditAnimeDescriptor descriptor = new EditAnimeDescriptorBuilder().withName(VALID_NAME_BNHA).build();
        EditCommand editCommand = new EditCommand(outOfBoundIndex, descriptor);

        assertCommandFailure(editCommand, model, Messages.MESSAGE_INVALID_ANIME_DISPLAYED_INDEX);
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

        EditCommand editCommand = new EditCommand(outOfBoundIndex,
                new EditAnimeDescriptorBuilder().withName(VALID_NAME_BNHA).build());

        assertCommandFailure(editCommand, model, Messages.MESSAGE_INVALID_ANIME_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        final EditCommand standardCommand = new EditCommand(INDEX_FIRST_ANIME, DESC_AKIRA);

        // same values -> returns true
        EditCommand.EditAnimeDescriptor copyDescriptor = new EditCommand.EditAnimeDescriptor(DESC_AKIRA);
        EditCommand commandWithSameValues = new EditCommand(INDEX_FIRST_ANIME, copyDescriptor);
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearCommand()));

        // different index -> returns false
        assertFalse(standardCommand.equals(new EditCommand(INDEX_SECOND_ANIME, DESC_AKIRA)));

        // different descriptor -> returns false
        assertFalse(standardCommand.equals(new EditCommand(INDEX_FIRST_ANIME, DESC_BNHA)));
    }

}
