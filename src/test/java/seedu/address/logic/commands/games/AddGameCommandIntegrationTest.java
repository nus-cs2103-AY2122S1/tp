package seedu.address.logic.commands.games;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalFriends.getTypicalFriendsList;
import static seedu.address.testutil.TypicalGames.getTypicalGamesList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.game.Game;
import seedu.address.testutil.GameBuilder;

public class AddGameCommandIntegrationTest {
    private Model model;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalFriendsList(), getTypicalGamesList(), new UserPrefs());
    }

    @Test
    public void execute_newGame_success() {
        Game validGame = new GameBuilder().withGameId("HELLOWORLD123").build();

        Model expectedModel = new ModelManager(model.getFriendsList(), model.getGamesList(), new UserPrefs());
        expectedModel.addGame(validGame);

        assertCommandSuccess(new AddGameCommand(validGame), model,
                String.format(AddGameCommand.MESSAGE_SUCCESS_ADD_GAME, validGame), expectedModel);
    }

    @Test
    public void execute_duplicateGame_throwsCommandException() {
        // duplicated in list
        Game gameInList = model.getGamesList().getGamesList().get(0);
        assertCommandFailure(new AddGameCommand(gameInList), model, AddGameCommand.MESSAGE_DUPLICATE_GAME);

        // duplicated gameId
        String duplicatedGameId = model.getGamesList().getGamesList().get(0).getGameId().value;
        assertCommandFailure(new AddGameCommand(new GameBuilder().withGameId(duplicatedGameId).build()),
                model, AddGameCommand.MESSAGE_DUPLICATE_GAME);
    }

}
