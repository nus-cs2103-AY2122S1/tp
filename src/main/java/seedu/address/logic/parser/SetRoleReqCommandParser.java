package seedu.address.logic.parser;

import seedu.address.logic.commands.SetRoleReqCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Role;

import java.util.Set;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ROLE;
import static seedu.address.logic.parser.ParserUtil.arePrefixesPresent;

public class SetRoleReqCommandParser {

    public static final ParseException DEFAULT_ERROR = new ParseException(
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, SetRoleReqCommand.HELP_MESSAGE));

    /**
     * Parses the given {@code String} of arguments in the context of the FindCommand
     * and returns a FindCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public SetRoleReqCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim().replace(SetRoleReqCommand.COMMAND_WORD, "");

        requireNonNull(args); // `setRoleReq r/kitchen-4 r/bartender-2`
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_ROLE);

        if (!argMultimap.getPreamble().isEmpty() || !arePrefixesPresent(argMultimap, PREFIX_ROLE)) {
            throw DEFAULT_ERROR;
        }

        Set<String> roleReqList = ParserUtil.parseRoleRequirements(argMultimap.getAllValues(PREFIX_ROLE));
        return new SetRoleReqCommand(roleReqList);
    }


}
