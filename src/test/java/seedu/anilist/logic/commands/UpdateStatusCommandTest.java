package seedu.anilist.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.anilist.logic.commands.CommandTestUtil.DESC_TOWATCH;
import static seedu.anilist.logic.commands.CommandTestUtil.DESC_WATCHING;
import static seedu.anilist.logic.commands.CommandTestUtil.DESC_WATCHING_SHORTFORM;
import static seedu.anilist.logic.commands.CommandTestUtil.VALID_STATUS_WATCHING;
import static seedu.anilist.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.anilist.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.anilist.logic.commands.CommandTestUtil.showAnimeAtIndex;
import static seedu.anilist.testutil.TypicalAnimes.getTypicalAnimeList;
import static seedu.anilist.testutil.TypicalIndexes.INDEX_FIRST_ANIME;
import static seedu.anilist.testutil.TypicalIndexes.INDEX_SECOND_ANIME;

import org.junit.jupiter.api.Test;

import seedu.anilist.commons.core.Messages;
import seedu.anilist.commons.core.index.Index;
import seedu.anilist.model.AnimeList;
import seedu.anilist.model.Model;
import seedu.anilist.model.ModelManager;
import seedu.anilist.model.UserPrefs;
import seedu.anilist.model.anime.Anime;
import seedu.anilist.testutil.AnimeBuilder;
import seedu.anilist.testutil.StatusDescriptorBuilder;

/**
 * Contains integration tests (interaction with the Model) and unit tests for UpdateStatusCommand.
 */
public class UpdateStatusCommandTest {

    private Model model = new ModelManager(getTypicalAnimeList(), new UserPrefs());

    @Test
    public void execute_allFieldsSpecifiedUnfilteredList_success() {
        Anime updatedAnime = new AnimeBuilder(model.getFilteredAnimeList().get(INDEX_FIRST_ANIME.getZeroBased()))
                .withStatus(VALID_STATUS_WATCHING)
                .build();
        UpdateStatusCommand.StatusDescriptor descriptor = new StatusDescriptorBuilder()
                .withStatus(VALID_STATUS_WATCHING)
                .build();
        UpdateStatusCommand updateStatusCommand = new UpdateStatusCommand(INDEX_FIRST_ANIME, descriptor);

        String expectedMessage = String.format(
                UpdateStatusCommand.MESSAGE_UPDATE_ANIME_STATUS_SUCCESS,
                updatedAnime);

        Model expectedModel = new ModelManager(new AnimeList(model.getAnimeList()), new UserPrefs());
        expectedModel.setAnime(model.getFilteredAnimeList().get(0), updatedAnime);

        assertCommandSuccess(updateStatusCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_filteredList_success() {
        showAnimeAtIndex(model, INDEX_FIRST_ANIME);

        Anime updatedAnime = new AnimeBuilder(model.getFilteredAnimeList().get(INDEX_FIRST_ANIME.getZeroBased()))
                .withStatus(VALID_STATUS_WATCHING)
                .build();
        UpdateStatusCommand.StatusDescriptor descriptor = new StatusDescriptorBuilder()
                .withStatus(VALID_STATUS_WATCHING)
                .build();
        UpdateStatusCommand updateStatusCommand = new UpdateStatusCommand(INDEX_FIRST_ANIME, descriptor);

        String expectedMessage = String.format(
                UpdateStatusCommand.MESSAGE_UPDATE_ANIME_STATUS_SUCCESS,
                updatedAnime);

        Model expectedModel = new ModelManager(new AnimeList(model.getAnimeList()), new UserPrefs());
        expectedModel.setAnime(model.getFilteredAnimeList().get(0), updatedAnime);

        assertCommandSuccess(updateStatusCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidAnimeIndexUnfilteredList_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredAnimeList().size() + 1);
        UpdateStatusCommand.StatusDescriptor descriptor = new StatusDescriptorBuilder()
                .withStatus(VALID_STATUS_WATCHING).build();
        UpdateStatusCommand updateStatusCommand = new UpdateStatusCommand(outOfBoundIndex, descriptor);

        assertCommandFailure(updateStatusCommand, model, Messages.MESSAGE_INVALID_ANIME_DISPLAYED_INDEX);
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
        UpdateStatusCommand.StatusDescriptor descriptor = new StatusDescriptorBuilder()
                .withStatus(VALID_STATUS_WATCHING).build();
        UpdateStatusCommand updateStatusCommand = new UpdateStatusCommand(outOfBoundIndex, descriptor);

        assertCommandFailure(updateStatusCommand, model, Messages.MESSAGE_INVALID_ANIME_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        final UpdateStatusCommand standardCommand = new UpdateStatusCommand(INDEX_FIRST_ANIME, DESC_WATCHING);

        // same values -> returns true
        UpdateStatusCommand.StatusDescriptor copyDescriptor = new UpdateStatusCommand.StatusDescriptor(DESC_WATCHING);
        UpdateStatusCommand commandWithSameValues = new UpdateStatusCommand(INDEX_FIRST_ANIME, copyDescriptor);
        assertEquals(commandWithSameValues, standardCommand);

        // original and shortform -> returns true
        UpdateStatusCommand.StatusDescriptor shortFormDescriptor = new UpdateStatusCommand.StatusDescriptor(
                DESC_WATCHING_SHORTFORM);
        UpdateStatusCommand commandWithShortForm = new UpdateStatusCommand(INDEX_FIRST_ANIME, shortFormDescriptor);
        assertEquals(commandWithShortForm, standardCommand);

        // same object -> returns true
        assertEquals(standardCommand, standardCommand);

        // null -> returns false
        assertNotEquals(standardCommand, null);

        // different types -> returns false
        assertNotEquals(new ClearCommand(), standardCommand);

        // different index -> returns false
        assertNotEquals(new UpdateStatusCommand(INDEX_SECOND_ANIME, DESC_WATCHING), standardCommand);

        // different descriptor -> returns false
        assertNotEquals(new UpdateStatusCommand(INDEX_FIRST_ANIME, DESC_TOWATCH), standardCommand);
    }

}

