package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_BIRTHDAY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import seedu.address.logic.commands.MailingListCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new {@code MailingListCommand} object.
 */
public class MailingListCommandParser implements Parser<MailingListCommand> {

    public static final Set<Prefix> DEFAULT_PREFIXES = Set.of(PREFIX_PHONE, PREFIX_EMAIL);

    public static final Set<Prefix> ALL_PREFIXES = Set.of(PREFIX_NAME, PREFIX_PHONE, PREFIX_EMAIL,
            PREFIX_ADDRESS, PREFIX_TAG, PREFIX_BIRTHDAY);

    /**
     * Parses the given {@code String} of arguments in the context of the MailingListCommand
     * and returns a MailingListCommand object for execution.
     *
     * @param args user input.
     * @return {@code MailingListCommand} which generates a csv file with displayed contacts.
     * @throws ParseException if the user input does not conform the expected format.
     */
    public MailingListCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, ALL_PREFIXES.toArray(Prefix[]::new));

        Predicate<Prefix> prefixIsPresent = prefix -> argMultimap.getValue(prefix).isPresent();
        Set<Prefix> providedPrefixes = ALL_PREFIXES.stream().filter(prefixIsPresent).collect(Collectors.toSet());

        // check that only valid Prefix are provided
        if (hasExtraArguments(argMultimap, providedPrefixes)) {
            throw new ParseException(MailingListCommand.MESSAGE_EXTRA_ARGUMENTS_FAILURE);
        }

        // set defaults
        if (providedPrefixes.isEmpty()) {
            providedPrefixes.addAll(DEFAULT_PREFIXES);
        }
        return new MailingListCommand(providedPrefixes);
    }

    private boolean hasExtraArguments(ArgumentMultimap argMultimap, Set<Prefix> providedPrefixes) {
        // check for preamble
        if (!argMultimap.getPreamble().equals("")) {
            return true;
        }

        // check for data after a prefix
        boolean hasExtraArguments = providedPrefixes.stream()
                .map(p -> argMultimap.getValue(p).get())
                .anyMatch(p -> !p.equals(""));
        if (hasExtraArguments) {
            return true;
        }

        return false;
    }


}
