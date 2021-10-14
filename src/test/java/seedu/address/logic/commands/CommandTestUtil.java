package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.parser.CliSyntax.FLAG_FRIEND_NAME;
import static seedu.address.logic.parser.CliSyntax.FLAG_GAME;
import static seedu.address.testutil.Assert.assertThrows;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.FriendsList;
import seedu.address.model.GamesList;
import seedu.address.model.Model;
import seedu.address.model.friend.Friend;
import seedu.address.model.friend.FriendNameContainsKeywordsPredicate;
import seedu.address.model.game.Game;
import seedu.address.testutil.EditFriendDescriptorBuilder;

/**
 * Contains helper methods for testing commands.
 */
public class CommandTestUtil {

    public static final String VALID_NAME_AMY = "Amy Bee";
    public static final String VALID_NAME_BOB = "Bob Choo";
    public static final String VALID_FRIEND_ID_AMY = "123";
    public static final String VALID_FRIEND_ID_BOB = "456";
    public static final String VALID_GAME_ID_CSGO = "CSGO";
    public static final String VALID_GAME_ID_APEX_LEGENDS = "ApexLegends";

    public static final String FRIEND_ID_DESC_AMY = " " + VALID_FRIEND_ID_AMY;
    public static final String FRIEND_ID_DESC_BOB = " " + VALID_FRIEND_ID_BOB;
    public static final String NAME_DESC_AMY = " " + FLAG_FRIEND_NAME + " " + VALID_NAME_AMY;
    public static final String NAME_DESC_BOB = " " + FLAG_FRIEND_NAME + " " + VALID_NAME_BOB;
    public static final String GAME_DESC_AMY = " " + FLAG_GAME + VALID_GAME_ID_APEX_LEGENDS;
    public static final String GAME_DESC_BOB = " " + FLAG_GAME + VALID_GAME_ID_CSGO;

    public static final String GAME_DESC_CSGO = " " + FLAG_GAME + VALID_GAME_ID_CSGO;
    public static final String GAME_DESC_APEX_LEGENDS = " " + FLAG_GAME + VALID_GAME_ID_APEX_LEGENDS;

    public static final String INVALID_NAME_DESC = " " + FLAG_FRIEND_NAME + " " + "James&"; // '&' not allowed in names
    public static final String INVALID_GAME_DESC = " " + FLAG_GAME + "kickstar*"; // '*' not allowed in games

    public static final String PREAMBLE_WHITESPACE = "\t  \r  --name";
    public static final String PREAMBLE_NON_EMPTY = "NonEmptyPreamble";

    public static final EditCommand.EditFriendDescriptor DESC_AMY;
    public static final EditCommand.EditFriendDescriptor DESC_BOB;

    static {
        DESC_AMY = new EditFriendDescriptorBuilder().withFriendId(VALID_FRIEND_ID_AMY)
                .withFriendName(VALID_NAME_AMY).withGames(VALID_GAME_ID_APEX_LEGENDS).build();
        DESC_BOB = new EditFriendDescriptorBuilder().withFriendId(VALID_FRIEND_ID_BOB)
                .withFriendName(VALID_NAME_BOB).withGames(VALID_GAME_ID_CSGO).build();
    }

    /**
     * Executes the given {@code command}, confirms that <br>
     * - the returned {@link CommandResult} matches {@code expectedCommandResult} <br>
     * - the {@code actualModel} matches {@code expectedModel}
     */
    public static void assertCommandSuccess(Command command, Model actualModel, CommandResult expectedCommandResult,
            Model expectedModel) {
        try {
            CommandResult result = command.execute(actualModel);
            assertEquals(expectedCommandResult, result);
            assertEquals(expectedModel, actualModel);
        } catch (CommandException ce) {
            throw new AssertionError("Execution of command should not fail.", ce);
        }
    }

    /**
     * Convenience wrapper to {@link #assertCommandSuccess(Command, Model, CommandResult, Model)}
     * that takes a string {@code expectedMessage}.
     */
    public static void assertCommandSuccess(Command command, Model actualModel, String expectedMessage,
            Model expectedModel) {
        CommandResult expectedCommandResult = new CommandResult(expectedMessage);
        assertCommandSuccess(command, actualModel, expectedCommandResult, expectedModel);
    }

    /**
     * Executes the given {@code command}, confirms that <br>
     * - a {@code CommandException} is thrown <br>
     * - the CommandException message matches {@code expectedMessage} <br>
     * - the address book, filtered person list and selected person in {@code actualModel} remain unchanged
     */
    public static void assertCommandFailure(Command command, Model actualModel, String expectedMessage) {
        // we are unable to defensively copy the model for comparison later, so we can
        // only do so by copying its components.
        FriendsList expectedFriendsList = new FriendsList(actualModel.getFriendsList());
        List<Friend> expectedFilteredFriendsList = new ArrayList<>(actualModel.getFilteredFriendsList());
        GamesList expectedGamesList = new GamesList(actualModel.getGamesList());
        List<Game> expectedFilteredGamesList = new ArrayList<>(actualModel.getFilteredGamesList());

        assertThrows(CommandException.class, expectedMessage, () -> command.execute(actualModel));
        assertEquals(expectedFriendsList, actualModel.getFriendsList());
        assertEquals(expectedFilteredFriendsList, actualModel.getFilteredFriendsList());
        assertEquals(expectedGamesList, actualModel.getGamesList());
        assertEquals(expectedFilteredGamesList, actualModel.getFilteredGamesList());
    }
    /**
     * Updates {@code model}'s filtered list to show only the person at the given {@code targetIndex} in the
     * {@code model}'s address book.
     */
    public static void showPersonAtIndex(Model model, Index targetIndex) {
        assertTrue(targetIndex.getZeroBased() < model.getFilteredFriendsList().size());

        Friend friend = model.getFilteredFriendsList().get(targetIndex.getZeroBased());
        final String[] splitName = friend.getName().fullName.split("\\s+");
        model.updateFilteredFriendsList(new FriendNameContainsKeywordsPredicate(Arrays.asList(splitName[0])));

        assertEquals(1, model.getFilteredFriendsList().size());
    }

}
