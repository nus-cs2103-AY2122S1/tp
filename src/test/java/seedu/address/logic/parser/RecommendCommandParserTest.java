package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.GAME_ID_CSGO_DESC;
import static seedu.address.logic.commands.CommandTestUtil.RECOMMEND_INVALID_DAY_FILTER;
import static seedu.address.logic.commands.CommandTestUtil.RECOMMEND_INVALID_GAME_ID;
import static seedu.address.logic.commands.CommandTestUtil.RECOMMEND_INVALID_HOUR_FILTER;
import static seedu.address.logic.commands.CommandTestUtil.RECOMMEND_INVALID_TIME_FLAG_MISSING_DAY;
import static seedu.address.logic.commands.CommandTestUtil.RECOMMEND_INVALID_TIME_FLAG_MISSING_HOUR;
import static seedu.address.logic.commands.CommandTestUtil.RECOMMEND_VALID_CSGO_VALID_HOUR_23_SUNDAY_DESC;
import static seedu.address.logic.commands.CommandTestUtil.RECOMMEND_VALID_VALORANT_VALID_HOUR_ZERO_MONDAY_DESC;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DAY_MONDAY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DAY_SUNDAY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_HOUR_LOWER_BOUNDARY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_HOUR_UPPER_BOUNDARY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TIME_HOUR_23_SUNDAY_DESC;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.logic.parser.ParserUtil.MESSAGE_INVALID_DAY_OF_WEEK;
import static seedu.address.logic.parser.RecommendCommandParser.MESSAGE_INVALID_TIME_FLAG;
import static seedu.address.testutil.TypicalGames.CSGO;
import static seedu.address.testutil.TypicalGames.VALORANT;

import java.time.DayOfWeek;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.RecommendCommand;
import seedu.address.model.game.GameId;
import seedu.address.model.time.HourOfDay;

public class RecommendCommandParserTest {
    private static final Parser<RecommendCommand> parser = new RecommendCommandParser();
    private final String invalidCommandFormatMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT,
            RecommendCommand.MESSAGE_USAGE);

    // heuristic - all valid inputs in at least 1 positive test case
    @Test
    public void parse_allFieldsPresent_success() {
        assertParseSuccess(parser, RECOMMEND_VALID_VALORANT_VALID_HOUR_ZERO_MONDAY_DESC,
                new RecommendCommand(VALORANT.getGameId(), new HourOfDay(VALID_HOUR_LOWER_BOUNDARY),
                        DayOfWeek.of(VALID_DAY_MONDAY)));

        assertParseSuccess(parser, RECOMMEND_VALID_CSGO_VALID_HOUR_23_SUNDAY_DESC,
                new RecommendCommand(CSGO.getGameId(), new HourOfDay(VALID_HOUR_UPPER_BOUNDARY),
                        DayOfWeek.of(VALID_DAY_SUNDAY)));
    }

    // heuristic - 1 invalid input per negative test case
    @Test
    public void parse_missingFields_failure() {
        // missing game id flag
        assertParseFailure(parser, " " + GAME_ID_CSGO_DESC, invalidCommandFormatMessage);

        // missing time flag
        assertParseFailure(parser, " " + VALID_TIME_HOUR_23_SUNDAY_DESC, invalidCommandFormatMessage);
    }

    @Test
    public void parse_incompleteTimeFlag_failure() {
        // missing DAY in -t flag
        assertParseFailure(parser, RECOMMEND_INVALID_TIME_FLAG_MISSING_DAY, MESSAGE_INVALID_TIME_FLAG);

        // missing HOUR in -t flag
        assertParseFailure(parser, RECOMMEND_INVALID_TIME_FLAG_MISSING_HOUR, MESSAGE_INVALID_TIME_FLAG);
    }

    @Test
    public void parse_invalidGameId_failure() {
        assertParseFailure(parser, RECOMMEND_INVALID_GAME_ID, GameId.MESSAGE_INVALID_CHARACTERS);
    }

    @Test
    public void parse_invalidHourOfDay() {
        assertParseFailure(parser, RECOMMEND_INVALID_HOUR_FILTER, HourOfDay.MESSAGE_INVALID_RANGE);
    }

    @Test
    public void parse_invalidDayOfWeek_failure() {
        assertParseFailure(parser, RECOMMEND_INVALID_DAY_FILTER, MESSAGE_INVALID_DAY_OF_WEEK);
    }
}
