package seedu.academydirectory.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.academydirectory.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.academydirectory.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.academydirectory.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.academydirectory.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.academydirectory.logic.parser.CliSyntax.PREFIX_TELEGRAM;

import java.util.function.Supplier;
import java.util.stream.Stream;

import seedu.academydirectory.logic.commands.RetrieveCommand;
import seedu.academydirectory.logic.parser.exceptions.ParseException;
import seedu.academydirectory.model.student.InformationWantedFunction;

/**
 * Parses input arguments and creates a new FindCommand object
 */
public class RetrieveCommandParser implements Parser<RetrieveCommand> {
    private static final Supplier<Stream<Prefix>> RELEVANT_PREFIXES_SUPPLIER = () ->
            Stream.of(PREFIX_PHONE, PREFIX_EMAIL, PREFIX_TELEGRAM);

    /**
     * Parses the given {@code String} of arguments in the context of the FindCommand
     * and returns a FindCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public RetrieveCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args,
                        Stream.concat(Stream.of(PREFIX_NAME), RELEVANT_PREFIXES_SUPPLIER.get())
                                .toArray(Prefix[]::new));

<<<<<<< HEAD
        Stream<Prefix> prefixes = Stream.of(PREFIX_PHONE, PREFIX_EMAIL, PREFIX_TELEGRAM);
        boolean isAnyPrefixMatch = prefixes.anyMatch(x -> x.equals(infoPrefix));
        if (!isAnyPrefixMatch) {
=======
        // Check at least one relevant prefix is provided
        boolean noPrefix = RELEVANT_PREFIXES_SUPPLIER.get()
                .map(x -> argMultimap.getAllValues(x).isEmpty())
                .reduce(true, Boolean::logicalAnd);

        if (noPrefix) {
>>>>>>> Add support for multiple information retrieval
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, RetrieveCommand.MESSAGE_USAGE));
        }

        Stream<InformationWantedFunction> filters = RELEVANT_PREFIXES_SUPPLIER.get()
                .filter(x -> !argMultimap.getAllValues(x).isEmpty())
                .map(InformationWantedFunction::new);

        return new RetrieveCommand(filters);
    }

}
