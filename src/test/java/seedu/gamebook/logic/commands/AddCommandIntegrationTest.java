package seedu.gamebook.logic.commands;

import static seedu.gamebook.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.gamebook.testutil.TypicalGameEntries.getTypicalGameBook;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.gamebook.model.Model;
import seedu.gamebook.model.ModelManager;
import seedu.gamebook.model.UserPrefs;
import seedu.gamebook.model.gameentry.GameEntry;
import seedu.gamebook.testutil.GameEntryBuilder;


/**
 * Contains integration tests (interaction with the Model) for {@code AddCommand}.
 */
public class AddCommandIntegrationTest {

    private Model model;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalGameBook(), new UserPrefs());
    }

    @Test
    public void execute_newGameEntry_success() {
        GameEntry validGameEntry = new GameEntryBuilder().build();

        Model expectedModel = new ModelManager(model.getGameBook(), new UserPrefs());
        expectedModel.addGameEntry(validGameEntry);

        assertCommandSuccess(new AddCommand(validGameEntry), model,
                String.format(AddCommand.MESSAGE_SUCCESS, validGameEntry, ""), expectedModel);
    }

    //@Test
    //public void execute_duplicateGameEntry_throwsCommandException() {
    //    GameEntry gameEntryInList = model.getGameBook().getGameEntryList().get(0);
    //    assertCommandFailure(new AddCommand(gameEntryInList), model, AddCommand.MESSAGE_DUPLICATE_GAME);
    //}

}
