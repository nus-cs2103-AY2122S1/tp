package seedu.anilist.logic.parser;

import static seedu.anilist.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.anilist.logic.parser.CliSyntax.PREFIX_GENRE;
import static seedu.anilist.logic.parser.CliSyntax.PREFIX_NAME;

import java.util.List;
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
    private static final String MESSAGE_INVALID_COMMAND_FIND = String.format(MESSAGE_INVALID_COMMAND_FORMAT,
            FindCommand.MESSAGE_USAGE);
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
            argMultimap = ParserUtil.tokenizeWithCheck(args, FindCommand.REQUIRES_PREAMBLE,
                    FindCommand.REQUIRED_PREFIXES, FindCommand.OPTIONAL_PREFIXES);
        } catch (ParseException pe) {
            throw new ParseException(MESSAGE_INVALID_COMMAND_FIND);
        }

        List<String> nameParams = argMultimap.getAllValues(PREFIX_NAME);
        List<String> genreParams = argMultimap.getAllValues(PREFIX_GENRE);
        boolean hasNameParams = nameParams.size() > 0;
        boolean hasGenreParams = genreParams.size() > 0;
        if (hasNameParams) {
            hasValidArguments = true;
            combinedPred = combinedPred.and(new NameContainsKeywordsPredicate(nameParams));
        }

        if (hasGenreParams) {
            hasValidArguments = true;
            combinedPred = combinedPred.and(new GenresContainedPredicate(genreParams));
        }

        if (!hasValidArguments) {
            throw new ParseException(MESSAGE_INVALID_COMMAND_FIND);
        }

        return new FindCommand(combinedPred);
    }

}
