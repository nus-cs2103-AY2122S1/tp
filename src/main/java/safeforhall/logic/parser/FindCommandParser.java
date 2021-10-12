package safeforhall.logic.parser;

import static java.util.Objects.requireNonNull;
import static safeforhall.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import safeforhall.logic.commands.FindCommand;
import safeforhall.logic.parser.exceptions.ParseException;

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
        requireNonNull(args);
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
        }

        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, CliSyntax.PREFIX_NAME, CliSyntax.PREFIX_ROOM,
                        CliSyntax.PREFIX_PHONE, CliSyntax.PREFIX_EMAIL, CliSyntax.PREFIX_VACCSTATUS,
                        CliSyntax.PREFIX_FACULTY);

        FindCommand.FindCompositePredicate findCompositePredicate = new FindCommand.FindCompositePredicate();
        if (argMultimap.getValue(CliSyntax.PREFIX_NAME).isPresent()) {
            findCompositePredicate.setName(ParserUtil.parseName(argMultimap.getValue(CliSyntax.PREFIX_NAME)
                    .get()));
        }
        if (argMultimap.getValue(CliSyntax.PREFIX_ROOM).isPresent()) {
            findCompositePredicate.setRoom(ParserUtil.parseRoom(argMultimap.getValue(CliSyntax.PREFIX_ROOM)
                    .get()));
        }
        if (argMultimap.getValue(CliSyntax.PREFIX_PHONE).isPresent()) {
            findCompositePredicate.setPhone(ParserUtil.parsePhone(argMultimap.getValue(CliSyntax.PREFIX_PHONE)
                    .get()));
        }
        if (argMultimap.getValue(CliSyntax.PREFIX_EMAIL).isPresent()) {
            findCompositePredicate.setEmail(ParserUtil.parseEmail(argMultimap.getValue(CliSyntax.PREFIX_EMAIL)
                    .get()));
        }
        if (argMultimap.getValue(CliSyntax.PREFIX_VACCSTATUS).isPresent()) {
            findCompositePredicate.setVaccStatus(ParserUtil.parseVaccStatus(argMultimap
                    .getValue(CliSyntax.PREFIX_VACCSTATUS).get()));
        }
        if (argMultimap.getValue(CliSyntax.PREFIX_FACULTY).isPresent()) {
            findCompositePredicate.setFaculty(ParserUtil.parseFaculty(argMultimap.getValue(CliSyntax.PREFIX_FACULTY)
                    .get()));
        }

        if (!findCompositePredicate.isAnyFieldFiltered()) {
            throw new ParseException(FindCommand.MESSAGE_NOT_FILTERED);
        }

        return new FindCommand(findCompositePredicate);
    }

}
