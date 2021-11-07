package seedu.address.logic.parser.guest;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PASSPORT_NUMBER;
import static seedu.address.logic.parser.CliSyntax.PREFIX_VENDOR_ID;

import java.util.stream.Stream;

import seedu.address.logic.commands.guest.ChargeGuestCommand;
import seedu.address.logic.parser.ArgumentMultimap;
import seedu.address.logic.parser.ArgumentTokenizer;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.Prefix;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.guest.PassportNumber;
import seedu.address.model.vendor.VendorId;

/**
 * Parses inputs arguments and creates a new ChargeGuestCommand object
 */
public class ChargeGuestCommandParser implements Parser<ChargeGuestCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of ChargeGuestCommand
     * and returns an ChargeGuestCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public ChargeGuestCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_PASSPORT_NUMBER, PREFIX_VENDOR_ID);

        if (!arePrefixesPresent(argMultimap, PREFIX_PASSPORT_NUMBER, PREFIX_VENDOR_ID)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, ChargeGuestCommand.MESSAGE_USAGE));
        }

        PassportNumber passportNumber =
                ParserUtil.parsePassportNumber(argMultimap.getValue(PREFIX_PASSPORT_NUMBER).get());
        VendorId vendorId =
                ParserUtil.parseVendorId(argMultimap.getValue(PREFIX_VENDOR_ID).get());

        return new ChargeGuestCommand(passportNumber, vendorId);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }


}
