package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.FLAG_GAME_ID;
import static seedu.address.logic.parser.CliSyntax.FLAG_TIME;

import java.time.DayOfWeek;

import seedu.address.logic.commands.RecommendCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.game.GameId;
import seedu.address.model.time.HourOfDay;

/**
 * Represents a {@code Parser} which parses user input to produce a {@code RecommendCommand}.
 */
class RecommendCommandParser implements Parser<RecommendCommand> {
    public static final String MESSAGE_INVALID_TIME_FLAG = "Time flag \"" + FLAG_TIME + "\" should be in the format "
            + "\" " + FLAG_TIME + "HOUR DAY\".";

    @Override
    public RecommendCommand parse(String userInput) throws ParseException {
        ArgumentMultimap argumentMultimap = ArgumentTokenizer.tokenize(userInput, FLAG_GAME_ID, FLAG_TIME);

        if (!ParserUtil.areFlagsPresent(argumentMultimap, FLAG_GAME_ID, FLAG_TIME)
                || argumentMultimap.getValue(FLAG_GAME_ID).isEmpty()
                || argumentMultimap.getValue(FLAG_TIME).isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, RecommendCommand.MESSAGE_USAGE));
        }

        GameId gameId = ParserUtil.parseGameId(argumentMultimap.getValue(FLAG_GAME_ID).get());

        String[] spaceDelimitedHourAndDayUserInputs = argumentMultimap.getValue(FLAG_TIME).get().split(" ");
        if (spaceDelimitedHourAndDayUserInputs.length != 2) {
            throw new ParseException(MESSAGE_INVALID_TIME_FLAG);
        }
        HourOfDay hourFilter = ParserUtil.parseValidRecommendHour(spaceDelimitedHourAndDayUserInputs[0]);
        DayOfWeek dayOfWeek = ParserUtil.parseValidDayOfWeek(spaceDelimitedHourAndDayUserInputs[1]);

        return new RecommendCommand(gameId, hourFilter, dayOfWeek);
    }
}
