package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ALLERGIES;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_LP;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SPECIAL_REQUESTS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.Set;
import java.util.stream.Stream;

import seedu.address.logic.commands.AddCustomerCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Phone;
import seedu.address.model.person.customer.Allergy;
import seedu.address.model.person.customer.Customer;
import seedu.address.model.person.customer.LoyaltyPoints;
import seedu.address.model.person.customer.SpecialRequest;
import seedu.address.model.tag.Tag;

/**
 * Parses input arguments and creates a new AddCustomerCommand object
 */
public class AddCustomerCommandParser implements Parser<AddCustomerCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the AddCustomerCommand
     * and returns an AddCustomerCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddCustomerCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_PHONE, PREFIX_EMAIL, PREFIX_ADDRESS,
                        PREFIX_LP, PREFIX_ALLERGIES, PREFIX_SPECIAL_REQUESTS, PREFIX_TAG);
        if (!arePrefixesPresent(argMultimap, PREFIX_NAME, PREFIX_ADDRESS, PREFIX_PHONE, PREFIX_EMAIL,
                PREFIX_LP) || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    AddCustomerCommand.MESSAGE_USAGE));
        }

        Name name = ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get());
        Phone phone = ParserUtil.parsePhone(argMultimap.getValue(PREFIX_PHONE).get());
        Email email = ParserUtil.parseEmail(argMultimap.getValue(PREFIX_EMAIL).get());
        Address address = ParserUtil.parseAddress(argMultimap.getValue(PREFIX_ADDRESS).get());
        LoyaltyPoints loyaltyPoints = ParserUtil.parseLoyaltyPoints(argMultimap.getValue(PREFIX_LP).get());
        Set<Allergy> allergies = ParserUtil.parseAllergies(argMultimap.getAllValues(PREFIX_ALLERGIES));
        Set<SpecialRequest> specialRequests =
                ParserUtil.parseSpecialRequests(argMultimap.getAllValues(PREFIX_SPECIAL_REQUESTS));
        Set<Tag> tagList = ParserUtil.parseTags(argMultimap.getAllValues(PREFIX_TAG));

        Customer customer = new Customer(name, phone, email, address, loyaltyPoints, allergies,
                specialRequests, tagList);

        return new AddCustomerCommand(customer);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

}
