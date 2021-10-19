package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CASE_NUMBER;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SHN_PERIOD_END;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SHN_PERIOD_START;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.function.Predicate;

import seedu.address.logic.commands.FindCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Person;
import seedu.address.model.person.predicates.CaseNumberContainsKeysPredicate;
import seedu.address.model.person.predicates.NameContainsKeysPredicate;
import seedu.address.model.person.predicates.PhoneContainsKeysPredicate;
import seedu.address.model.person.predicates.ShnPeriodEndContainsKeysPredicate;
import seedu.address.model.person.predicates.ShnPeriodStartContainsKeysPredicate;

/**
 * Parses input arguments and creates a new FindCommand object
 */
public class FindCommandParser implements Parser<FindCommand> {
    public static int INDEX_ARGUMENT = 1;
    public static int INPUT_SIZE = 2;

    /**
     * Parses the given {@code String} of arguments in the context of the FindCommand
     * and returns a FindCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public FindCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_PHONE,
                PREFIX_CASE_NUMBER, PREFIX_SHN_PERIOD_START, PREFIX_SHN_PERIOD_END);

        if (!isOnlyOnePrefixEntered(argMultimap)) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
        }

        String userInputNoPrefix = removePrefixFromUserInput(args, argMultimap);
        String trimmedUserInputNoPrefix = userInputNoPrefix.trim();
        if (trimmedUserInputNoPrefix.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
        }

        String[] keys = trimmedUserInputNoPrefix.split("\\s+");
        return new FindCommand(parsePredicate(Arrays.asList(keys), argMultimap));
    }

    /**
     * Parse arguments into a predicate used to find persons.
     */
    private Predicate<Person> parsePredicate(List<String> keys, ArgumentMultimap argumentMultimap)
            throws ParseException {

        if (argumentMultimap.getValue(PREFIX_NAME).isPresent()) {
            return new NameContainsKeysPredicate(keys);
        }

        if (argumentMultimap.getValue(PREFIX_PHONE).isPresent()) {
            return new PhoneContainsKeysPredicate(keys);
        }

        if (argumentMultimap.getValue(PREFIX_CASE_NUMBER).isPresent()) {
            return new CaseNumberContainsKeysPredicate(keys);
        }

        if (argumentMultimap.getValue(PREFIX_SHN_PERIOD_START).isPresent()) {
            return new ShnPeriodStartContainsKeysPredicate(keys);
        }

        if (argumentMultimap.getValue(PREFIX_SHN_PERIOD_END).isPresent()) {
            return new ShnPeriodEndContainsKeysPredicate(keys);
        }

        throw new ParseException(
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
    }

    /**
     * Returns the user input with the prefix removed. If none of the prefix matches,
     * the same user input will be returned.
     */
    private String removePrefixFromUserInput(String args, ArgumentMultimap argumentMultimap) {
        if (argumentMultimap.getValue(PREFIX_NAME).isPresent()) {
            String[] argsSplitByPrefix = args.split(PREFIX_NAME.getPrefix());
            String output = argsSplitByPrefix.length == INPUT_SIZE
                    ? argsSplitByPrefix[INDEX_ARGUMENT]
                    : args;
        }

        if (argumentMultimap.getValue(PREFIX_PHONE).isPresent()) {
            String[] argsSplitByPrefix = args.split(PREFIX_PHONE.getPrefix());
            String output = argsSplitByPrefix.length == INPUT_SIZE
                    ? argsSplitByPrefix[INDEX_ARGUMENT]
                    : args;
        }

        if (argumentMultimap.getValue(PREFIX_CASE_NUMBER).isPresent()) {
            String[] argsSplitByPrefix = args.split(PREFIX_CASE_NUMBER.getPrefix());
            String output = argsSplitByPrefix.length == INPUT_SIZE
                    ? argsSplitByPrefix[INDEX_ARGUMENT]
                    : args;
        }

        if (argumentMultimap.getValue(PREFIX_SHN_PERIOD_START).isPresent()) {
            String[] argsSplitByPrefix = args.split(PREFIX_SHN_PERIOD_START.getPrefix());
            String output = argsSplitByPrefix.length == INPUT_SIZE
                    ? argsSplitByPrefix[INDEX_ARGUMENT]
                    : args;
        }

        if (argumentMultimap.getValue(PREFIX_SHN_PERIOD_END).isPresent()) {
            String[] argsSplitByPrefix = args.split(PREFIX_SHN_PERIOD_END.getPrefix());
            String output = argsSplitByPrefix.length == INPUT_SIZE
                    ? argsSplitByPrefix[INDEX_ARGUMENT]
                    : args;
        }

        return "";
    }

    /**
     * Returns true if user enters only one prefix.
     */
    private boolean isOnlyOnePrefixEntered(ArgumentMultimap argumentMultimap) {
        HashSet<Prefix> prefixes = new HashSet<>();

        if (argumentMultimap.getValue(PREFIX_NAME).isPresent()) {
            prefixes.add(PREFIX_NAME);
        }

        if (argumentMultimap.getValue(PREFIX_PHONE).isPresent()) {
            prefixes.add(PREFIX_PHONE);
        }

        if (argumentMultimap.getValue(PREFIX_CASE_NUMBER).isPresent()) {
            prefixes.add(PREFIX_CASE_NUMBER);
        }

        if (argumentMultimap.getValue(PREFIX_SHN_PERIOD_START).isPresent()) {
            prefixes.add(PREFIX_SHN_PERIOD_START);
        }

        if (argumentMultimap.getValue(PREFIX_SHN_PERIOD_END).isPresent()) {
            prefixes.add(PREFIX_SHN_PERIOD_END);
        }

        return prefixes.size() == FindCommand.LIMIT_PREFIX;
    }
}
