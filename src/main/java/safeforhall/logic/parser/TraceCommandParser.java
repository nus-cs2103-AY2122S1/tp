package safeforhall.logic.parser;

import static java.util.Objects.requireNonNull;
import static safeforhall.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.stream.Stream;

import safeforhall.logic.commands.TraceCommand;
import safeforhall.logic.parser.exceptions.ParseException;

public class TraceCommandParser implements Parser<TraceCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the TraceCommand
     * and returns a TraceCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public TraceCommand parse(String args) throws ParseException {
        requireNonNull(args);
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, TraceCommand.MESSAGE_USAGE));
        }

        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, CliSyntax.PREFIX_RESIDENT,
                CliSyntax.PREFIX_DEPTH, CliSyntax.PREFIX_DURATION);

        if (!arePrefixesPresent(argMultimap, CliSyntax.PREFIX_RESIDENT)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, TraceCommand.MESSAGE_USAGE));
        }

        // Required fields
        // Either name or room need to be valid
        String inputForResident = argMultimap.getValue(CliSyntax.PREFIX_RESIDENT).get();
        try {
            ParserUtil.parseName(inputForResident);
        } catch (ParseException e) {
            try {
                ParserUtil.parseRoom(inputForResident);
            } catch (ParseException pe) {
                throw new ParseException("Information is neither a room or name\n"
                        + TraceCommand.MESSAGE_USAGE);
            }
        }

        // Optional fields
        Integer[] result = parseDepthDuration(argMultimap);

        return new TraceCommand(inputForResident, result[0], result[1]);
    }

    private Integer[] parseDepthDuration(ArgumentMultimap argMultimap) throws ParseException {
        Integer depth;
        Integer duration;
        try {
            depth = Integer.parseInt(argMultimap.getValue(CliSyntax.PREFIX_DEPTH)
                    .orElse(TraceCommand.DEFAULT_DEPTH.toString()));
            duration = Integer.parseInt(argMultimap.getValue(CliSyntax.PREFIX_DURATION)
                    .orElse(TraceCommand.DEFAULT_DURATION.toString()));
            if (depth < 1 || depth > 5) {
                throw new ParseException("Depth must be greater than 0 and less than 6\n"
                        + TraceCommand.MESSAGE_USAGE);
            }
            if (duration < 1 || duration > 31) {
                throw new ParseException("Duration must be greater than 0 and less than 32\n"
                        + TraceCommand.MESSAGE_USAGE);
            }
        } catch (NumberFormatException e) {
            throw new ParseException("Depth and duration must be integers\n" + TraceCommand.MESSAGE_USAGE);
        }
        return new Integer[]{depth, duration};
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
