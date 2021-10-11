package tutoraid.logic.parser;

import java.util.stream.Stream;

import tutoraid.commons.core.index.Index;
import tutoraid.logic.commands.AddProgressCommand;
import tutoraid.logic.parser.exceptions.ParseException;
import tutoraid.model.student.Progress;
import tutoraid.commons.core.Messages;

/**
 * Parses input arguments and creates a new PaidCommand object
 */
public class AddProgressCommandParser implements Parser<AddProgressCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of PaidCommand
     * and returns a PaidCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddProgressCommand parse(String args) throws ParseException {
        try {
            args = args.trim() + " ";
            String [] arguments = args.split(" ", 2);

            Index index = ParserUtil.parseIndex(arguments[0]);
            Progress progress = ParserUtil.parseProgress(arguments[1]);

            return new AddProgressCommand(index, progress);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT, AddProgressCommand.MESSAGE_USAGE), pe);
        }

    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
