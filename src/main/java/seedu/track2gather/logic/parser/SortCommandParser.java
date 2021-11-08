package seedu.track2gather.logic.parser;

import static seedu.track2gather.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.track2gather.logic.commands.SortCommand.SUPPORTED_PREFIXES;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import seedu.track2gather.logic.commands.SortCommand;
import seedu.track2gather.logic.commands.SortCommand.Direction;
import seedu.track2gather.logic.parser.exceptions.ParseException;

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
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, SUPPORTED_PREFIXES.toArray(new Prefix[0]));
        List<Prefix> prefixes = argMultimap.getPrefixes().stream()
                .filter(prefix -> !prefix.toString().isEmpty())
                .collect(Collectors.toList());

        if (prefixes.size() == 0 || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, SortCommand.MESSAGE_USAGE));
        }

        List<Direction> directions = new ArrayList<>();
        for (Prefix prefix : prefixes) {
            String value = argMultimap.getValue(prefix).get();

            if (value.isEmpty() || value.equals(Direction.ASCENDING.toString())) {
                directions.add(Direction.ASCENDING);
            } else if (value.equals(Direction.DESCENDING.toString())) {
                directions.add(Direction.DESCENDING);
            } else {
                throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, SortCommand.MESSAGE_USAGE));
            }
        }

        return new SortCommand(prefixes, directions);
    }
}
