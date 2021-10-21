package seedu.address.logic.parser;

import seedu.address.logic.commands.MailingListCommand;
import seedu.address.logic.parser.exceptions.ParseException;

import java.util.List;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.*;

/**
 * Parses the command
 */
public class MailingListCommandParser {

    private static List<Prefix> DEFAULT_PREFIXES = List.of(PREFIX_NAME,PREFIX_PHONE,PREFIX_EMAIL);

    public MailingListCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args,
                PREFIX_NAME, PREFIX_PHONE, PREFIX_EMAIL, PREFIX_ADDRESS, PREFIX_TAG, PREFIX_BIRTHDAY);

        Set<Prefix> prefixes = Set.of(PREFIX_NAME, PREFIX_PHONE, PREFIX_EMAIL, PREFIX_ADDRESS, PREFIX_TAG, PREFIX_BIRTHDAY);

        Predicate<Prefix> prefixIsPresent = prefix -> argMultimap.getValue(prefix).isPresent();
        Set<Prefix> providedPrefixes = prefixes.stream().filter(prefixIsPresent).collect(Collectors.toSet());

        if (providedPrefixes.isEmpty()) {
            providedPrefixes.addAll(DEFAULT_PREFIXES);
        }
        return new MailingListCommand(providedPrefixes);
    }

}
