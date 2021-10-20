package seedu.anilist.logic.commands;

import static seedu.anilist.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.anilist.testutil.TypicalAnimes.getTypicalAnimeList;

import org.junit.jupiter.api.Test;

import seedu.anilist.model.Model;
import seedu.anilist.model.ModelManager;
import seedu.anilist.model.UserPrefs;

public class AbortClearCommandTest {

    @Test
    public void execute_emptyAnimeList_success() {
        Model model = new ModelManager();
        Model expectedModel = new ModelManager();

        assertCommandSuccess(new AbortClearCommand(), model, AbortClearCommand.MESSAGE_ABORTED, expectedModel);
    }

    @Test
    public void execute_nonEmptyAnimeList_success() {
        Model model = new ModelManager(getTypicalAnimeList(), new UserPrefs());
        Model expectedModel = new ModelManager(getTypicalAnimeList(), new UserPrefs());

        assertCommandSuccess(new AbortClearCommand(), model, AbortClearCommand.MESSAGE_ABORTED, expectedModel);
    }

}
