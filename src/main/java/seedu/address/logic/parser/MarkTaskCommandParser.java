package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import seedu.address.logic.commands.HelpCommand;
import seedu.address.logic.commands.MarkTaskCommand;
import seedu.address.logic.commands.MarkTaskDoneCommand;
import seedu.address.logic.commands.MarkTaskUndoneCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parse input arguments and creates a new MarkTaskCommand object.
 */
public class MarkTaskCommandParser implements Parser<MarkTaskCommand> {

    private static final Pattern BASIC_COMMAND_FORMAT = Pattern.compile("(?<commandWord>\\S+)(?<arguments>.*)");

    /**
     * Parses the given {@code String} of arguments in the context of the MarkTaskCommand
     * and returns an MarkTaskCommand object for execution.
     *
     * @param args Args for marking the status of a task.
     * @return MarkTaskCommand object created from the user input.
     * @throws ParseException if the user input does not conform the expected format
     */
    public MarkTaskCommand parse(String args) throws ParseException {
        final Matcher matcher = BASIC_COMMAND_FORMAT.matcher(args.trim());
        if (!matcher.matches()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, HelpCommand.MESSAGE_USAGE));
        }

        final String commandWord = "mark " + matcher.group("commandWord");
        final String arguments = matcher.group("arguments");
        switch (commandWord) {
        case MarkTaskDoneCommand.COMMAND_WORD:
            return new MarkTaskDoneCommandParser().parse(arguments);
        case MarkTaskUndoneCommand.COMMAND_WORD:
            return new MarkTaskUndoneCommandParser().parse(arguments);
        default:
            throw new ParseException(MESSAGE_UNKNOWN_COMMAND);
        }
    }
}
