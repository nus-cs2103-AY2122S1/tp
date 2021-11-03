package seedu.anilist.logic.parser;

import static seedu.anilist.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.anilist.logic.parser.CliSyntax.PREFIX_GENRE;
import static seedu.anilist.logic.parser.CliSyntax.PREFIX_NAME;

import java.util.function.Predicate;

import seedu.anilist.logic.commands.FindCommand;
import seedu.anilist.logic.parser.exceptions.ParseException;
import seedu.anilist.model.anime.Anime;
import seedu.anilist.model.anime.GenresContainedPredicate;
import seedu.anilist.model.anime.NameContainsKeywordsPredicate;

/**
 * Parses input arguments and creates a new FindCommand object
 */
public class FindCommandParser implements Parser<FindCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the FindCommand
     * and returns a FindCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public FindCommand parse(String args) throws ParseException {
        Predicate<Anime> combinedPred = unused -> true;
        boolean hasValidArguments = false;

        ArgumentMultimap argMultimap;

        try {
            argMultimap = ParserUtil.tokenizeWithCheck(args, false, new Prefix[] {}, PREFIX_NAME, PREFIX_GENRE);
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
        }

        if (argMultimap.getValue(PREFIX_NAME).isPresent()) {
            hasValidArguments = true;
            combinedPred = combinedPred.and(
                new NameContainsKeywordsPredicate(argMultimap.getAllValues(PREFIX_NAME))
            );
        }

        if (argMultimap.getValue(PREFIX_GENRE).isPresent()) {
            hasValidArguments = true;
            combinedPred = combinedPred.and(
                new GenresContainedPredicate(argMultimap.getAllValues(PREFIX_GENRE))
            );
        }

        if (!hasValidArguments) {
            throw new ParseException(
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
        }

        return new FindCommand(combinedPred);
    }

}
