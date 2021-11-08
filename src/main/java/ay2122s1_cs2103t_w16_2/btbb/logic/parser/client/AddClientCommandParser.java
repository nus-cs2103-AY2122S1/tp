package ay2122s1_cs2103t_w16_2.btbb.logic.parser.client;

import static ay2122s1_cs2103t_w16_2.btbb.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static ay2122s1_cs2103t_w16_2.btbb.logic.parser.util.CliSyntax.PREFIX_CLIENT_ADDRESS;
import static ay2122s1_cs2103t_w16_2.btbb.logic.parser.util.CliSyntax.PREFIX_CLIENT_EMAIL;
import static ay2122s1_cs2103t_w16_2.btbb.logic.parser.util.CliSyntax.PREFIX_CLIENT_NAME;
import static ay2122s1_cs2103t_w16_2.btbb.logic.parser.util.CliSyntax.PREFIX_CLIENT_PHONE;

import ay2122s1_cs2103t_w16_2.btbb.exception.ParseException;
import ay2122s1_cs2103t_w16_2.btbb.logic.commands.client.AddClientCommand;
import ay2122s1_cs2103t_w16_2.btbb.logic.descriptors.ClientDescriptor;
import ay2122s1_cs2103t_w16_2.btbb.logic.parser.Parser;
import ay2122s1_cs2103t_w16_2.btbb.logic.parser.util.ArgumentMultimap;
import ay2122s1_cs2103t_w16_2.btbb.logic.parser.util.ArgumentTokenizer;
import ay2122s1_cs2103t_w16_2.btbb.logic.parser.util.ParserUtil;

/**
 * Parses input arguments and creates a new AddClientCommand object.
 */
public class AddClientCommandParser implements Parser<AddClientCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the AddClientCommand
     * and returns an AddClientCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format.
     */
    public AddClientCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args,
                PREFIX_CLIENT_NAME, PREFIX_CLIENT_PHONE, PREFIX_CLIENT_EMAIL, PREFIX_CLIENT_ADDRESS);


        if (!ParserUtil.areAllPrefixesPresent(argMultimap,
                PREFIX_CLIENT_NAME, PREFIX_CLIENT_ADDRESS, PREFIX_CLIENT_PHONE, PREFIX_CLIENT_EMAIL)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddClientCommand.MESSAGE_USAGE));
        }

        ClientDescriptor clientDescriptor = new ClientDescriptor();
        clientDescriptor.setName(ParserUtil.parseGenericString(argMultimap.getValue(PREFIX_CLIENT_NAME).get(), "Name"));
        clientDescriptor.setPhone(ParserUtil.parsePhone(argMultimap.getValue(PREFIX_CLIENT_PHONE).get()));
        clientDescriptor.setEmail(ParserUtil.parseEmail(argMultimap.getValue(PREFIX_CLIENT_EMAIL).get()));
        clientDescriptor.setAddress(ParserUtil.parseAddress(argMultimap.getValue(PREFIX_CLIENT_ADDRESS).get()));

        return new AddClientCommand(clientDescriptor);
    }
}
