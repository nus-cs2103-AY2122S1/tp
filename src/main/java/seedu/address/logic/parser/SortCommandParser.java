package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_COMPANY_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DEADLINE_OF_APPLICATION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_INTERNSHIP_POSITION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_INTERVIEW_DATE_AND_TIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PRIORITY;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import seedu.address.logic.commands.SortCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new SortCommand object
 */
public class SortCommandParser implements Parser<SortCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the SortCommand
     * and returns a SortCommand object for execution.
     * @throws ParseException if the user input does not conform to the expected format
     */
    public SortCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_COMPANY_NAME, PREFIX_INTERNSHIP_POSITION,
                        PREFIX_DEADLINE_OF_APPLICATION, PREFIX_INTERVIEW_DATE_AND_TIME, PREFIX_PRIORITY);

        int numberOfPrefixes = numberOfPrefixesPresent(argMultimap, PREFIX_COMPANY_NAME, PREFIX_INTERNSHIP_POSITION,
                PREFIX_DEADLINE_OF_APPLICATION, PREFIX_INTERVIEW_DATE_AND_TIME, PREFIX_PRIORITY);

        if (numberOfPrefixes == 0 || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, SortCommand.MESSAGE_USAGE));
        }

        if (numberOfPrefixes > 1) {
            throw new ParseException(SortCommand.MESSAGE_MULTIPLE_PREFIXES);
        }

        String parameter = (String) getParamAndPrefix(argMultimap).get(0);
        Prefix prefix = (Prefix) getParamAndPrefix(argMultimap).get(1);

        if (!argMultimap.getValue(prefix).equals(Optional.of(""))) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, SortCommand.MESSAGE_USAGE));
        }

        return new SortCommand(parameter);
    }

    /**
     * Returns the matching parameter and corresponding prefix to the user input.
     * @param argMultimap The ArgumentMultimap object that contains the prefixes inputted by the user.
     * @return The parameter and prefix input by the user.
     */
    private List<Object> getParamAndPrefix(ArgumentMultimap argMultimap) {
        String parameter = null;
        Prefix prefix = null;

        if (argMultimap.getValue(PREFIX_COMPANY_NAME).isPresent()) {
            parameter = "company";
            prefix = PREFIX_COMPANY_NAME;
        }
        if (argMultimap.getValue(PREFIX_INTERNSHIP_POSITION).isPresent()) {
            parameter = "position";
            prefix = PREFIX_INTERNSHIP_POSITION;
        }
        if (argMultimap.getValue(PREFIX_DEADLINE_OF_APPLICATION).isPresent()) {
            parameter = "deadline";
            prefix = PREFIX_DEADLINE_OF_APPLICATION;
        }
        if (argMultimap.getValue(PREFIX_INTERVIEW_DATE_AND_TIME).isPresent()) {
            parameter = "interview";
            prefix = PREFIX_INTERVIEW_DATE_AND_TIME;
        }
        if (argMultimap.getValue(PREFIX_PRIORITY).isPresent()) {
            parameter = "priority";
            prefix = PREFIX_PRIORITY;
        }

        List<Object> result = new ArrayList<>();
        result.add(parameter);
        result.add(prefix);
        return result;
    }

    /**
     * Returns the number of prefixes present in the user input.
     */
    private static int numberOfPrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        int i;
        int count = 0;
        for (i = 0; i < prefixes.length; i++) {
            if (argumentMultimap.getValue(prefixes[i]).isPresent()) {
                count++;
            }
        }
        return count;
    }

}
