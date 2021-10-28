package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.parser.CliSyntax.FLAG_ADD;
import static seedu.address.logic.parser.CliSyntax.FLAG_FREE;
import static seedu.address.logic.parser.CliSyntax.FLAG_FRIEND_NAME;
import static seedu.address.logic.parser.CliSyntax.FLAG_GAME;
import static seedu.address.logic.parser.CliSyntax.FLAG_GAME_ID;
import static seedu.address.logic.parser.CliSyntax.FLAG_PERIOD;
import static seedu.address.logic.parser.CliSyntax.FLAG_SCHEDULE;
import static seedu.address.logic.parser.CliSyntax.FLAG_TIME;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalGames.APEX_LEGENDS;
import static seedu.address.testutil.TypicalGames.CSGO;
import static seedu.address.testutil.TypicalGames.VALORANT;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.commands.friends.EditFriendCommand;
import seedu.address.model.FriendsList;
import seedu.address.model.GamesList;
import seedu.address.model.Model;
import seedu.address.model.friend.Friend;
import seedu.address.model.friend.FriendNameContainsKeywordsPredicate;
import seedu.address.model.game.Game;
import seedu.address.model.game.GameIdContainsKeywordPredicate;
import seedu.address.testutil.EditFriendDescriptorBuilder;

/**
 * Contains helper methods for testing commands.
 */
public class CommandTestUtil {

    public static final String VALID_NAME_AMY = "Amy Bee";
    public static final String VALID_NAME_BOB = "Bob Choo";
    public static final String VALID_FRIEND_ID_AMY = "amyawesome";
    public static final String VALID_FRIEND_ID_BOB = "456";
    public static final String VALID_GAME_ID_CSGO = "CSGO";
    public static final String VALID_GAME_ID_APEX_LEGENDS = "ApexLegends";
    public static final String VALID_USER_NAME_DRACO = "draco#1777";
    public static final String VALID_USER_NAME_OMEGA = "OmegaLynx";

    public static final int VALID_DAY_MONDAY = 1; // valid day range is 1-7 with 1 being monday and 7 being sunday.
    public static final int VALID_DAY_SUNDAY = 7;
    public static final int VALID_HOUR_LOWER_BOUNDARY = 0; // valid hour range is 0 - 23
    public static final int VALID_HOUR_UPPER_BOUNDARY = 23;
    public static final String VALID_START_TIME = "0200";
    public static final String VALID_END_TIME = "0800";
    public static final String VALID_IS_FREE_TIME = "1";
    public static final int INVALID_DAY_ZERO = 0;
    public static final int INVALID_HOUR_LOWER_BOUND = -1;
    public static final int INVALID_HOUR_UPPER_BOUND = 24;

    public static final String FRIEND_ID_DESC_AMY = " " + FLAG_ADD + VALID_FRIEND_ID_AMY;
    public static final String FRIEND_ID_DESC_BOB = " " + FLAG_ADD + VALID_FRIEND_ID_BOB;
    public static final String NAME_DESC_AMY = " " + FLAG_FRIEND_NAME + VALID_NAME_AMY;
    public static final String NAME_DESC_BOB = " " + FLAG_FRIEND_NAME + VALID_NAME_BOB;

    public static final String GAME_DESC_CSGO = " " + FLAG_ADD + CSGO.gameId;
    public static final String GAME_DESC_VALORANT = " " + FLAG_ADD + VALORANT.gameId;
    public static final String GAME_DESC_APEX_LEGENDS = " " + FLAG_ADD + APEX_LEGENDS.gameId;

    public static final String INVALID_GAME_ID = "kickstar*"; // '*' not allowed in GameId
    public static final String INVALID_NAME_DESC = " " + FLAG_FRIEND_NAME + " " + "James&"; // '&' not allowed in names
    public static final String INVALID_GAME_DESC = " " + FLAG_GAME + INVALID_GAME_ID;

    public static final String PREAMBLE_WHITESPACE = "\t  \r";
    public static final String PREAMBLE_NON_EMPTY = "NonEmptyPreamble";

    public static final String SCHEDULE_FRIEND_AMY = " " + FLAG_SCHEDULE + " " + VALID_FRIEND_ID_AMY + " " + FLAG_PERIOD
            + " " + VALID_START_TIME + " " + VALID_END_TIME + " " + VALID_DAY_MONDAY + " " + FLAG_FREE
            + " " + VALID_IS_FREE_TIME;

    public static final String GAME_ID_VALORANT_DESC = FLAG_GAME_ID + " " + VALORANT.getGameId();
    public static final String GAME_ID_CSGO_DESC = FLAG_GAME_ID + " " + CSGO.getGameId();
    public static final String VALID_TIME_HOUR_ZERO_MONDAY_DESC = "" + FLAG_TIME
            + VALID_HOUR_LOWER_BOUNDARY + " " + VALID_DAY_MONDAY;
    public static final String VALID_TIME_HOUR_23_SUNDAY_DESC = "" + FLAG_TIME
            + VALID_HOUR_UPPER_BOUNDARY + " " + VALID_DAY_SUNDAY;

    public static final String RECOMMEND_VALID_VALORANT_VALID_HOUR_ZERO_MONDAY_DESC = " " + GAME_ID_VALORANT_DESC
            + " " + VALID_TIME_HOUR_ZERO_MONDAY_DESC;
    public static final String RECOMMEND_VALID_CSGO_VALID_HOUR_23_SUNDAY_DESC = " " + GAME_ID_CSGO_DESC
            + " " + VALID_TIME_HOUR_23_SUNDAY_DESC;

    public static final String RECOMMEND_INVALID_GAME_ID = " " + FLAG_GAME_ID + INVALID_GAME_ID + " "
            + VALID_TIME_HOUR_ZERO_MONDAY_DESC;
    public static final String RECOMMEND_INVALID_TIME_FLAG_MISSING_HOUR = " " + GAME_ID_CSGO_DESC + " " + FLAG_TIME
            + VALID_DAY_MONDAY;
    public static final String RECOMMEND_INVALID_TIME_FLAG_MISSING_DAY = " " + GAME_ID_CSGO_DESC + " " + FLAG_TIME
            + VALID_HOUR_UPPER_BOUNDARY;
    public static final String RECOMMEND_INVALID_HOUR_FILTER = " " + GAME_ID_CSGO_DESC + " " + FLAG_TIME
            + INVALID_HOUR_LOWER_BOUND + " " + VALID_DAY_MONDAY;
    public static final String RECOMMEND_INVALID_DAY_FILTER = " " + GAME_ID_CSGO_DESC + " " + FLAG_TIME
            + VALID_HOUR_LOWER_BOUNDARY + " " + INVALID_DAY_ZERO;


    public static final EditFriendCommand.EditFriendDescriptor DESC_AMY;
    public static final EditFriendCommand.EditFriendDescriptor DESC_BOB;

    static {
        DESC_AMY = new EditFriendDescriptorBuilder().withFriendId(VALID_FRIEND_ID_AMY)
                .withFriendName(VALID_NAME_AMY).build();
        DESC_BOB = new EditFriendDescriptorBuilder().withFriendId(VALID_FRIEND_ID_BOB)
                .withFriendName(VALID_NAME_BOB).build();
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
        List<Friend> expectedFilteredFriendsList = new ArrayList<>(actualModel.getFilteredAndSortedFriendsList());
        GamesList expectedGamesList = new GamesList(actualModel.getGamesList());
        List<Game> expectedFilteredGamesList = new ArrayList<>(actualModel.getFilteredGamesList());

        assertThrows(CommandException.class, expectedMessage, () -> command.execute(actualModel));
        assertEquals(expectedFriendsList, actualModel.getFriendsList());
        assertEquals(expectedFilteredFriendsList, actualModel.getFilteredAndSortedFriendsList());
        assertEquals(expectedGamesList, actualModel.getGamesList());
        assertEquals(expectedFilteredGamesList, actualModel.getFilteredGamesList());
    }

    /**
     * Updates {@code model}'s filtered list to show only the person at the given {@code targetIndex} in the
     * {@code model}'s address book.
     */
    public static void showPersonAtIndex(Model model, Index targetIndex) {
        assertTrue(targetIndex.getZeroBased() < model.getFilteredAndSortedFriendsList().size());

        Friend friend = model.getFilteredAndSortedFriendsList().get(targetIndex.getZeroBased());
        final String[] splitName = friend.getFriendName().fullName.split("\\s+");
        model.updateFilteredAndSortedFriendsList(new FriendNameContainsKeywordsPredicate(Arrays.asList(splitName[0])));

        assertEquals(1, model.getFilteredAndSortedFriendsList().size());
    }

    /**
     * Updates {@code model}'s filtered list to show only the person at the given {@code targetIndex} in the
     * {@code model}'s address book.
     */
    public static void showGameAtIndex(Model model, Index targetIndex) {
        assertTrue(targetIndex.getZeroBased() < model.getFilteredGamesList().size());

        Game game = model.getFilteredGamesList().get(targetIndex.getZeroBased());
        final String[] splitName = game.getGameId().value.split("\\s+");
        model.updateFilteredGamesList(new GameIdContainsKeywordPredicate(splitName[0]));

        assertEquals(1, model.getFilteredGamesList().size());
    }

}
