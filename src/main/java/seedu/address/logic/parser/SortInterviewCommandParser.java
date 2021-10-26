package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.logic.commands.SortInterviewCommand;
import seedu.address.logic.parser.exceptions.ParseException;

import java.util.Arrays;

public class SortInterviewCommandParser implements Parser<SortInterviewCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the SortInterviewCommand
     * and returns a SortInterviewCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public SortInterviewCommand parse(String args) throws ParseException {
        requireNonNull(args);

        String trimmedArgs = args.trim();

        if (!trimmedArgs.matches(SortInterviewCommand.ValidSortInterviewArgs.getRegex())) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, SortInterviewCommand.MESSAGE_USAGE));
        }

        SortInterviewCommand sortInterviewCommand = Arrays.stream(SortInterviewCommand.ValidSortInterviewArgs.values())
                .filter(arg -> arg.toString().equals(trimmedArgs))
                .findFirst()
                .get()
                .getSortInterviewCommand();

        return sortInterviewCommand;
    }

}
