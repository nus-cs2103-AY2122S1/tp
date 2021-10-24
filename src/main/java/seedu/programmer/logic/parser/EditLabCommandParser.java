package seedu.programmer.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.programmer.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.programmer.logic.parser.CliSyntax.*;
import static seedu.programmer.logic.parser.CliSyntax.PREFIX_NAME;

import java.util.stream.Stream;

import seedu.programmer.commons.core.index.Index;
import seedu.programmer.logic.commands.AddCommand;
import seedu.programmer.logic.commands.AddLabCommand;
import seedu.programmer.logic.commands.EditCommand;
import seedu.programmer.logic.commands.EditLabCommand;
import seedu.programmer.logic.parser.exceptions.ParseException;
import seedu.programmer.model.student.Lab;


/**
 * Parses input arguments and creates a new CreateLabResultCommand object
 */
public class EditLabCommandParser implements Parser<EditLabCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the AddCommand
     * and returns an AddCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public EditLabCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_LAB_TITLE, PREFIX_LAB_NEW_TITLE, PREFIX_LAB_TOTAL);

        if (!argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditLabCommand.MESSAGE_USAGE));
        }

        if (argMultimap.getValue(PREFIX_LAB_NEW_TITLE).isPresent() &&
                argMultimap.getValue(PREFIX_LAB_TOTAL).isPresent()) {
            String title = ParserUtil.parseTitle(argMultimap.getValue(PREFIX_LAB_TITLE).orElse(null));
            String newTitle = ParserUtil.parseTitle(argMultimap.getValue(PREFIX_LAB_NEW_TITLE).orElse(null));
            Double total = ParserUtil.parseResult(argMultimap.getValue(PREFIX_LAB_TOTAL).orElse(null));
            Lab labResult = new Lab(title);
            return new EditLabCommand(labResult, newTitle, total);
        } else if (argMultimap.getValue(PREFIX_LAB_NEW_TITLE).isPresent()) {
            String title = ParserUtil.parseTitle(argMultimap.getValue(PREFIX_LAB_TITLE).orElse(null));
            String newTitle = ParserUtil.parseTitle(argMultimap.getValue(PREFIX_LAB_NEW_TITLE).orElse(null));
            Lab labResult = new Lab(title);
            return new EditLabCommand(labResult, newTitle);
        } else if (argMultimap.getValue(PREFIX_LAB_TOTAL).isPresent()) {
            String title = ParserUtil.parseTitle(argMultimap.getValue(PREFIX_LAB_TITLE).orElse(null));
            Double total = ParserUtil.parseResult(argMultimap.getValue(PREFIX_LAB_TOTAL).orElse(null));
            Lab labResult = new Lab(title);
            return new EditLabCommand(labResult, total);
        } else {
            Lab labResult = new Lab(null);
            return new EditLabCommand(labResult, null, null);
        }
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values
     * in the given {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

}
