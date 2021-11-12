package seedu.teachbook.logic.parser;

import static seedu.teachbook.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.teachbook.logic.commands.SortCommand;
import seedu.teachbook.logic.parser.exceptions.ParseException;

public class SortCommandParser implements Parser<SortCommand> {

    /**
     * Parses the user input accordingly in order to distinguish between sorting of grades and sorting of names.
     *
     * @param args user input which should be either "name" or "grade"
     * @return {@code SortCommand} result of parsing the user input.
     * @throws ParseException if the user input does not conform the expected format.
     */
    public SortCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();

        if (trimmedArgs.equals("name")) {
            return new SortCommand(true, false);
        } else if (trimmedArgs.equals("grade")) {
            return new SortCommand(false, true);
        }

        throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, SortCommand.MESSAGE_USAGE));
    }
}
