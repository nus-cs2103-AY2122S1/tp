package seedu.anilist.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static seedu.anilist.commons.core.Messages.MESSAGE_ANIME_LISTED_OVERVIEW;
import static seedu.anilist.logic.commands.CommandTestUtil.VALID_STATUS_TOWATCH;
import static seedu.anilist.logic.commands.CommandTestUtil.VALID_STATUS_WATCHING;
import static seedu.anilist.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.anilist.logic.commands.CommandTestUtil.showAnimeAtIndex;
import static seedu.anilist.model.Model.PREDICATE_SHOW_ALL_ANIME;
import static seedu.anilist.testutil.TypicalAnimes.CSM;
import static seedu.anilist.testutil.TypicalAnimes.DBZ;
import static seedu.anilist.testutil.TypicalAnimes.GS;
import static seedu.anilist.testutil.TypicalAnimes.getTypicalAnimeList;
import static seedu.anilist.testutil.TypicalIndexes.INDEX_FIRST_ANIME;

import java.util.Arrays;
import java.util.function.Predicate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.anilist.model.Model;
import seedu.anilist.model.ModelManager;
import seedu.anilist.model.UserPrefs;
import seedu.anilist.model.anime.Anime;
import seedu.anilist.model.anime.Status;
import seedu.anilist.model.anime.StatusEqualsPredicate;

/**
 * Contains integration tests (interaction with the Model) and unit tests for ListCommand.
 */
public class ListCommandTest {

    private Model model;
    private Model expectedModel;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalAnimeList(), new UserPrefs());
        expectedModel = new ModelManager(model.getAnimeList(), new UserPrefs());
    }

    @Test
    public void execute_noStatusSpecifiedListIsNotFiltered_showsSameList() {
        String expectedMessage = String.format(MESSAGE_ANIME_LISTED_OVERVIEW,
                expectedModel.getFilteredAnimeList().size());
        assertCommandSuccess(new ListCommand(PREDICATE_SHOW_ALL_ANIME), model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_noStatusSpecifiedListIsFiltered_showsEverything() {
        String expectedMessage = String.format(MESSAGE_ANIME_LISTED_OVERVIEW,
                expectedModel.getFilteredAnimeList().size());
        showAnimeAtIndex(model, INDEX_FIRST_ANIME);
        assertCommandSuccess(new ListCommand(PREDICATE_SHOW_ALL_ANIME), model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_statusSpecified_multipleAnimesFound() {
        String expectedMessage = String.format(MESSAGE_ANIME_LISTED_OVERVIEW, 3);
        StatusEqualsPredicate predicateWatching =
                new StatusEqualsPredicate(new Status(VALID_STATUS_TOWATCH));
        ListCommand listCommandWatching = new ListCommand(predicateWatching);
        expectedModel.updateFilteredAnimeList(predicateWatching);
        assertCommandSuccess(listCommandWatching, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(CSM, DBZ, GS), model.getFilteredAnimeList());
    }

    @Test
    public void equals() {
        Predicate<Anime> firstPredicate =
                new StatusEqualsPredicate(new Status(VALID_STATUS_WATCHING));
        Predicate<Anime> secondPredicate = PREDICATE_SHOW_ALL_ANIME;

        ListCommand listFirstCommand = new ListCommand(firstPredicate);
        ListCommand listSecondCommand = new ListCommand(secondPredicate);

        // same object -> returns true
        assertEquals(listFirstCommand, listFirstCommand);

        // same values -> returns true
        ListCommand listFirstCommandCopy = new ListCommand(firstPredicate);
        assertEquals(listFirstCommand, listFirstCommandCopy);

        ListCommand listSecondCommandCopy = new ListCommand(secondPredicate);
        assertEquals(listSecondCommand, listSecondCommandCopy);

        // different types -> returns false
        assertNotEquals(1, listFirstCommand);

        // null -> returns false
        assertNotEquals(null, listFirstCommand);

        // different anime -> returns false
        assertNotEquals(listFirstCommand, listSecondCommand);
    }
}
