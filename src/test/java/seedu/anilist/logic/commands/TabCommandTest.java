package seedu.anilist.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.anilist.commons.core.Messages.MESSAGE_ANIME_LISTED_OVERVIEW;
import static seedu.anilist.logic.commands.CommandTestUtil.VALID_STATUS_TOWATCH;
import static seedu.anilist.logic.commands.CommandTestUtil.VALID_STATUS_WATCHING;
import static seedu.anilist.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.anilist.testutil.TypicalAnimes.CSM;
import static seedu.anilist.testutil.TypicalAnimes.DBZ;
import static seedu.anilist.testutil.TypicalAnimes.GS;
import static seedu.anilist.testutil.TypicalAnimes.getTypicalAnimeList;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.anilist.model.Model;
import seedu.anilist.model.ModelManager;
import seedu.anilist.model.UserPrefs;
import seedu.anilist.model.anime.Status;
import seedu.anilist.model.anime.StatusEqualsPredicate;




public class TabCommandTest {
    private Model model = new ModelManager(getTypicalAnimeList(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalAnimeList(), new UserPrefs());

    @Test
    public void equals() {
        StatusEqualsPredicate firstPredicate =
                new StatusEqualsPredicate(new Status(VALID_STATUS_WATCHING));
        StatusEqualsPredicate secondPredicate =
                new StatusEqualsPredicate(new Status(VALID_STATUS_TOWATCH));

        TabCommand tabFirstCommand = new TabCommand(firstPredicate);
        TabCommand tabSecondCommand = new TabCommand(secondPredicate);

        // same object -> returns true
        assertTrue(tabFirstCommand.equals(tabFirstCommand));

        // same values -> returns true
        TabCommand tabFirstCommandCopy = new TabCommand(firstPredicate);
        assertTrue(tabFirstCommand.equals(tabFirstCommandCopy));

        // different types -> returns false
        assertFalse(tabFirstCommand.equals(1));

        // null -> returns false
        assertFalse(tabFirstCommand.equals(null));

        // different anime -> returns false
        assertFalse(tabFirstCommand.equals(tabSecondCommand));
    }

    @Test
    public void execute_status_multipleAnimesFound() {
        String expectedMessage = String.format(MESSAGE_ANIME_LISTED_OVERVIEW, 3);
        StatusEqualsPredicate predicateWatching =
                new StatusEqualsPredicate(new Status(VALID_STATUS_TOWATCH));
        TabCommand tabCommandWatching = new TabCommand(predicateWatching);
        expectedModel.updateFilteredAnimeList(predicateWatching);
        assertCommandSuccess(tabCommandWatching, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(CSM, DBZ, GS), model.getFilteredAnimeList());
    }
}
