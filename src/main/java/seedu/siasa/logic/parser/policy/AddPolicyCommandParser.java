package seedu.siasa.logic.parser.policy;

import static seedu.siasa.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.siasa.logic.parser.CliSyntax.PREFIX_COMMISSION;
import static seedu.siasa.logic.parser.CliSyntax.PREFIX_CONTACT_INDEX;
import static seedu.siasa.logic.parser.CliSyntax.PREFIX_EXPIRY;
import static seedu.siasa.logic.parser.CliSyntax.PREFIX_PAYMENT;
import static seedu.siasa.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.siasa.logic.parser.CliSyntax.PREFIX_TITLE;

import java.util.Set;
import java.util.stream.Stream;

import seedu.siasa.commons.core.index.Index;
import seedu.siasa.logic.commands.policy.AddPolicyCommand;
import seedu.siasa.logic.parser.ArgumentMultimap;
import seedu.siasa.logic.parser.ArgumentTokenizer;
import seedu.siasa.logic.parser.Parser;
import seedu.siasa.logic.parser.ParserUtil;
import seedu.siasa.logic.parser.Prefix;
import seedu.siasa.logic.parser.exceptions.ParseException;
import seedu.siasa.model.policy.Commission;
import seedu.siasa.model.policy.CoverageExpiryDate;
import seedu.siasa.model.policy.PaymentStructure;
import seedu.siasa.model.policy.Title;
import seedu.siasa.model.tag.Tag;

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
                ArgumentTokenizer.tokenize(args,
                        PREFIX_TITLE,
                        PREFIX_EXPIRY,
                        PREFIX_PAYMENT,
                        PREFIX_COMMISSION,
                    PREFIX_CONTACT_INDEX,
                        PREFIX_TAG);

        if (!arePrefixesPresent(argMultimap,
                PREFIX_TITLE,
                PREFIX_PAYMENT,
                PREFIX_COMMISSION,
            PREFIX_CONTACT_INDEX)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddPolicyCommand.MESSAGE_USAGE));
        }

        Title title = ParserUtil.parseTitle(argMultimap.getValue(PREFIX_TITLE).get());
        PaymentStructure paymentStructure =
                ParserUtil.parsePaymentStructure(argMultimap.getValue(PREFIX_PAYMENT).get());
        Commission commission =
                ParserUtil.parseCommission(argMultimap.getValue(PREFIX_COMMISSION).get(), paymentStructure);
        CoverageExpiryDate coverageExpiryDate = null;
        if (argMultimap.getValue(PREFIX_EXPIRY).isPresent()) {
            coverageExpiryDate = ParserUtil.parseCoverageExpiryDate(argMultimap.getValue(PREFIX_EXPIRY).get());
        }
        Index index = ParserUtil.parseIndex(argMultimap.getValue(PREFIX_CONTACT_INDEX).get());
        Set<Tag> tagList = ParserUtil.parseTags(argMultimap.getAllValues(PREFIX_TAG));

        return new AddPolicyCommand(title, paymentStructure, coverageExpiryDate, commission, index, tagList);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

}
