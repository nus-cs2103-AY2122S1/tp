package seedu.programmer.logic.parser;

import static seedu.programmer.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.programmer.logic.parser.CliSyntax.PREFIX_LAB_TITLE;

import java.util.stream.Stream;

import seedu.programmer.logic.commands.DeleteLabCommand;
import seedu.programmer.logic.parser.exceptions.ParseException;
import seedu.programmer.model.student.Lab;


/**
 * Parses input arguments and creates a new CreateLabResultCommand object
 */
public class DeleteLabCommandParser implements Parser<DeleteLabCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the AddCommand
     * and returns an AddCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public DeleteLabCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_LAB_TITLE);

        if (!arePrefixesPresent(argMultimap, PREFIX_LAB_TITLE)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteLabCommand.MESSAGE_USAGE));
        }


        String title = ParserUtil.parseTitle(argMultimap.getValue(PREFIX_LAB_TITLE).orElse(""));
        Lab labResult = new Lab(title);

        return new DeleteLabCommand(labResult);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values
     * in the given {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

}
