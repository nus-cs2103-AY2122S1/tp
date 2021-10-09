package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.*;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Stream;

import seedu.address.logic.commands.AddCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.Model;
import seedu.address.model.person.*;
import seedu.address.model.tag.Tag;

/**
 * Parses input arguments and creates a new AddCommand object
 */
public class AddCommandParser implements Parser<AddCommand> {

    private Model model;

    public AddCommandParser(Model model) {
        this.model = model;

    }

    /**
     * Parses the given {@code String} of arguments in the context of the AddCommand
     * and returns an AddCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_PHONE, PREFIX_EMAIL, PREFIX_ADDRESS,
                    PREFIX_RISKAPPETITE, PREFIX_DISPOSABLEINCOME ,PREFIX_TAG);

        if (!arePrefixesPresent(argMultimap, PREFIX_NAME, PREFIX_EMAIL)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE));
        }

        String clientCounter;

        if (this.model == null) {
            clientCounter = "0";
        } else {
            clientCounter = this.model.getAddressBook().getClientCounter() == null ? "0"
                    : this.model.getAddressBook().getClientCounter();
        }

        ClientId clientId = new ClientId(clientCounter);
        Name name = ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get());
        Email email = ParserUtil.parseEmail(argMultimap.getValue(PREFIX_EMAIL).get());
        Phone phone = ParserUtil.parsePhone(argMultimap.getValue(PREFIX_PHONE));
        Address address = ParserUtil.parseAddress(argMultimap.getValue(PREFIX_ADDRESS));
        RiskAppetite riskAppetite = ParserUtil.parseRiskAppetite(argMultimap
            .getValue(PREFIX_RISKAPPETITE));
        DisposableIncome disposableIncome = ParserUtil.parseDisposableIncome(argMultimap
            .getValue(PREFIX_DISPOSABLEINCOME));
        Set<Tag> tagList = ParserUtil.parseTags(argMultimap.getAllValues(PREFIX_TAG));

        Person person = new Person(clientId, name, phone, email, address, riskAppetite, disposableIncome, tagList);

        int tempClientCounter = Integer.parseInt(clientCounter);
        String newClientCounter = Integer.toString(tempClientCounter + 1);
        this.model.getAddressBook().setClientCounter(newClientCounter);

        return new AddCommand(person);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

}
