package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE_NUMBER;

import java.util.stream.Stream;

import seedu.address.logic.commands.AddClientCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.client.PhoneNumber;
import seedu.address.model.commons.Name;

public class AddClientCommandParser implements Parser<AddClientCommand> {
    @Override
    public AddClientCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(
                args, PREFIX_PHONE_NUMBER, PREFIX_EMAIL, PREFIX_ADDRESS);

        Name name;
        try {
            name = ParserUtil.parseName(argMultimap.getPreamble());
        } catch (ParseException parseException) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    AddClientCommand.MESSAGE_USAGE), parseException);
        }

        if (!arePrefixesPresent(argMultimap, PREFIX_PHONE_NUMBER)) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    AddClientCommand.MESSAGE_USAGE));
        }

        PhoneNumber phoneNumber = ParserUtil.parsePhoneNumber(
                argMultimap.getValue(PREFIX_PHONE_NUMBER).get());

        AddClientCommand.AddClientDescriptor descriptor =
                new AddClientCommand.AddClientDescriptor(name, phoneNumber);

        if (argMultimap.getValue(PREFIX_EMAIL).isPresent()) {
            descriptor.setEmail(ParserUtil.parseEmail(argMultimap.getValue(PREFIX_EMAIL).get()));
        }

        if (argMultimap.getValue(PREFIX_ADDRESS).isPresent()) {
            descriptor.setAddress(ParserUtil.parseAddress(argMultimap.getValue(PREFIX_ADDRESS).get()));
        }

        return new AddClientCommand(descriptor);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
