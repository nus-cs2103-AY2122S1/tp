package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.time.HourOfDay.MESSAGE_HOUR_OF_DAY_MUST_BE_INT;
import static seedu.address.model.time.HourOfDay.MESSAGE_INVALID_RANGE;

import java.time.DateTimeException;
import java.time.DayOfWeek;
import java.util.stream.Stream;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.StringUtil;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.friend.FriendId;
import seedu.address.model.friend.FriendName;
import seedu.address.model.game.GameId;
import seedu.address.model.gamefriendlink.SkillValue;
import seedu.address.model.gamefriendlink.UserName;
import seedu.address.model.time.HourOfDay;

/**
 * Contains utility methods used for parsing strings in the various *Parser classes.
 */
public class ParserUtil {

    public static final String MESSAGE_INVALID_INDEX = "Index is not a non-zero unsigned integer.";
    public static final String MESSAGE_INVALID_DAY_OF_WEEK = "The day provided must an integer within the range 1 - 7 "
            + "inclusive.";

    /**
     * Private constructor to hide implicit public constructor since
     * {@code ParserUtil} is a utility class.
     */
    private ParserUtil() {
    }

    /**
     * Parses {@code oneBasedIndex} into an {@code Index} and returns it. Leading and trailing whitespaces will be
     * trimmed.
     *
     * @throws ParseException if the specified index is invalid (not non-zero unsigned integer).
     */
    public static Index parseIndex(String oneBasedIndex) throws ParseException {
        String trimmedIndex = oneBasedIndex.trim();
        if (!StringUtil.isNonZeroUnsignedInteger(trimmedIndex)) {
            throw new ParseException(MESSAGE_INVALID_INDEX);
        }
        return Index.fromOneBased(Integer.parseInt(trimmedIndex));
    }

    /**
     * Parses a {@code String friendId} into a {@code FriendId}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code friendId} is invalid.
     */
    public static FriendId parseFriendId(String friendId) throws ParseException {
        requireNonNull(friendId);

        String trimmedId = friendId.trim();
        if (friendId.isBlank()) {
            throw new ParseException(FriendId.MESSAGE_EMPTY_FRIEND_ID);
        } else if (!FriendId.isValidFriendId(trimmedId)) {
            throw new ParseException(FriendId.MESSAGE_INVALID_CHARACTERS);
        }

        return new FriendId(trimmedId);
    }

    /**
     * Parses a {@code String friendName} into a {@code FriendName}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code friendName} is invalid.
     */
    public static FriendName parseFriendName(String friendName) throws ParseException {
        requireNonNull(friendName);

        String trimmedName = friendName.trim();
        if (!FriendName.isValidName(trimmedName)) {
            throw new ParseException(FriendName.MESSAGE_CONSTRAINTS);
        }

        return new FriendName(trimmedName);
    }

    /**
     * Parses a {@code String gameId} into a {@code GameId}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code gameId} is invalid.
     */
    public static GameId parseGameId(String gameId) throws ParseException {
        requireNonNull(gameId);

        String trimmedId = gameId.trim();
        if (!GameId.isValidGameId(trimmedId)) {
            throw new ParseException(GameId.MESSAGE_INVALID_CHARACTERS);
        }

        return new GameId(trimmedId);
    }

    /**
     * Takes in {@code userName} and returns the corresponding {@code UserName} if its format is valid.
     */
    public static UserName parseUserName(String userName) throws ParseException {
        requireNonNull(userName);

        String trimmedName = userName.trim();
        if (!UserName.isValidUserName(trimmedName)) {
            throw new ParseException(UserName.MESSAGE_CONSTRAINTS);
        }

        return new UserName(trimmedName);
    }

    /**
     * Takes in {@code recommendationHourFilter} and returns the corresponding {@code HourOfDay} if its format and
     * value is valid.
     *
     * @param recommendationHourFilter user input for hour of day to filter for recommendation.
     * @return valid {@code HourOfDay}
     * @throws ParseException thrown when format or value of trimmed hour filter provided is invalid.
     */
    public static HourOfDay parseValidRecommendHour(String recommendationHourFilter) throws ParseException {
        int hourOfDay = parseInteger(recommendationHourFilter.trim(), MESSAGE_HOUR_OF_DAY_MUST_BE_INT);
        if (!HourOfDay.isValidHourOfDay(hourOfDay)) {
            throw new ParseException(MESSAGE_INVALID_RANGE);
        }
        return new HourOfDay(hourOfDay);
    }

    /**
     * Takes in {@code dayOfWeek} and returns the corresponding {@code DayOfWeek} if its format and
     * value is valid.
     *
     * @param dayOfWeek user input for day of week to filter.
     * @return valid {@code DayOfWeek}
     * @throws ParseException thrown when format or value of trimmed day filter provided is invalid.
     */
    public static DayOfWeek parseValidDayOfWeek(String dayOfWeek) throws ParseException {
        String dayOfWeekNotIntegerMessage = "The day provided must be an integer value.";
        int dayOfWeekVal = parseInteger(dayOfWeek.trim(), dayOfWeekNotIntegerMessage);
        try {
            return DayOfWeek.of(dayOfWeekVal);
        } catch (DateTimeException dte) {
            throw new ParseException(MESSAGE_INVALID_DAY_OF_WEEK);
        }
    }

    /**
     * Takes in {@code skillVal} and returns the corresponding {@code SkillValue} if its range is valid.
     */
    public static SkillValue parseSkillValue(String skillVal) throws ParseException {
        Integer skillValue = parseInteger(skillVal, SkillValue.MESSAGE_CONSTRAINTS);
        if (!SkillValue.validateSkillValue(skillValue)) {
            throw new ParseException(SkillValue.MESSAGE_CONSTRAINTS);
        }

        return new SkillValue(skillValue);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    public static boolean areFlagsPresent(ArgumentMultimap argumentMultimap, Flag... flags) {
        return Stream.of(flags).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

    private static int parseInteger(String integerString, String errorMessage) throws ParseException {
        try {
            return Integer.parseInt(integerString);
        } catch (NumberFormatException nfe) {
            throw new ParseException(errorMessage);
        }
    }
}
