package seedu.address.logic.parser.vendor;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_COST;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_OPERATING_HOURS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SERVICE_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CliSyntax.PREFIX_VENDOR_ID;

import java.util.Collection;
import java.util.Collections;
import java.util.Optional;
import java.util.Set;

import seedu.address.logic.commands.vendor.EditVendorCommand;
import seedu.address.logic.commands.vendor.EditVendorCommand.EditVendorDescriptor;
import seedu.address.logic.parser.ArgumentMultimap;
import seedu.address.logic.parser.ArgumentTokenizer;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.tag.Tag;
import seedu.address.model.vendor.VendorId;

public class EditVendorCommandParser implements Parser<EditVendorCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the EditVendorCommand
     * and returns an EditVendorCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public EditVendorCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_PHONE, PREFIX_EMAIL, PREFIX_ADDRESS, PREFIX_TAG,
                        PREFIX_VENDOR_ID, PREFIX_COST, PREFIX_OPERATING_HOURS, PREFIX_SERVICE_NAME);

        VendorId vendorId;

        EditVendorDescriptor editVendorDescriptor = new EditVendorDescriptor();

        if (argMultimap.getValue(PREFIX_NAME).isPresent()) {
            editVendorDescriptor.setName(ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get()));
        }

        if (argMultimap.getValue(PREFIX_EMAIL).isPresent()) {
            editVendorDescriptor.setEmail(ParserUtil.parseEmail(argMultimap.getValue(PREFIX_EMAIL).get()));
        }

        parseTagsForEdit(argMultimap.getAllValues(PREFIX_TAG)).ifPresent(editVendorDescriptor::setTags);

        if (argMultimap.getValue(PREFIX_VENDOR_ID).isPresent()) {
            vendorId = ParserUtil.parseVendorId(argMultimap.getValue(PREFIX_VENDOR_ID).get());
            editVendorDescriptor.setVendorId(vendorId);
        } else {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditVendorCommand.MESSAGE_USAGE));
        }

        if (argMultimap.getValue(PREFIX_ADDRESS).isPresent()) {
            editVendorDescriptor.setAddress(
                    ParserUtil.parseAddress(argMultimap.getValue(PREFIX_ADDRESS).get()));
        }

        if (argMultimap.getValue(PREFIX_PHONE).isPresent()) {
            editVendorDescriptor.setPhone(
                    ParserUtil.parsePhone(argMultimap.getValue(PREFIX_PHONE).get()));
        }

        if (argMultimap.getValue(PREFIX_SERVICE_NAME).isPresent()) {
            editVendorDescriptor.setServiceName(
                    ParserUtil.parseServiceName(argMultimap.getValue(PREFIX_SERVICE_NAME).get()));
        }

        if (argMultimap.getValue(PREFIX_COST).isPresent()) {
            editVendorDescriptor.setCost(
                    ParserUtil.parseCost(argMultimap.getValue(PREFIX_COST).get()));
        }

        if (argMultimap.getValue(PREFIX_OPERATING_HOURS).isPresent()) {
            editVendorDescriptor.setOperatingHours(
                    ParserUtil.parseOperatingHours(argMultimap.getValue(PREFIX_OPERATING_HOURS).get()));
        }

        if (!editVendorDescriptor.isAnyFieldEdited()) {
            throw new ParseException(EditVendorCommand.MESSAGE_NOT_EDITED);
        }

        return new EditVendorCommand(vendorId, editVendorDescriptor);
    }

    /**
     * Parses {@code Collection<String> tags} into a {@code Set<Tag>} if {@code tags} is non-empty.
     * If {@code tags} contain only one element which is an empty string, it will be parsed into a
     * {@code Set<Tag>} containing zero tags.
     */
    private Optional<Set<Tag>> parseTagsForEdit(Collection<String> tags) throws ParseException {
        assert tags != null;

        if (tags.isEmpty()) {
            return Optional.empty();
        }
        Collection<String> tagSet = tags.size() == 1 && tags.contains("") ? Collections.emptySet() : tags;
        return Optional.of(ParserUtil.parseTags(tagSet));
    }


}
