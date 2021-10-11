package seedu.fast.logic.parser;

import static seedu.fast.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.fast.commons.util.sort.SortByAppointment;
import seedu.fast.commons.util.sort.SortByName;
import seedu.fast.commons.util.sort.SortByPriority;
import seedu.fast.logic.commands.SortCommand;
import seedu.fast.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new SortCommand object
 */
public class SortCommandParser implements Parser<SortCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the SortCommand
     * and returns a SortCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public SortCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, SortCommand.MESSAGE_USAGE));
        }

        switch (trimmedArgs) {

        case SortByName.KEYWORD:
            return new SortCommand(new SortByName(), SortByName.KEYWORD);

        case SortByAppointment.KEYWORD:
            return new SortCommand(new SortByAppointment(), SortByAppointment.KEYWORD);

        case SortByPriority.KEYWORD:
            return new SortCommand(new SortByPriority(), SortByPriority.KEYWORD);

        default:
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, SortCommand.MESSAGE_USAGE));
        }


    }

}
