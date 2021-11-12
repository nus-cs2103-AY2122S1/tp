package seedu.address.logic.commands.games;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.friends.ListFriendCommand.MESSAGE_UNKNOWN_PREDICATE;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalFriends.getTypicalFriendsList;
import static seedu.address.testutil.TypicalGames.getTypicalGamesList;

import java.util.function.Predicate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.game.Game;
import seedu.address.model.game.GameIdContainsKeywordPredicate;

/**
 * Contains integration tests (interaction with the Model) and unit tests for ListCommand.
 */
public class ListGameCommandTest {

    private Model model;
    private Model expectedModel;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalFriendsList(), getTypicalGamesList(), new UserPrefs());
        expectedModel = new ModelManager(model.getFriendsList(), getTypicalGamesList(), new UserPrefs());
    }

    @Test
    public void execute_unknownPredicate_throwsCommandException() {
        Predicate<Game> unknownPredicate = game -> false;
        assertCommandFailure(new ListGameCommand(unknownPredicate), model, MESSAGE_UNKNOWN_PREDICATE);
        assertThrows(CommandException.class, () -> new ListGameCommand(unknownPredicate).getMessageSuccess());
    }

    @Test
    public void execute_listGameNotFiltered_showsSameList() {
        assertCommandSuccess(new ListGameCommand(preparePredicate("")), model,
                ListGameCommand.MESSAGE_SUCCESS_PREPEND, expectedModel);
    }

    @Test
    public void execute_listGameIsFiltered_showsEverything() throws CommandException {
        // Set up expected filtered list by Ali
        expectedModel.updateFilteredGamesList(preparePredicate("Ali"));

        ListGameCommand listGameCommand = new ListGameCommand(preparePredicate("Ali"));
        assertCommandSuccess(listGameCommand, model,
                listGameCommand.getMessageSuccess(), expectedModel);
    }

    @Test
    public void equals() {
        Predicate<Game> filterByA = preparePredicate("A");
        ListGameCommand gameCommand = new ListGameCommand(filterByA);

        // Same Object
        assertTrue(gameCommand.equals(gameCommand));
        // Same Predicate
        assertTrue(gameCommand.equals(new ListGameCommand(filterByA)));
        // Same Predicate Value
        assertTrue(gameCommand.equals(new ListGameCommand(preparePredicate("A"))));

        // Different Value
        assertFalse(gameCommand.equals(new ListGameCommand(preparePredicate("Different"))));
    }

    /**
     * Parses {@code userInput} into a {@code NameContainsKeywordsPredicate}.
     */
    private GameIdContainsKeywordPredicate preparePredicate(String userInput) {
        return new GameIdContainsKeywordPredicate(userInput);
    }
}
