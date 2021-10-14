package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_TOO_MANY_FIELDS;
import static seedu.address.logic.parser.CliSyntax.ALL_PREFIXES;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import seedu.address.logic.commands.FindCommand;
import seedu.address.logic.commands.SortCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.SortByAttribute;
import seedu.address.model.person.SortDirection;

public class SortCommandParser implements Parser<SortCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the FindCommand
     * and returns a SortCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public SortCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
        }

        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, ALL_PREFIXES);
        if (!anyPrefixesPresent(argMultimap, ALL_PREFIXES) || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, SortCommand.MESSAGE_USAGE));
        }

        Prefix p = getPrefix(argMultimap, ALL_PREFIXES);

        SortDirection sortDirection = ParserUtil.parseSortDirection(argMultimap.getValue(p).get());
        SortByAttribute sorter = new SortByAttribute(p, sortDirection);

        return new SortCommand(sorter);
    }

    /**
     * Returns true if any of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean anyPrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).anyMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

    /**
     * Returns the prefix from the given {@code ArgumentMultiMap} and throws {@code ParseException}
     * if there is more than one prefix is present.
     */
    private static Prefix getPrefix(ArgumentMultimap argumentMultimap, Prefix... prefixes) throws ParseException {
        List<Prefix> resultList = Stream.of(prefixes).filter(prefix -> argumentMultimap.getValue(prefix).isPresent())
                .collect(Collectors.toList());
        if (resultList.size() > 1) {
            throw new ParseException(String.format(MESSAGE_TOO_MANY_FIELDS, SortCommand.MESSAGE_USAGE));
        }
        return resultList.get(0);
    }
}
