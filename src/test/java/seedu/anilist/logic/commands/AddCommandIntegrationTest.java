package seedu.anilist.logic.commands;

import static seedu.anilist.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.anilist.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.anilist.testutil.TypicalAnimes.getTypicalAnimeList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.anilist.model.Model;
import seedu.anilist.model.ModelManager;
import seedu.anilist.model.UserPrefs;
import seedu.anilist.model.anime.Anime;
import seedu.anilist.testutil.AnimeBuilder;

/**
 * Contains integration tests (interaction with the Model) for {@code AddCommand}.
 */
public class AddCommandIntegrationTest {

    private Model model;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalAnimeList(), new UserPrefs());
    }

    @Test
    public void execute_newAnime_success() {
        Anime validAnime = new AnimeBuilder().build();

        Model expectedModel = new ModelManager(model.getAnimeList(), new UserPrefs());
        expectedModel.addAnime(validAnime);

        assertCommandSuccess(new AddCommand(validAnime), model,
                String.format(AddCommand.MESSAGE_SUCCESS, validAnime), expectedModel);
    }

    @Test
    public void execute_duplicateAnime_throwsCommandException() {
        Anime animeInList = model.getAnimeList().getAnimeList().get(0);
        assertCommandFailure(new AddCommand(animeInList), model, AddCommand.MESSAGE_DUPLICATE_ANIME);
    }

}
