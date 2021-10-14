package seedu.programmer.logic.parser;

import static seedu.programmer.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.programmer.logic.parser.CliSyntax.PREFIX_INDEX;
import static seedu.programmer.logic.parser.CliSyntax.PREFIX_LAB_RESULT;
import static seedu.programmer.logic.parser.CliSyntax.PREFIX_LAB_TITLE;
import static seedu.programmer.logic.parser.CliSyntax.PREFIX_LAB_TOTAL;

import java.util.stream.Stream;

import seedu.programmer.commons.core.index.Index;
import seedu.programmer.logic.commands.AddCommand;
import seedu.programmer.logic.commands.CreateLabResultCommand;
import seedu.programmer.logic.parser.exceptions.ParseException;
import seedu.programmer.model.student.LabResult;


/**
 * Parses input arguments and creates a new CreateLabResultCommand object
 */
//todo: for test of show feature only
public class CreateLabResultCommandParser implements Parser<CreateLabResultCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the AddCommand
     * and returns an AddCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public CreateLabResultCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_INDEX, PREFIX_LAB_TITLE, PREFIX_LAB_RESULT, PREFIX_LAB_TOTAL);

        if (!arePrefixesPresent(argMultimap, PREFIX_INDEX, PREFIX_LAB_TITLE, PREFIX_LAB_RESULT, PREFIX_LAB_TOTAL)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE));
        }

        Index index = Index.fromOneBased(ParserUtil.parseNumber(argMultimap.getValue(PREFIX_INDEX).orElse(null)));
        String title = ParserUtil.parseTitle(argMultimap.getValue(PREFIX_LAB_TITLE).orElse(null));
        Double result = ParserUtil.parseResult(argMultimap.getValue(PREFIX_LAB_RESULT).orElse(null));
        Double total = ParserUtil.parseTotal(argMultimap.getValue(PREFIX_LAB_TOTAL).orElse(null));
        LabResult labResult = new LabResult(title, result, total);

        return new CreateLabResultCommand(index, labResult);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values
     * in the given {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

}
