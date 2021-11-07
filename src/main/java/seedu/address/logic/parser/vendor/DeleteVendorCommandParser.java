package seedu.address.logic.parser.vendor;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_VENDOR_ID;

import seedu.address.logic.commands.vendor.DeleteVendorCommand;
import seedu.address.logic.parser.ArgumentMultimap;
import seedu.address.logic.parser.ArgumentTokenizer;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.exceptions.ParseException;

// @author NicolasCwy
public class DeleteVendorCommandParser implements Parser<DeleteVendorCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the DeleteVendorCommand
     * and returns an DeleteVendorCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public DeleteVendorCommand parse(String args) throws ParseException {
        requireNonNull(args);

        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_VENDOR_ID);

        if (argMultimap.getValue(PREFIX_VENDOR_ID).isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteVendorCommand.MESSAGE_USAGE));
        }

        return new DeleteVendorCommand(ParserUtil.parseVendorId(argMultimap.getValue(PREFIX_VENDOR_ID).get()));
    }

}
