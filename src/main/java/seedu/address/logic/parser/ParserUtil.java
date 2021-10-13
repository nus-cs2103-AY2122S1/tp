package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.FLAG_FRIEND_NAME;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Stream;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.StringUtil;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.friend.FriendId;
import seedu.address.model.friend.FriendName;
import seedu.address.model.friend.gamefriendlink.GameFriendLink;
import seedu.address.model.game.GameId;

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
        } else if (friendId.contains(FLAG_FRIEND_NAME.toString().trim())) {
            // TODO set a different message if preferred
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, FriendId.MESSAGE_CONSTRAINTS));
        }
        return new FriendId(trimmedName);
    }

    /**
     * Parses a {@code String gameId} into a {@code GameId}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code gameId} is invalid.
     */
    public static GameId parseGameId(String gameId) throws ParseException {
        requireNonNull(gameId);
        String trimmedName = gameId.trim();
        if (!GameId.isValidGameId(trimmedName)) {
            throw new ParseException(GameId.MESSAGE_CONSTRAINTS);
        }
        return new GameId(trimmedName);
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

    //    /**
    //     * Parses a {@code String gameName} into a {@code Game}.
    //     * Leading and trailing whitespaces will be trimmed.
    //     *
    //     * @throws ParseException if the given {@code gameName} is invalid.
    //     */
    //    public static Game parseGame(String gameName) throws ParseException {
    //        requireNonNull(gameName);
    //        String trimmedGameName = gameName.trim();
    //        if (!Game.isValidGameName(trimmedGameName)) {
    //            throw new ParseException(Game.MESSAGE_CONSTRAINTS);
    //        }
    //        return new Game(trimmedGameName);
    //    }

    /**
     * Parses {@code Collection<String> games} into a {@code Set<GameFriendLink>}.
     */
    public static Set<GameFriendLink> parseGames(Collection<String> games) throws ParseException {
        requireNonNull(games);
        final Set<GameFriendLink> gameSet = new HashSet<>();
        for (String gameName : games) {
            // TODO - Edit command
            // gameSet.add(parseGame(gameName));
        }
        return gameSet;
    }

    /**
     * Parses {@code Collection<String> games} into a {@code Hashmap<String, String>}.
     * The key of the Hashmap is the game name, while the value is the username for that key.
     */
    public static HashMap<String, String> parseGamesAndUsernames(Collection<String> games) throws ParseException {
        requireNonNull(games);
        final HashMap<String, String> gamesHashMap = new HashMap<>();
        games.stream().forEach(segment -> {
            String[] gameAndUsername = segment.split(":");
            String gameName = gameAndUsername[0];
            String inGameUsername = gameAndUsername[1];
            gamesHashMap.put(gameName, inGameUsername);
        });
        return gamesHashMap;
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    public static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Flag... flags) {
        return Stream.of(flags).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
