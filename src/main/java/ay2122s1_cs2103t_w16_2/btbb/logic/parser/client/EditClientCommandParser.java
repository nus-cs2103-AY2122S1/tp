package ay2122s1_cs2103t_w16_2.btbb.logic.parser.client;

import static ay2122s1_cs2103t_w16_2.btbb.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static ay2122s1_cs2103t_w16_2.btbb.commons.core.Messages.MESSAGE_NOT_EDITED;
import static ay2122s1_cs2103t_w16_2.btbb.logic.parser.util.CliSyntax.PREFIX_CLIENT_ADDRESS;
import static ay2122s1_cs2103t_w16_2.btbb.logic.parser.util.CliSyntax.PREFIX_CLIENT_EMAIL;
import static ay2122s1_cs2103t_w16_2.btbb.logic.parser.util.CliSyntax.PREFIX_CLIENT_NAME;
import static ay2122s1_cs2103t_w16_2.btbb.logic.parser.util.CliSyntax.PREFIX_CLIENT_PHONE;
import static java.util.Objects.requireNonNull;

import ay2122s1_cs2103t_w16_2.btbb.commons.core.index.Index;
import ay2122s1_cs2103t_w16_2.btbb.exception.ParseException;
import ay2122s1_cs2103t_w16_2.btbb.logic.commands.client.EditClientCommand;
import ay2122s1_cs2103t_w16_2.btbb.logic.descriptors.ClientDescriptor;
import ay2122s1_cs2103t_w16_2.btbb.logic.parser.Parser;
import ay2122s1_cs2103t_w16_2.btbb.logic.parser.util.ArgumentMultimap;
import ay2122s1_cs2103t_w16_2.btbb.logic.parser.util.ArgumentTokenizer;
import ay2122s1_cs2103t_w16_2.btbb.logic.parser.util.ParserUtil;

/**
 * Parses input arguments and creates a new EditClientCommand object
 */
public class EditClientCommandParser implements Parser<EditClientCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the EditClientCommand
     * and returns an EditClientCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public EditClientCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args,
                PREFIX_CLIENT_NAME, PREFIX_CLIENT_PHONE, PREFIX_CLIENT_EMAIL, PREFIX_CLIENT_ADDRESS);

        Index index;

        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditClientCommand.MESSAGE_USAGE),
                    pe);
        }

        ClientDescriptor editClientDescriptor = new ClientDescriptor();
        fillClientDescriptor(argMultimap, editClientDescriptor);

        if (!editClientDescriptor.isAnyFieldEdited()) {
            throw new ParseException(MESSAGE_NOT_EDITED);
        }

        return new EditClientCommand(index, editClientDescriptor);
    }

    private void fillClientDescriptor(ArgumentMultimap argMultimap,
                                      ClientDescriptor clientDescriptor) throws ParseException {
        if (argMultimap.getValue(PREFIX_CLIENT_NAME).isPresent()) {
            clientDescriptor.setName(ParserUtil.parseGenericString(argMultimap.getValue(PREFIX_CLIENT_NAME).get(),
                    "Name"));
        }
        if (argMultimap.getValue(PREFIX_CLIENT_PHONE).isPresent()) {
            clientDescriptor.setPhone(ParserUtil.parsePhone(argMultimap.getValue(PREFIX_CLIENT_PHONE).get()));
        }
        if (argMultimap.getValue(PREFIX_CLIENT_EMAIL).isPresent()) {
            clientDescriptor.setEmail(ParserUtil.parseEmail(argMultimap.getValue(PREFIX_CLIENT_EMAIL).get()));
        }
        if (argMultimap.getValue(PREFIX_CLIENT_ADDRESS).isPresent()) {
            clientDescriptor.setAddress(ParserUtil.parseAddress(argMultimap.getValue(PREFIX_CLIENT_ADDRESS).get()));
        }
    }
}
