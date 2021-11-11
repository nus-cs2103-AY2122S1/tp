package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ALL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EXCLUDE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_INCLUDE;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.UnselectCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses the given {@code String} of arguments in the context of
 * UnselectCommand and returns a UnselectCommand object for execution.
 */
public class UnselectCommandParser implements Parser<UnselectCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the
     * UnselectCommand and returns a UnselectCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    @Override
    public UnselectCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_ALL, PREFIX_EXCLUDE, PREFIX_INCLUDE);

        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty() || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, UnselectCommand.MESSAGE_USAGE));
        }

        Optional<String> all = argMultimap.getValue(PREFIX_ALL);
        Optional<String> except = argMultimap.getValue(PREFIX_EXCLUDE);
        Optional<String> include = argMultimap.getValue(PREFIX_INCLUDE);

        if (except.isPresent() && !except.get().isEmpty()) {
            return new UnselectCommand(getIndexesFromString(except.get()), false);
        } else if (all.isPresent()) {
            return new UnselectCommand();
        } else if (include.isPresent() && !include.get().isEmpty()) {
            return new UnselectCommand(getIndexesFromString(include.get()), true);
        }

        throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, UnselectCommand.MESSAGE_USAGE));
    }

    private List<Index> getIndexesFromString(String args) throws ParseException {
        requireNonNull(args);
        String[] indexStrings = args.split("\\s+");
        List<Index> indexList = new ArrayList<>();
        for (String s : indexStrings) {
            indexList.add(ParserUtil.parseIndex(s));
        }
        return indexList;
    }

}
