package seedu.anilist.logic.commands;

import static seedu.anilist.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.anilist.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.anilist.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.anilist.model.Model;
import seedu.anilist.model.ModelManager;
import seedu.anilist.model.UserPrefs;
import seedu.anilist.model.anime.Anime;
import seedu.anilist.testutil.PersonBuilder;

/**
 * Contains integration tests (interaction with the Model) for {@code AddCommand}.
 */
public class AddCommandIntegrationTest {

    private Model model;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    }

    @Test
    public void execute_newPerson_success() {
        Anime validAnime = new PersonBuilder().build();

        Model expectedModel = new ModelManager(model.getAniList(), new UserPrefs());
        expectedModel.addAnime(validAnime);

        assertCommandSuccess(new AddCommand(validAnime), model,
                String.format(AddCommand.MESSAGE_SUCCESS, validAnime), expectedModel);
    }

    @Test
    public void execute_duplicatePerson_throwsCommandException() {
        Anime animeInList = model.getAniList().getAnimeList().get(0);
        assertCommandFailure(new AddCommand(animeInList), model, AddCommand.MESSAGE_DUPLICATE_PERSON);
    }

}
