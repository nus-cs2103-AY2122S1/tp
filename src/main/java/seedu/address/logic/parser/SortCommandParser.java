package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.SortCommand.SUPPORTED_PREFIXES;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import seedu.address.logic.commands.SortCommand;
import seedu.address.logic.parser.exceptions.ParseException;

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
        List<String> tokens = Stream.of(args.split("\\s+"))
                .filter(str -> !str.isBlank())
                .collect(Collectors.toList());
        if (tokens.isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, SortCommand.MESSAGE_USAGE));
        }

        List<Prefix> prefixes = tokens.stream().map(Prefix::new).collect(Collectors.toList());
        if (!prefixes.stream().allMatch(SUPPORTED_PREFIXES::contains)) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, SortCommand.MESSAGE_USAGE));
        }

        return new SortCommand(filterLastPrefixOccurrence(prefixes));
    }

    /**
     * Returns a list of distinct prefixes in {@code prefixes} in the order of the last occurrences
     * of each prefix.
     */
    private static List<Prefix> filterLastPrefixOccurrence(List<Prefix> prefixes) {
        return prefixes.stream().distinct()
                .sorted(Comparator.comparingInt(prefixes::lastIndexOf))
                .collect(Collectors.toList());
    }
}
