package seedu.address.logic.parser.friends;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.VALID_FRIEND_ID_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_FRIEND_ID_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_GAME_ID_APEX_LEGENDS;
import static seedu.address.logic.commands.CommandTestUtil.VALID_GAME_ID_CSGO;
import static seedu.address.logic.parser.CliSyntax.FLAG_ADD_GAME_SKILL;
import static seedu.address.logic.parser.CliSyntax.FLAG_GAME;
import static seedu.address.logic.parser.CliSyntax.FLAG_POSTFIX;
import static seedu.address.logic.parser.CliSyntax.FLAG_VALUE;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.model.friend.FriendId.MESSAGE_EMPTY_FRIEND_ID;
import static seedu.address.model.friend.FriendId.MESSAGE_INVALID_CHARACTERS;
import static seedu.address.testutil.TypicalFriends.AMY;
import static seedu.address.testutil.TypicalFriends.BOB;
import static seedu.address.testutil.TypicalGames.APEX_LEGENDS;
import static seedu.address.testutil.TypicalGames.CSGO;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.friends.AddFriendGameSkillCommand;
import seedu.address.model.game.GameId;
import seedu.address.model.gamefriendlink.SkillValue;

public class AddFriendGameSkillCommandParserTest {
    private static final String INVALID_FRIEND_ID_WITH_SPACES = "id with spaces";
    private static final String INVALID_FRIEND_ID_INVALID_CHAR = "!NV@L$D#C&A%";
    private static final String INVALID_GAME_ID_WITH_SPACES = "game with spaces";
    private static final String INVALID_GAME_ID_INVALID_CHAR = "!NV@L$D#C&A%";
    private final AddFriendGameSkillCommandParser parser = new AddFriendGameSkillCommandParser();
    private final String invalidCommandFormatMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT,
            AddFriendGameSkillCommand.MESSAGE_USAGE);

    @Test
    public void parse_allFlagsPresent_success() {
        String allFlagsPresentInput = FLAG_POSTFIX.getFlag() + FLAG_ADD_GAME_SKILL + VALID_FRIEND_ID_AMY
                + " " + FLAG_GAME + VALID_GAME_ID_CSGO + " " + FLAG_VALUE + "3";
        assertParseSuccess(parser, allFlagsPresentInput, new AddFriendGameSkillCommand(AMY.getFriendId(),
                CSGO.getGameId(), new SkillValue(3)));

        String allFlagsPresentInputLowerBound = FLAG_POSTFIX.getFlag() + FLAG_ADD_GAME_SKILL + VALID_FRIEND_ID_AMY
                + " " + FLAG_GAME + VALID_GAME_ID_APEX_LEGENDS + " " + FLAG_VALUE + "0";
        assertParseSuccess(parser, allFlagsPresentInputLowerBound, new AddFriendGameSkillCommand(AMY.getFriendId(),
                APEX_LEGENDS.getGameId(), new SkillValue(0)));

        String allFlagsPresentInputUpperBound = FLAG_POSTFIX.getFlag() + FLAG_ADD_GAME_SKILL + VALID_FRIEND_ID_BOB
                + " " + FLAG_GAME + VALID_GAME_ID_CSGO + " " + FLAG_VALUE + "10";
        assertParseSuccess(parser, allFlagsPresentInputUpperBound, new AddFriendGameSkillCommand(BOB.getFriendId(),
                CSGO.getGameId(), new SkillValue(10)));
    }

    @Test
    public void parse_missingFriendId_failure() {
        String missingFriendIdInput = FLAG_POSTFIX.getFlag() + FLAG_ADD_GAME_SKILL
                + " " + FLAG_GAME + VALID_GAME_ID_CSGO + " " + FLAG_VALUE + "3";
        assertParseFailure(parser, missingFriendIdInput, MESSAGE_EMPTY_FRIEND_ID);
    }

    @Test
    public void parse_missingGameFlag_failure() {
        String missingGameFlagInput = FLAG_POSTFIX.getFlag() + FLAG_ADD_GAME_SKILL
                + VALID_FRIEND_ID_AMY + " " + FLAG_VALUE + "3";
        assertParseFailure(parser, missingGameFlagInput, invalidCommandFormatMessage);
    }

    @Test
    public void parse_missingValue_failure() {
        String missingValueFlagInput = FLAG_POSTFIX.getFlag() + FLAG_ADD_GAME_SKILL + VALID_FRIEND_ID_AMY
                + " " + FLAG_GAME + VALID_GAME_ID_CSGO;
        assertParseFailure(parser, missingValueFlagInput, invalidCommandFormatMessage);
    }

    @Test
    public void parse_invalidFriendId_failure() {
        String invalidFriendIdWithSpacesInput = FLAG_POSTFIX.getFlag() + FLAG_ADD_GAME_SKILL
                + INVALID_FRIEND_ID_WITH_SPACES + " " + FLAG_GAME + VALID_GAME_ID_CSGO + " " + FLAG_VALUE + "3";
        assertParseFailure(parser, invalidFriendIdWithSpacesInput, MESSAGE_INVALID_CHARACTERS);

        String invalidFriendIdWithInvalidChar = FLAG_POSTFIX.getFlag() + FLAG_ADD_GAME_SKILL
                + INVALID_FRIEND_ID_INVALID_CHAR + " " + FLAG_GAME + VALID_GAME_ID_CSGO + " " + FLAG_VALUE + "3";
        assertParseFailure(parser, invalidFriendIdWithInvalidChar, MESSAGE_INVALID_CHARACTERS);
    }

    @Test
    public void parse_invalidGameId_failure() {
        String invalidGameIdWithSpacesInput = FLAG_POSTFIX.getFlag() + FLAG_ADD_GAME_SKILL
                + VALID_FRIEND_ID_AMY + " " + FLAG_GAME + INVALID_GAME_ID_WITH_SPACES + " " + FLAG_VALUE + "3";
        assertParseFailure(parser, invalidGameIdWithSpacesInput, GameId.MESSAGE_INVALID_CHARACTERS);

        String invalidGameIdWithInvalidChar = FLAG_POSTFIX.getFlag() + FLAG_ADD_GAME_SKILL
                + VALID_FRIEND_ID_AMY + " " + FLAG_GAME + INVALID_GAME_ID_INVALID_CHAR + " " + FLAG_VALUE + "3";
        assertParseFailure(parser, invalidGameIdWithInvalidChar, GameId.MESSAGE_INVALID_CHARACTERS);
    }

    @Test
    public void parse_invalidSkillValue_failure() {
        String invalidValueNotIntegerInput = FLAG_POSTFIX.getFlag() + FLAG_ADD_GAME_SKILL
                + VALID_FRIEND_ID_AMY + " " + FLAG_GAME + VALID_GAME_ID_CSGO + " " + FLAG_VALUE + "non integer";
        assertParseFailure(parser, invalidValueNotIntegerInput, SkillValue.MESSAGE_CONSTRAINTS);

        String invalidGameIdWithExceedLowerBound = FLAG_POSTFIX.getFlag() + FLAG_ADD_GAME_SKILL
                + VALID_FRIEND_ID_AMY + " " + FLAG_GAME + VALID_GAME_ID_CSGO + " " + FLAG_VALUE + "-1";
        assertParseFailure(parser, invalidGameIdWithExceedLowerBound, SkillValue.MESSAGE_CONSTRAINTS);
    }
}
