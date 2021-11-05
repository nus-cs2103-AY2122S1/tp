package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.ExportCommand;
import seedu.address.logic.commands.ExportCommandAll;
import seedu.address.logic.commands.ExportCommandIndex;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new ExportCommand object.
 */
public class ExportCommandParser implements Parser<ExportCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the ExportCommand
     * and returns a ExportCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public ExportCommand parse(String args) throws ParseException {

        // no index
        if (args.equals("")) {
            return new ExportCommandAll();
        }

        // index given
        try {
            Index index = ParserUtil.parseIndex(args);
            return new ExportCommandIndex(index);
        } catch (ParseException peIndex) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, ExportCommand.MESSAGE_USAGE));
        }

    }
}
