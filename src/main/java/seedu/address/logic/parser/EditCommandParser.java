package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.FLAG_FRIEND_ID;
import static seedu.address.logic.parser.CliSyntax.FLAG_FRIEND_NAME;
import static seedu.address.logic.parser.CliSyntax.FLAG_GAME_OLD;

import java.util.Collection;
import java.util.Collections;
import java.util.Optional;
import java.util.Set;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.EditCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.friend.gamefriendlink.GameFriendLink;

/**
 * Parses input arguments and creates a new EditCommand object
 */
public class EditCommandParser implements Parser<EditCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the EditCommand
     * and returns an EditCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public EditCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, FLAG_FRIEND_ID, FLAG_FRIEND_NAME, FLAG_GAME_OLD);

        Index index;

        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditCommand.MESSAGE_USAGE), pe);
        }

        EditCommand.EditFriendDescriptor editFriendDescriptor = new EditCommand.EditFriendDescriptor();
        if (argMultimap.getValue(FLAG_FRIEND_ID).isPresent()) {
            editFriendDescriptor.setFriendId(ParserUtil.parseFriendId(argMultimap.getValue(FLAG_FRIEND_ID).get()));
        }

        if (argMultimap.getValue(FLAG_FRIEND_NAME).isPresent()) {
            editFriendDescriptor.setFriendName(ParserUtil.parseFriendName(argMultimap.getValue(FLAG_FRIEND_NAME)
                    .get()));
        }

        parseGamesForEdit(argMultimap.getAllValues(FLAG_GAME_OLD)).ifPresent(editFriendDescriptor::setGames);

        if (!editFriendDescriptor.isAnyFieldEdited()) {
            throw new ParseException(EditCommand.MESSAGE_NOT_EDITED);
        }

        return new EditCommand(index, editFriendDescriptor);
    }

    /**
     * Parses {@code Collection<String> games} into a {@code Set<GameFriendLink>} if {@code games} is non-empty.
     * If {@code games} contain only one element which is an empty string, it will be parsed into a
     * {@code Set<GameFriendLink>} containing zero games.
     */
    private Optional<Set<GameFriendLink>> parseGamesForEdit(Collection<String> games) throws ParseException {
        assert games != null;

        if (games.isEmpty()) {
            return Optional.empty();
        }
        Collection<String> gameSet = games.size() == 1 && games.contains("") ? Collections.emptySet() : games;
        return Optional.of(ParserUtil.parseGames(gameSet));
    }
}
