package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.Arrays;

import seedu.address.logic.commands.FilterInterviewCommand;
import seedu.address.logic.parser.exceptions.ParseException;

public class FilterInterviewCommandParser implements Parser<FilterInterviewCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the FilterInterviewCommand
     * and returns a FilterInterviewCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public FilterInterviewCommand parse(String args) throws ParseException {
        requireNonNull(args);

        String errorMessage = FilterInterviewCommand.MESSAGE_USAGE;
        String trimmedArgs = args.trim().toLowerCase();

        if (!trimmedArgs.matches(FilterInterviewCommand.ValidFilterInterviewArgs.getRegex())) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, errorMessage));
        }

        FilterInterviewCommand filterInterviewCommand = Arrays
                .stream(FilterInterviewCommand.ValidFilterInterviewArgs.values())
                .filter(arg -> arg.toString().equals(trimmedArgs))
                .findFirst()
                .get()
                .getFilterInterviewCommand();

        return filterInterviewCommand;
    }

}
