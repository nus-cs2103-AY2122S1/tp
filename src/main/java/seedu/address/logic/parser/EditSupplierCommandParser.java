package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DELIVERY_DETAILS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SUPPLY_TYPE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.Collection;
import java.util.Collections;
import java.util.Optional;
import java.util.Set;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.EditSupplierCommand;
import seedu.address.logic.commands.EditSupplierCommand.EditSupplierDescriptor;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.tag.Tag;

/**
 * Parses input arguments and creates a new EditSupplierCommand object
 */
public class EditSupplierCommandParser implements Parser<EditSupplierCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the EditSupplierCommand
     * and returns an EditSupplierCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public EditSupplierCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_PHONE, PREFIX_EMAIL, PREFIX_ADDRESS, PREFIX_TAG,
                        PREFIX_SUPPLY_TYPE, PREFIX_DELIVERY_DETAILS);

        Index index;

        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditSupplierCommand.MESSAGE_USAGE), pe);
        }

        EditSupplierDescriptor editSupplierDescriptor = new EditSupplierDescriptor();
        if (argMultimap.getValue(PREFIX_NAME).isPresent()) {
            editSupplierDescriptor.setName(ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get()));
        }
        if (argMultimap.getValue(PREFIX_PHONE).isPresent()) {
            editSupplierDescriptor.setPhone(ParserUtil.parsePhone(argMultimap.getValue(PREFIX_PHONE).get()));
        }
        if (argMultimap.getValue(PREFIX_EMAIL).isPresent()) {
            editSupplierDescriptor.setEmail(ParserUtil.parseEmail(argMultimap.getValue(PREFIX_EMAIL).get()));
        }
        if (argMultimap.getValue(PREFIX_ADDRESS).isPresent()) {
            editSupplierDescriptor.setAddress(ParserUtil.parseAddress(argMultimap.getValue(PREFIX_ADDRESS).get()));
        }
        if (argMultimap.getValue(PREFIX_SUPPLY_TYPE).isPresent()) {
            editSupplierDescriptor.setSupplyType(ParserUtil.parseSupplyType(argMultimap.getValue(PREFIX_SUPPLY_TYPE)
                    .get()));
        }
        if (argMultimap.getValue(PREFIX_DELIVERY_DETAILS).isPresent()) {
            editSupplierDescriptor.setDeliveryDetails(
                    ParserUtil.parseDeliveryDetails(argMultimap.getValue(PREFIX_DELIVERY_DETAILS).get())
            );
        }
        parseTagsForEdit(argMultimap.getAllValues(PREFIX_TAG)).ifPresent(editSupplierDescriptor::setTags);

        if (!editSupplierDescriptor.isAnyFieldEdited()) {
            throw new ParseException(EditSupplierCommand.MESSAGE_NOT_EDITED);
        }

        return new EditSupplierCommand(index, editSupplierDescriptor);
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
