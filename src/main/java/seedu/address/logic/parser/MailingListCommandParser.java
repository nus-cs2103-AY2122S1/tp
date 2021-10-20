package seedu.address.logic.parser;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.EditUtil;
import seedu.address.logic.commands.EditCommand;
import seedu.address.logic.commands.MailingListCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Phone;

import java.util.HashSet;
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

    public MailingListCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args,
                PREFIX_NAME, PREFIX_PHONE, PREFIX_EMAIL, PREFIX_ADDRESS, PREFIX_TAG, PREFIX_BIRTHDAY);

        Set<Prefix> prefixes = Set.of(PREFIX_NAME, PREFIX_PHONE, PREFIX_EMAIL, PREFIX_ADDRESS, PREFIX_TAG, PREFIX_BIRTHDAY);

        Predicate<Prefix> prefixIsPresent = prefix -> argMultimap.getValue(prefix).isPresent();
        Set<Prefix> providedPrefixes = prefixes.stream().filter(prefixIsPresent).collect(Collectors.toSet());

        return new MailingListCommand(providedPrefixes);

    }
}
