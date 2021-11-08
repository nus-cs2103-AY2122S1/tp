package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_WEEK;

import java.util.ArrayList;
import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.MarkStudentAttCommand;
import seedu.address.logic.commands.MarkStudentPartCommand;
import seedu.address.logic.commands.MarkTaskDoneCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.student.Attendance;

/**
 * Parses input arguments and creates a new MarkTaskDoneCommand object.
 */
public class MarkTaskDoneCommandParser implements Parser<MarkTaskDoneCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the DeleteCommand
     * and returns a DeleteCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public MarkTaskDoneCommand parse(String args) throws ParseException {
        requireNonNull(args);
        String trimmedArgs = args.trim();

        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, MarkTaskDoneCommand.MESSAGE_USAGE));
        }

        try {
            List<Index> tasksToMark = ParserUtil.parseMultipleIndexWithoutDuplicates(trimmedArgs);
            return new MarkTaskDoneCommand(tasksToMark);
        } catch (NumberFormatException e) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, MarkTaskDoneCommand.MESSAGE_USAGE));
        }
    }
}
