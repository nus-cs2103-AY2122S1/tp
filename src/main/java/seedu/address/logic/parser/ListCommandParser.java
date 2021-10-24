package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.logic.commands.ListCommand;
import seedu.address.logic.parser.exceptions.ParseException;

public class ListCommandParser implements Parser<ListCommand> {
    private static final String ARGS_LIST_ALL = "";
    private static final String ARGS_LIST_WEEK_TRIMMED = "w/";
    private static final String ARGS_LIST_MONTH_TRIMMED = "m/";

    /**
     * Parses the given {@code String} of arguments in the context of the ListCommand
     * @throws ParseException if the user input does not conform the expected format
     */
    public ListCommand parse(String args) throws ParseException {
        String argsTrimmed = args.trim();

        if (argsTrimmed.equals(ARGS_LIST_ALL)) {
            return new ListCommand();
        } else if (argsTrimmed.equals(ARGS_LIST_MONTH_TRIMMED)) {
            return new ListCommand(true, true);
        } else if (argsTrimmed.equals(ARGS_LIST_WEEK_TRIMMED)) {
            return new ListCommand(true, false);
        } else {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, ListCommand.MESSAGE_USAGE));
        }
    }


}
