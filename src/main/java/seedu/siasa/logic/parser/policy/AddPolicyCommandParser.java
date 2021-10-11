package seedu.siasa.logic.parser.policy;

import static seedu.siasa.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.siasa.logic.parser.CliSyntax.PREFIX_TITLE;
import static seedu.siasa.logic.parser.CliSyntax.PREFIX_EXPIRY;
import static seedu.siasa.logic.parser.CliSyntax.PREFIX_PRICE;
import static seedu.siasa.logic.parser.CliSyntax.PREFIX_COMMISSION;
import static seedu.siasa.logic.parser.CliSyntax.PREFIX_CLIENT_INDEX;

import java.util.HashSet;
import java.util.stream.Stream;

import seedu.siasa.logic.commands.policy.AddPolicyCommand;
import seedu.siasa.logic.parser.exceptions.ParseException;
import seedu.siasa.model.person.Address;
import seedu.siasa.model.person.Email;
import seedu.siasa.model.person.Name;
import seedu.siasa.model.person.Person;
import seedu.siasa.model.person.Phone;
import seedu.siasa.logic.parser.ArgumentMultimap;
import seedu.siasa.logic.parser.ArgumentTokenizer;
import seedu.siasa.logic.parser.Parser;
import seedu.siasa.logic.parser.ParserUtil;
import seedu.siasa.logic.parser.Prefix;
import seedu.siasa.model.policy.Commission;
import seedu.siasa.model.policy.ExpiryDate;
import seedu.siasa.model.policy.Title;
import seedu.siasa.model.policy.Policy;
import seedu.siasa.model.policy.Price;

/**
 * Parses input arguments and creates a new AddCommand object
 */
public class AddPolicyCommandParser implements Parser<AddPolicyCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the AddCLientCommand
     * and returns an AddClientCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddPolicyCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_TITLE, PREFIX_EXPIRY, PREFIX_PRICE, PREFIX_COMMISSION, PREFIX_CLIENT_INDEX);

        if (!arePrefixesPresent(argMultimap, PREFIX_TITLE, PREFIX_EXPIRY, PREFIX_PRICE, PREFIX_COMMISSION, PREFIX_CLIENT_INDEX)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddPolicyCommand.MESSAGE_USAGE));
        }

        Title title = ParserUtil.parseTitle(argMultimap.getValue(PREFIX_TITLE).get());
        Price price = ParserUtil.parsePrice(argMultimap.getValue(PREFIX_PRICE).get());
        ExpiryDate expiryDate = ParserUtil.parseExpiryDate(argMultimap.getValue(PREFIX_EXPIRY).get());
        Commission commission = ParserUtil.parseCommission(argMultimap.getValue(PREFIX_COMMISSION).get());

        // TODO: Link owner to policy
        Person owner = new Person(new Name("a"),
                new Phone("00000000"),
                new Email("a@b.com"),
                new Address("empty"),
                new HashSet<>());

        Policy policy = new Policy(title, price, expiryDate, commission, owner);

        return new AddPolicyCommand(policy);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

}
