package seedu.modulink.logic.parser;

import static seedu.modulink.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.modulink.logic.parser.CliSyntax.PREFIX_MOD;

import java.util.Set;
import java.util.stream.Stream;

import seedu.modulink.logic.commands.FilterCommand;
import seedu.modulink.logic.parser.exceptions.ParseException;
import seedu.modulink.model.person.ModuleContainsKeywordsPredicate;
import seedu.modulink.model.tag.Mod;

public class FilterCommandParser implements Parser<FilterCommand> {

    public static final String MESSAGE_MORE_THAN_ONE_PARAMETER_FORMAT = "Only one module can be specified.";

    /**
     * Parses the given {@code String} of arguments in the context of the FilterCommand
     * and returns a FilterCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public FilterCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_MOD);

        if (!arePrefixesPresent(argMultimap, PREFIX_MOD)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, FilterCommand.MESSAGE_USAGE));
        }

        try {
            Set<Mod> modList = ParserUtil.parseTags(argMultimap.getAllValues(PREFIX_MOD));
            if (modList.size() > 1) {
                throw new ParseException(String.format(MESSAGE_MORE_THAN_ONE_PARAMETER_FORMAT,
                        FilterCommand.MESSAGE_USAGE));
            }

            return new FilterCommand(new ModuleContainsKeywordsPredicate(modList));

        } catch (ParseException e) {
            throw new ParseException(String.format(e.getMessage() + "%s",
                    e.getMessage().startsWith("Unknown prefix(es)") ? FilterCommand.MESSAGE_USAGE : ""));
        }

    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
