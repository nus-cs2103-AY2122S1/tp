package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.StringUtil;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.friend.FriendId;
import seedu.address.model.friend.FriendName;
import seedu.address.model.game.Game;

/**
 * Contains utility methods used for parsing strings in the various *Parser classes.
 */
public class ParserUtil {

    public static final String MESSAGE_INVALID_INDEX = "Index is not a non-zero unsigned integer.";

    /**
     * Private constructor to hide implicit public constructor since
     * {@code ParserUtil} is a utility class.
     */
    private ParserUtil() {}

    /**
     * Parses {@code oneBasedIndex} into an {@code Index} and returns it. Leading and trailing whitespaces will be
     * trimmed.
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
        String trimmedName = friendId.trim();
        if (!FriendId.isValidFriendId(trimmedName)) {
            throw new ParseException(FriendId.MESSAGE_CONSTRAINTS);
        }
        return new FriendId(trimmedName);
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
     * Parses a {@code String gameName} into a {@code Game}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code gameName} is invalid.
     */
    public static Game parseGame(String gameName) throws ParseException {
        requireNonNull(gameName);
        String trimmedGameName = gameName.trim();
        if (!Game.isValidGameName(trimmedGameName)) {
            throw new ParseException(Game.MESSAGE_CONSTRAINTS);
        }
        return new Game(trimmedGameName);
    }

    /**
     * Parses {@code Collection<String> games} into a {@code Set<Game>}.
     */
    public static Set<Game> parseGames(Collection<String> games) throws ParseException {
        requireNonNull(games);
        final Set<Game> gameSet = new HashSet<>();
        for (String gameName : games) {
            gameSet.add(parseGame(gameName));
        }
        return gameSet;
    }
}
