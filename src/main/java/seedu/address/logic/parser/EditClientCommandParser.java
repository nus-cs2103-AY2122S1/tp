package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ORDER;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE_NUMBER;

import java.util.Collection;
import java.util.Collections;
import java.util.Optional;
import java.util.Set;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.EditClientCommand;
import seedu.address.logic.commands.EditClientCommand.EditClientDescriptor;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.Model;
import seedu.address.model.order.Order;

/**
 * Parses input arguments and creates a new EditClientCommand object
 */
public class EditClientCommandParser implements Parser<EditClientCommand> {
    private final Model model;

    public EditClientCommandParser(Model model) {
        this.model = model;
    }

    /**
     * Parses the given {@code String} of arguments in the context of the EditClientCommand
     * and returns an EditClientCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public EditClientCommand parse(String args) throws ParseException {
        requireNonNull(args);

        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_PHONE_NUMBER,
                PREFIX_EMAIL, PREFIX_ADDRESS, PREFIX_ORDER);

        Index index = ParserUtil.parseIndex(argMultimap.getPreamble());

        EditClientDescriptor editClientDescriptor = new EditClientDescriptor();
        if (argMultimap.getValue(PREFIX_NAME).isPresent()) {
            editClientDescriptor.setName(ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get()));
        }

        if (argMultimap.getValue(PREFIX_PHONE_NUMBER).isPresent()) {
            editClientDescriptor.setPhoneNumber(
                    ParserUtil.parsePhoneNumber(argMultimap.getValue(PREFIX_PHONE_NUMBER).get()));
        }

        if (argMultimap.getValue(PREFIX_EMAIL).isPresent()) {
            editClientDescriptor.setEmail(ParserUtil.parseEmail(argMultimap.getValue(PREFIX_EMAIL).get()));
        }

        if (argMultimap.getValue(PREFIX_ADDRESS).isPresent()) {
            editClientDescriptor.setAddress(ParserUtil.parseAddress(argMultimap.getValue(PREFIX_ADDRESS).get()));
        }

        parseOrdersForEdit(argMultimap.getAllValues(PREFIX_ORDER), model).ifPresent(editClientDescriptor::setOrders);

        if (!editClientDescriptor.isAnyFieldEdited()) {
            throw new ParseException(EditClientCommand.MESSAGE_NOT_EDITED);
        }

        return new EditClientCommand(index, editClientDescriptor);
    }

    /**
     * Parses {@code Collection<String> tags} into a {@code Set<Tag>} if {@code tags} is non-empty.
     * If {@code tags} contain only one element which is an empty string, it will be parsed into a
     * {@code Set<Tag>} containing zero tags.
     */
    private Optional<Set<Order>> parseOrdersForEdit(Collection<String> orders, Model model) throws ParseException {
        assert orders != null;

        if (orders.isEmpty()) {
            return Optional.empty();
        }

        Collection<String> orderSet = orders.size() == 1 && orders.contains("") ? Collections.emptySet() : orders;
        return Optional.of(ParserUtil.parseOrders(orderSet, model));
    }
}
