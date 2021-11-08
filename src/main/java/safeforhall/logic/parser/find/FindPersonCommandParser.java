package safeforhall.logic.parser.find;

import static java.util.Objects.requireNonNull;
import static safeforhall.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import safeforhall.logic.commands.find.FindPersonCommand;
import safeforhall.logic.parser.ArgumentMultimap;
import safeforhall.logic.parser.ArgumentTokenizer;
import safeforhall.logic.parser.CliSyntax;
import safeforhall.logic.parser.Parser;
import safeforhall.logic.parser.ParserUtil;
import safeforhall.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new FindPersonCommand object
 */
public class FindPersonCommandParser implements Parser<FindPersonCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the FindPersonCommand
     * and returns a FindPersonCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public FindPersonCommand parse(String args) throws ParseException {
        requireNonNull(args);
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindPersonCommand.MESSAGE_USAGE));
        }

        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, CliSyntax.PREFIX_NAME, CliSyntax.PREFIX_ROOM,
                        CliSyntax.PREFIX_PHONE, CliSyntax.PREFIX_EMAIL, CliSyntax.PREFIX_VACCSTATUS,
                        CliSyntax.PREFIX_FACULTY);

        FindPersonCommand.FindCompositePredicate findCompositePredicate =
                new FindPersonCommand.FindCompositePredicate();

        setPredicateFields(argMultimap, findCompositePredicate);

        if (!findCompositePredicate.isAnyFieldFiltered()) {
            throw new ParseException(FindPersonCommand.MESSAGE_NOT_FILTERED);
        }

        return new FindPersonCommand(findCompositePredicate);
    }

    private void setPredicateFields(ArgumentMultimap argMultimap,
                            FindPersonCommand.FindCompositePredicate findCompositePredicate) throws ParseException {
        if (argMultimap.getValue(CliSyntax.PREFIX_NAME).isPresent()) {
            findCompositePredicate.setName(ParserUtil.parseName(argMultimap.getValue(CliSyntax.PREFIX_NAME)
                    .get()));
        }
        if (argMultimap.getValue(CliSyntax.PREFIX_ROOM).isPresent()) {
            findCompositePredicate.setRoom(ParserUtil.parseRoomForFind(argMultimap.getValue(CliSyntax.PREFIX_ROOM)
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
    }

}
