package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CASE_NUMBER;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;

import java.util.Arrays;
import java.util.HashSet;

import seedu.address.logic.commands.FindCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.predicates.CaseNumberContainsKeysPredicate;
import seedu.address.model.person.predicates.NameContainsKeywordsPredicate;
import seedu.address.model.person.predicates.PhoneContainsKeysPredicate;

/**
 * Parses input arguments and creates a new FindCommand object
 */
public class FindCommandParser implements Parser<FindCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the FindCommand
     * and returns a FindCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public FindCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_PHONE,
                PREFIX_CASE_NUMBER);

        if (!isOnlyOnePrefixEntered(argMultimap)) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
        }

        if (argMultimap.getValue(PREFIX_NAME).isPresent()) {
            String trimmedArgs = argMultimap.getValue(PREFIX_NAME).get().trim();
            String[] keys = trimmedArgs.split("\\s+");
            return new FindCommand(new NameContainsKeywordsPredicate(Arrays.asList(keys)));
        }

        if (argMultimap.getValue(PREFIX_PHONE).isPresent()) {
            String trimmedArgs = argMultimap.getValue(PREFIX_PHONE).get().trim();
            String[] keys = trimmedArgs.split("\\s+");
            return new FindCommand(new PhoneContainsKeysPredicate(Arrays.asList(keys)));
        }

        if (argMultimap.getValue(PREFIX_CASE_NUMBER).isPresent()) {
            String trimmedArgs = argMultimap.getValue(PREFIX_CASE_NUMBER).get().trim();
            String[] keys = trimmedArgs.split("\\s+");
            return new FindCommand(new CaseNumberContainsKeysPredicate(Arrays.asList(keys)));
        }

        throw new ParseException(
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
    }

    private boolean isOnlyOnePrefixEntered(ArgumentMultimap argumentMultimap) {
        HashSet<String> prefixes = new HashSet<>();

        if (argumentMultimap.getValue(PREFIX_NAME).isPresent()) {
            prefixes.add(PREFIX_NAME.getPrefix());
        }

        if (argumentMultimap.getValue(PREFIX_PHONE).isPresent()) {
            prefixes.add(PREFIX_PHONE.getPrefix());
        }

        if (argumentMultimap.getValue(PREFIX_CASE_NUMBER).isPresent()) {
            prefixes.add(PREFIX_CASE_NUMBER.getPrefix());
        }

        return prefixes.size() == FindCommand.LIMIT_PREFIX;
    }

}
