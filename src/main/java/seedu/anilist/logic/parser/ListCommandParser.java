package seedu.anilist.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.anilist.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.anilist.logic.parser.CliSyntax.PREFIX_STATUS;
import static seedu.anilist.model.Model.PREDICATE_SHOW_ALL_ANIME;

import java.util.Optional;

import seedu.anilist.logic.commands.ListCommand;
import seedu.anilist.logic.parser.exceptions.ParseException;
import seedu.anilist.model.anime.Status;
import seedu.anilist.model.anime.StatusEqualsPredicate;




public class ListCommandParser implements Parser<ListCommand> {
    private static final String MESSAGE_INVALID_COMMAND_LIST = String.format(MESSAGE_INVALID_COMMAND_FORMAT,
            ListCommand.MESSAGE_USAGE);

    /**
     * Parses the given {@code String} of arguments in the context of the ListCommand
     * and returns a ListCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public ListCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap;
        try {
            argMultimap = ParserUtil.tokenizeWithCheck(args, ListCommand.REQUIRES_PREAMBLE,
                    ListCommand.REQUIRED_PREFIXES, ListCommand.OPTIONAL_PREFIXES);
        } catch (ParseException pe) {
            throw new ParseException(MESSAGE_INVALID_COMMAND_LIST);
        }

        Optional<String> statusParam = argMultimap.getValue(PREFIX_STATUS);
        boolean hasStatus = statusParam.isPresent();
        if (!hasStatus) {
            return new ListCommand(PREDICATE_SHOW_ALL_ANIME);
        }

        Status statusToMatch = ParserUtil.parseStatus(statusParam.get());

        return new ListCommand(new StatusEqualsPredicate(statusToMatch));
    }
}
