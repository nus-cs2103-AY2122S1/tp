package seedu.anilist.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.anilist.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.anilist.logic.parser.CliSyntax.PREFIX_STATUS;

import seedu.anilist.logic.commands.TabCommand;
import seedu.anilist.logic.parser.exceptions.ParseException;
import seedu.anilist.model.anime.Status;
import seedu.anilist.model.anime.StatusEqualsPredicate;



public class TabCommandParser implements Parser<TabCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the TabCommand
     * and returns a TabCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public TabCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_STATUS);
        Status statusToMatch;

        if (argMultimap.getValue(PREFIX_STATUS).isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    TabCommand.MESSAGE_USAGE));
        }

        statusToMatch = ParserUtil.parseStatus(argMultimap.getValue(PREFIX_STATUS).get());

        return new TabCommand(new StatusEqualsPredicate(statusToMatch));
    }

}
