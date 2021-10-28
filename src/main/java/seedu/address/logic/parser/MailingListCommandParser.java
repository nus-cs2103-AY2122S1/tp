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
 * Parses the command
 */
public class MailingListCommandParser implements Parser<MailingListCommand> {

    public static final Set<Prefix> DEFAULT_PREFIXES = Set.of(PREFIX_PHONE, PREFIX_EMAIL);

    /**
     * Parses the given {@code String} of arguments in the context of the MailingListCommand
     * and returns a MailingListCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public MailingListCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args,
                PREFIX_NAME, PREFIX_PHONE, PREFIX_EMAIL, PREFIX_ADDRESS, PREFIX_TAG, PREFIX_BIRTHDAY);

        Set<Prefix> prefixes = Set.of(PREFIX_NAME, PREFIX_PHONE, PREFIX_EMAIL,
                PREFIX_ADDRESS, PREFIX_TAG, PREFIX_BIRTHDAY);

        Predicate<Prefix> prefixIsPresent = prefix -> argMultimap.getValue(prefix).isPresent();
        Set<Prefix> providedPrefixes = prefixes.stream().filter(prefixIsPresent).collect(Collectors.toSet());

        if (providedPrefixes.isEmpty()) {
            providedPrefixes.addAll(DEFAULT_PREFIXES);
        }
        return new MailingListCommand(providedPrefixes);
    }

}
