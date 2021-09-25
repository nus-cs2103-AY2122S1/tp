package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.anime.Anime;
import seedu.address.testutil.PersonBuilder;

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
