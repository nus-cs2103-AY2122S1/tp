package seedu.times.logic.parser;

import static seedu.times.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.times.logic.commands.ViewCommand;
import seedu.times.logic.parser.exceptions.ParseException;
import seedu.times.ui.TabName;

/**
 * Parses input command and returns ViewCommand.
 */
public class ViewCommandParser implements Parser<ViewCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the ViewCommand
     * and returns an ViewCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    @Override
    public ViewCommand parse(String args) throws ParseException {

        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, ViewCommand.MESSAGE_USAGE));
        }

        String[] splitArgs = trimmedArgs.split(" ");
        if (splitArgs.length > 1) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, ViewCommand.MESSAGE_USAGE));
        }

        String tabToView = splitArgs[0];
        TabName tab;

        switch (tabToView) {
        case "students":
            tab = TabName.STUDENTS;
            break;
        case "classes":
            tab = TabName.CLASSES;
            break;
        case "timetable":
            tab = TabName.TIMETABLE;
            break;
        default:
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, ViewCommand.INVALID_TAB));
        }

        return new ViewCommand(tab);
    }
}
