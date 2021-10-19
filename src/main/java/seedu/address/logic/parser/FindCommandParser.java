package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CASE_NUMBER;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SHN_PERIOD_END;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SHN_PERIOD_START;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Stream;

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

    /**
     * Parses the given {@code String} of arguments in the context of the FindCommand
     * and returns a FindCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public FindCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_PHONE,
                PREFIX_CASE_NUMBER, PREFIX_SHN_PERIOD_START, PREFIX_SHN_PERIOD_END);

        boolean hasOnlyOnePrefix = isOnlyOnePrefixEntered(argMultimap, PREFIX_NAME, PREFIX_PHONE,
                PREFIX_CASE_NUMBER, PREFIX_SHN_PERIOD_START, PREFIX_SHN_PERIOD_END);

        if (!hasOnlyOnePrefix || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
        }

        String trimmedUserInputNoPrefix = removePrefixFromUserInput(args.trim(), argMultimap).trim();
        if (trimmedUserInputNoPrefix.isBlank()) {
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
     * an empty string will be returned.
     */
    private String removePrefixFromUserInput(String args, ArgumentMultimap argumentMultimap) {
        if (argumentMultimap.getValue(PREFIX_NAME).isPresent()) {
            return args.substring(PREFIX_NAME.getPrefix().length()).trim();
        }

        if (argumentMultimap.getValue(PREFIX_PHONE).isPresent()) {
            return args.substring(PREFIX_PHONE.getPrefix().length()).trim();
        }

        if (argumentMultimap.getValue(PREFIX_CASE_NUMBER).isPresent()) {
            return args.substring(PREFIX_CASE_NUMBER.getPrefix().length()).trim();
        }

        if (argumentMultimap.getValue(PREFIX_SHN_PERIOD_START).isPresent()) {
            return args.substring(PREFIX_SHN_PERIOD_START.getPrefix().length()).trim();
        }

        if (argumentMultimap.getValue(PREFIX_SHN_PERIOD_END).isPresent()) {
            return args.substring(PREFIX_SHN_PERIOD_END.getPrefix().length()).trim();
        }

        return "";
    }

    /**
     * Returns true if user enters only one of the prefix is non-empty.
     */
    private boolean isOnlyOnePrefixEntered(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        int numOfPrefixEntered =  Stream.of(prefixes)
                .mapToInt(prefix -> argumentMultimap.getValue(prefix).isPresent() ? 1 : 0).sum();
        return numOfPrefixEntered == FindCommand.REQUIRED_NUMBER_OF_PREFIX;
    }
}
