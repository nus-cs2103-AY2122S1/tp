package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ALL_FLAG;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TASK_INDEX;

import java.util.ArrayList;
import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.DeleteCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new DeleteCommand object
 */
public class DeleteCommandParser implements Parser<Command> {

    /**
     * Parses the given {@code String} of arguments in the context of the DeleteCommand
     * and returns a DeleteCommand object for execution.
     * @throws ParseException if the user input does not conform to the expected format with or without flags.
     */
    public Command parse(String args) throws ParseException {

        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_TASK_INDEX, PREFIX_ALL_FLAG);
        if (argMultimap.getValue(PREFIX_ALL_FLAG).isPresent()) {
            if (args.trim().equals(PREFIX_ALL_FLAG.getPrefix())) {
                return new DeleteCommand();
            } else {
                throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteCommand.MESSAGE_USAGE));
            }
        }

        if (argMultimap.getValue(PREFIX_TASK_INDEX).isPresent()) {
            return new DeleteTaskCommandParser().parse(args);
        }

        String trimmedArgs = args.trim();
        if (!trimmedArgs.startsWith("-m")) {
            return parseWithoutFlag(trimmedArgs);
        } else {
            return parseWithFlag(trimmedArgs);
        }
    }

    /**
     * Parses the given {@code String} of trimmed arguments in the context of the DeleteCommand
     * and returns a DeleteCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format of the command without the flag.
     */
    public DeleteCommand parseWithoutFlag(String trimmedArgs) throws ParseException {
        try {
            Index index = ParserUtil.parseIndex(trimmedArgs);
            return new DeleteCommand(index);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteCommand.MESSAGE_USAGE), pe);
        }
    }

    /**
     * Parses the given {@code String} of trimmed arguments in the context of the DeleteCommand
     * and returns a DeleteCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format of the command with the flag.
     */
    public DeleteCommand parseWithFlag(String trimmedArgs) throws ParseException {
        String[] flagsAndIndexes = trimmedArgs.split("-m", 2);
        if (flagsAndIndexes[1].isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteCommand.MESSAGE_USAGE));
        } else {
            try {
                String[] indexes = flagsAndIndexes[1].trim().split(" ");
                List<Index> preppedIndexes = new ArrayList<>();
                for (String indexString : indexes) {
                    preppedIndexes.add(ParserUtil.parseIndex(indexString));
                }
                return new DeleteCommand(preppedIndexes);
            } catch (ParseException pe) {
                throw new ParseException(
                        String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteCommand.MESSAGE_USAGE), pe);
            }
        }
    }
}
