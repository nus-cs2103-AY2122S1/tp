package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMISSION_VALUE_NEGATIVE;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX;
import static seedu.address.commons.core.Messages.MESSAGE_NUMBER_TOO_LARGE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_COMMISSION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_INSURER;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NUMBER;

import java.util.stream.Stream;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.AddPolicyCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Name;
import seedu.address.model.person.Policy;

/**
 * Parses input arguments and creates a new AddPolicyCommand object
 */
public class AddPolicyCommandParser implements Parser<AddPolicyCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the AddPolicyCommand
     * and returns an AddPolicyCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddPolicyCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_INSURER, PREFIX_NUMBER, PREFIX_NAME, PREFIX_COMMISSION);

        if (!arePrefixesPresent(argMultimap, PREFIX_INSURER, PREFIX_NUMBER, PREFIX_NAME, PREFIX_COMMISSION)) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddPolicyCommand.MESSAGE_USAGE));
        }

        Index index;

        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (IndexOutOfBoundsException e) {
            throw new ParseException(MESSAGE_INVALID_PERSON_DISPLAYED_INDEX, e);
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddPolicyCommand.MESSAGE_USAGE), pe);
        }
        long policyNumber;
        try {
            policyNumber = ParserUtil.parsePolicyNumber(argMultimap.getValue(PREFIX_NUMBER).get());
            if (policyNumber > Math.pow(10, 16)) {
                throw new ParseException(MESSAGE_NUMBER_TOO_LARGE);
            }
        } catch (java.lang.NumberFormatException npe) {
            throw new ParseException(MESSAGE_NUMBER_TOO_LARGE);
        }
        Name insurer = ParserUtil.parseInsurerName(argMultimap.getValue(PREFIX_INSURER).get());
        Name policyName = ParserUtil.parsePolicyName(argMultimap.getValue(PREFIX_NAME).get());
        double commission = ParserUtil.parseCommission(argMultimap.getValue(PREFIX_COMMISSION).get());

        if (commission < 0) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMISSION_VALUE_NEGATIVE, commission));
        }
        Policy newPolicy = new Policy(insurer, policyNumber, policyName, commission);
        return new AddPolicyCommand(index, newPolicy);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

}
